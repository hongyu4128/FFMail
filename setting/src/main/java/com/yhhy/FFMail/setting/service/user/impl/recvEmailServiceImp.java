package com.yhhy.FFMail.setting.service.user.impl;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailMessage;
import org.springframework.stereotype.Service;

import com.yhhy.FFMail.setting.dao.user.RecvEmailInfoDao;
import com.yhhy.FFMail.setting.domain.user.RecvEmailInfo;
import com.yhhy.FFMail.setting.domain.user.UserEmailBasicInfo;
import com.yhhy.FFMail.setting.service.user.recvEmailService;

/**
 * 用于设置收取邮件
 * @author Poppy
 *
 */
@Service("recvEmailServiceImp")
public class recvEmailServiceImp implements recvEmailService{
	@Autowired
	public RecvEmailInfo recvEmailInfo;
	@Autowired
	RecvEmailInfoDao recvEmailInfoDao;

	@Override
	public void recvEmail(UserEmailBasicInfo email){
		// TODO Auto-generated method stub
		//链接邮件服务器的参数配置
		Properties props = new Properties();
		//设置传输协议
		props.setProperty("mail.store.protocal", "pop3");
		//设置收件人的pop3服务器
		props.setProperty("mail.pop3.host", "pop2.163.com");
		//创建Session对象
		Session session = Session.getInstance(props);
		//打印session  session.setDebug(true);
		try {
			Store store = session.getStore("pop3");
			//连接pop3服务器
			store.connect("pop3.63.com",email.getEmailAddress(),email.getEmailPwd());
			//获得用户的邮件账号，且通过pop3协议获取的文件夹只能是inbox
			Folder folder = store.getFolder("inbox");
			//设置对邮件账户的访问权限
			folder.open(Folder.READ_WRITE);
			//得到账户信息的所有邮件信息
			Message[] messages = folder.getMessages();
			for(int i = 0; i < messages.length; i++) {
				//获得邮件主题
				String subject = messages[i].getSubject();
				//获得邮件发件人
				Address[] from = messages[i].getFrom();
				//获得邮件内容
				String content = (String) messages[i].getContent();
				//将获取到的邮件信息保存到recvEmailInfo对象中
				recvEmailInfo.setEmailAddress(email.getEmailAddress());
				recvEmailInfo.setEmailFrom(from);
				recvEmailInfo.setEmailSubject(subject);
				recvEmailInfo.setEmailContent(content);
				//将recvEmailInfo对象作为参数传给recvEmailInfoDao方法，作为存储邮件的参数
				recvEmailInfoDao.saveRecvEmailInfo(recvEmailInfo);
			}
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
