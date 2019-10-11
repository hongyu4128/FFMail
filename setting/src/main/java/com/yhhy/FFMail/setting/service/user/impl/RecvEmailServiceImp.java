package com.yhhy.FFMail.setting.service.user.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.pop3.POP3Folder;
import com.sun.mail.pop3.POP3Store;
import com.yhhy.FFMail.setting.dao.user.RecvEmailInfoDao;
import com.yhhy.FFMail.setting.domain.user.RecvEmailInfo;
import com.yhhy.FFMail.setting.domain.user.UserEmailBasicInfo;
import com.yhhy.FFMail.setting.service.user.RecvEmailService;
import com.yhhy.FFMailBasic.basic.common.RemoveCryptographyRestrictions;

/**
 * 用于设置收取邮件
 * 
 * @author Poppy
 *
 */
@Service("recvEmailService")
public class RecvEmailServiceImp implements RecvEmailService {
	@Autowired
	RecvEmailInfoDao recvEmailInfoDao;

	@Override
	public void recvEmail(UserEmailBasicInfo email) throws AuthenticationFailedException,NoSuchProviderException,MessagingException,IOException,Exception{

		RecvEmailInfo recvEmailInfo = new RecvEmailInfo();
		RemoveCryptographyRestrictions.init();
		Folder folder = null;
		POP3Folder inbox1 = null;
		IMAPFolder inbox2 = null;
		Store store = null;
		POP3Store pop3Store = null;

		if(email.getRecvServiceType().equals("pop3")) {
			// 连接邮件服务器的参数配置
//			Properties props = new Properties();
//			props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//			props.setProperty("mail.imajavap.socketFactory.fallback", "false");
//			// 设置传输协议和端口
//			props.setProperty("mail.store.protocol", "pop3");//协议
//     		props.setProperty("mail.pop3.port", "995");//端口
//			props.setProperty("mail.pop3.host", "pop3.163.com");//pop3服务器
			// 创建Session对象
//			Session session = Session.getInstance(props);
			// 打印session session.setDebug(true);
			//pop3Store = (POP3Store) session.getStore(email.getRecvServiceType());
//			store = session.getStore(email.getRecvServiceType());
			store =  getQQSSLStore(email);		
			// 连接imap服务器
			store.connect("pop.qq.com",995,email.getEmailAddress(), email.getEmailPwd());
			// 获得用户的邮件账号，且通过pop3协议获取的文件夹只能是inbox
			folder = store.getFolder("inbox");
			//获取邮件UID需要先进行强制转换
			inbox1 = (POP3Folder) folder; 
			// 设置对邮件账户的访问权限
			folder.open(Folder.READ_WRITE);
			// 得到账户信息的所有邮件信息
			Message[] messages = folder.getMessages();
			for (Message msg : messages) {
				MimeMessage mimeMsg = (MimeMessage) msg;
				//获取邮件ID
				String uid = inbox1.getUID(mimeMsg);
				//数据库中如果已经有了该uid，则跳过该邮件，否则收取
				if(recvEmailInfoDao.uidIsExists(email.getEmailAddress(), uid))
					continue;
				else {
					// 获得邮件主题
					String subject = MimeUtility.decodeText(mimeMsg.getSubject());
					// 获得邮件发件人
					String from = getFrom(mimeMsg);
					//        InternetAddress address = new InternetAddress();
					//获取邮件发送时间
					Date sendDate = mimeMsg.getSentDate();
					//获取邮件收取时间
					Date recvDate = mimeMsg.getReceivedDate();
					// 获得邮件内容
					StringBuffer sbf = new StringBuffer(100);
					getMailTextContent(mimeMsg, sbf);

					// 将获取到的邮件信息保存到recvEmailInfo对象中
					recvEmailInfo.setUid(uid);
					recvEmailInfo.setEmailAddress(email.getEmailAddress());
					recvEmailInfo.setEmailFrom(from);
					recvEmailInfo.setEmailSubject(subject);
					recvEmailInfo.setSendDate(sendDate);
					recvEmailInfo.setRecvDate(recvDate);
					recvEmailInfo.setEmailContent(sbf.toString());
					// 将recvEmailInfo对象作为参数传给recvEmailInfoDao方法，作为存储邮件的参数
					recvEmailInfoDao.saveRecvEmailInfo(recvEmailInfo);
				}
			}
		}
		else if (email.getRecvServiceType().equals("imap")) {
			// 连接邮件服务器的参数配置
			Properties props = new Properties();
			props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.imajavap.socketFactory.fallback", "false");
			// 设置传输协议和端口
			props.setProperty("mail.transport.protocol", "imap");
			props.setProperty("mail.imap.port", "993");
			props.setProperty("mail.imap.socketFactory.port", "993");
			// 设置收件人的pop3服务器
			// props.setProperty("mail.pop3.host", "pop3.163.com");
			// 创建Session对象
			Session session = Session.getInstance(props);
			// 打印session session.setDebug(true);
			store = session.getStore(email.getRecvServiceType());
			// 连接imap服务器
			store.connect(email.getRecvService(), email.getEmailAddress(), email.getEmailPwd());
			// 获得用户的邮件账号，且通过pop3协议获取的文件夹只能是inbox
			folder = store.getFolder("inbox");
			//获取邮件UID需要先进行强制转换
			inbox2 = (IMAPFolder) folder; 			
			// 设置对邮件账户的访问权限
			folder.open(Folder.READ_WRITE);
			// 得到账户信息的所有邮件信息
			Message[] messages = folder.getMessages();
			for (Message msg : messages) {
				MimeMessage mimeMsg = (MimeMessage) msg;

				//获取邮件ID
				String uid = Long.toString(inbox2.getUID(mimeMsg));
				//数据库中如果已经有了该uid，则跳过该邮件，否则收取
				if(recvEmailInfoDao.uidIsExists(email.getEmailAddress(), uid))
					continue;
				else {
					// 获得邮件主题
					String subject = MimeUtility.decodeText(mimeMsg.getSubject());
					// 获得邮件发件人
					String from = getFrom(mimeMsg);
					//        InternetAddress address = new InternetAddress();
					//获取邮件发送时间
					Date sendDate = mimeMsg.getSentDate();
					//获取邮件收取时间
					Date recvDate = mimeMsg.getReceivedDate();
					// 获得邮件内容
					StringBuffer sbf = new StringBuffer(100);
					getMailTextContent(mimeMsg, sbf);

					// 将获取到的邮件信息保存到recvEmailInfo对象中
					recvEmailInfo.setUid(uid);
					recvEmailInfo.setEmailAddress(email.getEmailAddress());
					recvEmailInfo.setEmailFrom(from);
					recvEmailInfo.setEmailSubject(subject);
					recvEmailInfo.setSendDate(sendDate);
					recvEmailInfo.setRecvDate(recvDate);
					recvEmailInfo.setEmailContent(sbf.toString());
					// 将recvEmailInfo对象作为参数传给recvEmailInfoDao方法，作为存储邮件的参数
					recvEmailInfoDao.saveRecvEmailInfo(recvEmailInfo);
				}
			}
		}
		folder.close(false);
		store.close();
		pop3Store.close();
	}

