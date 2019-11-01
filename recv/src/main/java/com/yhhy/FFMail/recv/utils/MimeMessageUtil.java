package com.yhhy.FFMail.recv.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * mime报文工具类
 * 
 * @author hongyu
 *
 */
public class MimeMessageUtil {
	public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {
		  String from = "";
		  InternetAddress address = null;
		  Address[] froms = msg.getFrom();
		  if (froms == null || froms.length < 1) {
		   from = "<unknown@mail.com>";
		   //throw new MessagingException("没有发件人!");
		  }
		  else {
		   address = (InternetAddress) froms[0];
		   String person = address.getPersonal();
		   if (person != null) {
		    person = MimeUtility.decodeText(person) + " ";
		   } else {
		    person = "";
		   }
		   from = person + "<" + address.getAddress() + ">";
		  }
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
}
