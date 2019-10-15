package com.yhhy.FFMail.recv.service.impl.pop3;

import java.io.IOException;
import java.util.Date;

import javax.mail.AuthenticationFailedException;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.pop3.POP3Folder;
import com.sun.mail.pop3.POP3Store;
import com.yhhy.FFMail.recv.dao.RecvEmailDao;
import com.yhhy.FFMail.recv.domain.RecvEmail;
import com.yhhy.FFMail.recv.service.RecvEmailService;
import com.yhhy.FFMail.recv.utils.MimeMessageUtil;
import com.yhhy.FFMail.recv.utils.SSLUtil;
import com.yhhy.FFMailBasic.basic.common.RemoveCryptographyRestrictions;
import com.yhhy.FFMailBasic.basic.domain.user.email.UserEmailBasicInfo;

public class POP3RecvMailServiceImpl implements RecvEmailService {

    @Autowired
    private RecvEmailDao recvEmailDao;

    @Override
    public void recvEmail(UserEmailBasicInfo email)
            throws AuthenticationFailedException, NoSuchProviderException, MessagingException, IOException, Exception {
        RecvEmail recvEmailInfo = new RecvEmail();
        RemoveCryptographyRestrictions.init();
        Folder folder = null;
        POP3Folder inbox = null;
        Store store = null;
        // 连接邮件服务器的参数配置
//      Properties props = new Properties();
//      props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//      props.setProperty("mail.imajavap.socketFactory.fallback", "false");
//      // 设置传输协议和端口
//      props.setProperty("mail.store.protocol", "pop3");//协议
//      props.setProperty("mail.pop3.port", "995");//端口
//      props.setProperty("mail.pop3.host", "pop3.163.com");//pop3服务器
        // 创建Session对象
//      Session session = Session.getInstance(props);
        // 打印session session.setDebug(true);
        // pop3Store = (POP3Store) session.getStore(email.getRecvServiceType());
//      store = session.getStore(email.getRecvServiceType());
        store = SSLUtil.getQQSSLStore(email);
        // 连接imap服务器
        store.connect("pop.qq.com", 995, email.getEmailAddress(), email.getEmailPwd());
        // 获得用户的邮件账号，且通过pop3协议获取的文件夹只能是inbox
        folder = store.getFolder("inbox");
        // 获取邮件UID需要先进行强制转换
        inbox = (POP3Folder) folder;
        // 设置对邮件账户的访问权限
        folder.open(Folder.READ_WRITE);
        try {
            // 得到账户信息的所有邮件信息
            Message[] messages = folder.getMessages();
            for (Message msg : messages) {
                MimeMessage mimeMsg = (MimeMessage) msg;
                // 获取邮件ID
                String uid = inbox.getUID(mimeMsg);
                // 数据库中如果已经有了该uid，则跳过该邮件，否则收取
                if (recvEmailDao.uidIsExists(email.getEmailAddress(), uid))
                    continue;
                else {
                    // 获得邮件主题
                    String subject = MimeUtility.decodeText(mimeMsg.getSubject());
                    // 获得邮件发件人
                    String from = MimeMessageUtil.getFrom(mimeMsg);
                    // InternetAddress address = new InternetAddress();
                    // 获取邮件发送时间
                    Date sendDate = mimeMsg.getSentDate();
                    // 获取邮件收取时间
                    Date recvDate = mimeMsg.getReceivedDate();
                    // 获得邮件内容
                    StringBuffer sbf = new StringBuffer(100);
                    MimeMessageUtil.getMailTextContent(mimeMsg, sbf);

                    // 将获取到的邮件信息保存到recvEmailInfo对象中
                    recvEmailInfo.setUid(uid);
                    recvEmailInfo.setEmailAddress(email.getEmailAddress());
                    recvEmailInfo.setEmailFrom(from);
                    recvEmailInfo.setEmailSubject(subject);
                    recvEmailInfo.setSendDate(sendDate);
                    recvEmailInfo.setRecvDate(recvDate);
                    recvEmailInfo.setEmailContent(sbf.toString());
                    // 将recvEmailInfo对象作为参数传给recvEmailInfoDao方法，作为存储邮件的参数
                    recvEmailDao.saveRecvEmail(recvEmailInfo);
                }
            }
        } finally {
            folder.close(false);
            store.close();
        }
    }

}