	public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {
		String from = "";
		Address[] froms = msg.getFrom();
		if (froms == null || froms.length < 1)
			throw new MessagingException("没有发件人!");

		InternetAddress address = (InternetAddress) froms[0];
		String person = address.getPersonal();
		if (person != null) {
			person = MimeUtility.decodeText(person) + " ";
		} else {
			person = "";
		}
		from = person + "<" + address.getAddress() + ">";

		return from;
	}

	/**
	 * 获得邮件文本内容
	 * 
	 * @param part    邮件体
	 * @param content 存储邮件文本内容的字符串
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
		// 如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
		boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
		if (part.isMimeType("text/*") && !isContainTextAttach) {
			content.append(part.getContent().toString());
		} else if (part.isMimeType("message/rfc822")) {
			getMailTextContent((Part) part.getContent(), content);
		} else if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			int partCount = multipart.getCount();
			for (int i = 0; i < partCount; i++) {
				BodyPart bodyPart = multipart.getBodyPart(i);
				getMailTextContent(bodyPart, content);
			}
		}
	}

	private InternetAddress[] parse(Address[] from, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	private String parseMultipart(Multipart content) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * qq邮箱需要建立ssl连接才能接收邮件 pop3方式
	 * */
	public static Store getQQSSLStore(UserEmailBasicInfo email) throws Exception {
        // 创建一个有具体连接信息的Properties对象  
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.pop3.socketFactory.fallback", "false");
        props.setProperty("mail.pop3.port", "465");
        props.setProperty("mail.pop3.socketFactory.port", "995");
        Session session = Session.getInstance(props);
        URLName urln = new URLName("pop3", email.getRecvService(), email.getRecvPort(), null,email.getEmailAddress(),email.getEmailPwd());
        Store store = session.getStore(urln);
        return store;
	}
	/**
	 * 返回非ssl连接
	 * */
	public static Store getStore(UserEmailBasicInfo email) throws Exception {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", email.getSendService());
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props, null);
		URLName urln = new URLName("pop3", email.getRecvService(), 110, null,
				email.getEmailAddress(),email.getEmailPwd());
		Store store = session.getStore(urln);
		return store;
	}
}
