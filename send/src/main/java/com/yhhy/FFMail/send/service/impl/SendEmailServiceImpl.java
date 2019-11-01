package com.yhhy.FFMail.send.service.impl;
/**
 * 发送邮件的服务，先判断是否连接成功，若没有先连接，已连接直接发送邮件
 * 判断连接的方法先写在这里，后面拆出来
 * 邮件包括：发件人，收件人（暂定一个），发件时间，发件主题，发件内容（暂只考虑简单文本）
 */

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhhy.FFMail.send.dao.SendEmailDao;
import com.yhhy.FFMail.send.domain.SendEmailinfo;
import com.yhhy.FFMail.send.service.SendEmailService;

@Service("SendEmailService")
public class SendEmailServiceImpl implements SendEmailService {
	@Autowired
	private SendEmailDao sendEmailDao;
	@SuppressWarnings("static-access")
	@Override
	public void sendEmail(SendEmailinfo sendInfo) throws Exception{
		// TODO Auto-generated method stub
		 Properties pro = System.getProperties();
		  pro.put("mail.transport.protocol", "smtp"); // 连接协议
	      pro.put("mail.smtp.host", "smtp.qq.com");
	      pro.put("mail.smtp.port", 465);
	      pro.put("mail.smtp.auth", "true");
	      pro.put("mail.smtp.ssl.enable", "true");  // 设置是否使用ssl安全连接 ---一般都使用
	      pro.put("mail.debug", "true"); // 设置是否显示debug信息 true 会在控制台显示相关
	      // 根据邮件会话属性和密码验证器构造一个发送邮件的session
	 //     Session sendMailSession = Session.getInstance(pro);
	      Session sendMailSession = Session.getDefaultInstance(pro,
	            new Authenticator()
	            {
	               @Override
	               protected PasswordAuthentication getPasswordAuthentication()
	               {
	                  return new PasswordAuthentication(sendInfo.getEmailFrom(), sendInfo.getEmailPwd());
	               }
	            });
	      // 根据session创建一个邮件消息
	      Message mailMessage = new MimeMessage(sendMailSession);
	      // 设置邮件消息的发送者
	      mailMessage.setFrom(new InternetAddress(sendInfo.getEmailFrom()));
	      // 创建邮件的接收者地址，并设置到邮件消息中
	      mailMessage.setRecipient(Message.RecipientType.TO,
	            new InternetAddress(sendInfo.getEmailRecipient()));
	      // 设置邮件消息的主题
	      mailMessage.setSubject(sendInfo.getEmailSubject());
	      // 设置邮件消息发送的时间
	      mailMessage.setSentDate(new Date());
	      System.out.println(new Date());
	      // 设置邮件消息的主要内容
	      mailMessage.setText(sendInfo.getEmailContent());
	      // 得到邮差对象
	      Transport transport = sendMailSession.getTransport();
	      //transport.connect(sendInfo.getEmailFrom(),"ovhkpakrkqwabege");
	      // 发送邮件
	     // transport.send(mailMessage);
	      transport.send(mailMessage);
	      sendEmailDao.saveSendEmail(sendInfo);
	}
	

}
