package com.yhhy.FFMail.recv.utils;

import java.security.Security;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import com.yhhy.FFMailBasic.basic.domain.user.email.UserEmailBasicInfo;

/**
 * SSL连接工具类
 * 
 * @author hongyu
 *
 */
public class SSLUtil {
    /**
     * qq邮箱需要建立ssl连接才能接收邮件 pop3方式
     */
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
        URLName urln = new URLName("pop3", email.getRecvService(), email.getRecvPort(), null, email.getEmailAddress(),
                email.getEmailPwd());
        Store store = session.getStore(urln);
        return store;
    }

    /**
     * 返回非ssl连接
     */
    public static Store getStore(UserEmailBasicInfo email) throws Exception {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", email.getSendService());
        props.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, null);
        URLName urln = new URLName("pop3", email.getRecvService(), 110, null, email.getEmailAddress(),
                email.getEmailPwd());
        Store store = session.getStore(urln);
        return store;
    }
}
