package com.yhhy.FFMail.setting.service.user.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.mail.imap.IMAPFolder;
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
	public void recvEmail(UserEmailBasicInfo email) {
		RecvEmailInfo recvEmailInfo = new RecvEmailInfo();
		try {
			RemoveCryptographyRestrictions.init();
			// 连接邮件服务器的参数配置
			Properties props = new Properties();
			props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.setProperty("mail.imap.socketFactory.fallback", "false");
			// 设置传输协议和端口
			props.setProperty("mail.transport.protocol", "imap");
			props.setProperty("mail.imap.port", email.getRecvPort());
			props.setProperty("mail.imap.socketFactory.port", email.getRecvPort());
			// 设置收件人的pop3服务器
			// props.setProperty("mail.pop3.host", "pop3.163.com");
			// 创建Session对象
			Session session = Session.getInstance(props);
			// 打印session session.setDebug(true);

			Store store = session.getStore("imap");
			// 连接imap服务器
			store.connect(email.getRecvService(), email.getEmailAddress(), email.getEmailPwd());
			// 获得用户的邮件账号，且通过pop3协议获取的文件夹只能是inbox
			Folder folder = store.getFolder("inbox");
			//获取邮件UID需要先进行强制转换
			IMAPFolder inbox = (IMAPFolder) folder; 
			// 设置对邮件账户的访问权限
			folder.open(Folder.READ_WRITE);
			// 得到账户信息的所有邮件信息
			Message[] messages = folder.getMessages();
			for (Message msg : messages) {
				MimeMessage mimeMsg = (MimeMessage) msg;
				//获取邮件ID
				long uid = inbox.getUID(mimeMsg);
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
			folder.close(false);
			store.close();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {
		String from = "";
		Address[] froms = msg.getFrom();
		if (froms.length < 1)
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

}
