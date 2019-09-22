package com.yhhy.FFMail.setting.service.user.impl;

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
  public String saveEmailInfo(EmailInfo e) throws EmailNotExistsException{
    Boolean isExist = this.EmailExist(e);
    if (isExist) {
      throw new EmailNotExistsException(isExist);
    }
      userEmailDao.SaveEmail(e);
      return null;   
  }

  private Boolean EmailExist(EmailInfo e) {
    // TODO Auto-generated method stub
    userEmailDao.EmailIsExist(e);
    return null;
  }

 

}
