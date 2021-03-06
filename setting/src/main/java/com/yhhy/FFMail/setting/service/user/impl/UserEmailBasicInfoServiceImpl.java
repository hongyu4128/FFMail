package com.yhhy.FFMail.setting.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhhy.FFMail.setting.common.bo.exceptions.emailinfo.EmailNotExistsException;
import com.yhhy.FFMail.setting.dao.user.UserEmailBasicInfoDao;
import com.yhhy.FFMail.setting.service.user.UserEmailBasicInfoService;
import com.yhhy.FFMailBasic.basic.domain.user.email.UserEmailBasicInfo;

@Service("UserEmailBasicInfoService")
public class UserEmailBasicInfoServiceImpl implements UserEmailBasicInfoService {

  @Autowired
  private UserEmailBasicInfoDao userEmailDao;

  @Override
  public void saveEmailInfo(UserEmailBasicInfo email) throws EmailNotExistsException {
    Boolean isExist = this.emailExist(email);
    if (isExist) {
      throw new EmailNotExistsException(isExist);
    }
    userEmailDao.saveUserEmail(email);
  }

  private Boolean emailExist(UserEmailBasicInfo email) {
    return userEmailDao.queryEmailIsExist(email);
  }

  /**
   * 邮箱校验
   * 
   */
 
}
