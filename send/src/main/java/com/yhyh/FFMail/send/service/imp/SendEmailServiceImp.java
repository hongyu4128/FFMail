package com.yhyh.FFMail.send.service.imp;
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
import org.springframework.stereotype.Service;
import com.yhyh.FFMail.send.domain.SendEmail;
import com.yhyh.FFMail.send.service.SendEmailService;

@Service("SendEmailService")
public class SendEmailServiceImp implements SendEmailService {

	@Override
	public void sendEmail(SendEmail sendInfo) throws Exception{
		// TODO Auto-generated method stub
		 Properties pro = System.getProperties();
	      pro.put("mail.smtp.host", "smtp.126.com");
	      pro.put("mail.smtp.port", "25");
	      pro.put("mail.smtp.auth", "true");

	      // 根据邮件会话属性和密码验证器构造一个发送邮件的session
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
	      // 设置邮件消息的主要内容
	      mailMessage.setText(sendInfo.getEmailContent());
	      // 发送邮件
	      Transport.send(mailMessage);
	      
	}

}
