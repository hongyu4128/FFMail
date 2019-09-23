package com.yhhy.FFMail.setting.service.user.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhhy.FFMail.setting.common.bo.exceptions.user.EmailNotExistsException;
import com.yhhy.FFMail.setting.dao.user.UserEmailDao;
import com.yhhy.FFMail.setting.domain.user.EmailInfo;
import com.yhhy.FFMail.setting.service.user.UserEmailService;

@Service("UserEmailService")
public class UserEmailServiceImpl implements UserEmailService {

  @Autowired
  private UserEmailDao userEmailDao;

  @Override
  public void saveEmailInfo(EmailInfo email) throws EmailNotExistsException {
    Boolean isExist = this.emailExist(email);
    if (isExist) {
      throw new EmailNotExistsException(isExist);
    }
    Boolean b = this.connectMail(email);
    if (!b) {
      throw new EmailNotExistsException(b);
    }
    userEmailDao.saveEmail(email);
  }

  private Boolean emailExist(EmailInfo email) {
    return userEmailDao.queryEmailIsExist(email);
  }

  /**
   * 邮箱校验
   * 
   */
  private Boolean connectMail(EmailInfo email) {
    try {
      Properties props = new Properties();
      props.setProperty(email.getRecvService(), "true");
      Session session = Session.getInstance(props, new Authenticator() {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
          // 邮件服务器在外网
          return new PasswordAuthentication(email.getEmailAddress(), email.getEmailPwd());
        }
      });
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
