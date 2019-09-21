package com.yhhy.FFMail.setting.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhhy.FFMail.setting.common.bo.exceptions.user.UserNotExistsException;
import com.yhhy.FFMail.setting.common.bo.exceptions.user.UserWrongPasswordException;
import com.yhhy.FFMail.setting.dao.user.UserSettingDao;
import com.yhhy.FFMail.setting.domain.user.User;
import com.yhhy.FFMail.setting.service.user.UserSettingService;

@Service("UserSettingService")
public class UserSettingServiceImpl implements UserSettingService {

  @Autowired
  private UserSettingDao userSettingDao;

  @Override
  public void saveUser(User u) throws UserNotExistsException {
    Boolean isExist = this.userIsExist(u);
    if (!isExist) {
      throw new UserNotExistsException();
    }
    userSettingDao.saveUserInfo(u);
  }

  private Boolean userIsExist(User u) {
    return userSettingDao.queryUserIsExist(u);
  }

  @Override
  public String login(User u) throws UserNotExistsException {
    Boolean isExist = this.userIsExist(u);
    if (!isExist) {
      throw new UserNotExistsException();
    }
    try {
      if (this.checkPassword(u)) {
        return "/index";
      }
    } catch (UserWrongPasswordException e) {
      return "/login";
    }
    return "/login";
  }

  private Boolean checkPassword(User u) throws UserWrongPasswordException {
    if (userSettingDao.checkPassword(u) == 0) {
      throw new UserWrongPasswordException();
    }
    return true;
  }
}
