package com.yhhy.FFMailBasic.basic.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhhy.FFMailBasic.basic.bo.exceptions.user.UserNotExistsException;
import com.yhhy.FFMailBasic.basic.bo.exceptions.user.UserWrongPasswordException;
import com.yhhy.FFMailBasic.basic.dao.user.UserSettingDao;
import com.yhhy.FFMailBasic.basic.domain.user.User;
import com.yhhy.FFMailBasic.basic.service.user.UserSettingService;

@Service("UserSettingService")
public class UserSettingServiceImpl implements UserSettingService {

  @Autowired
  private UserSettingDao userSettingDao;

  @Override
  public void register(User u) throws UserNotExistsException {
    Boolean isExist = this.userIsExist(u);
    if (isExist) {
      throw new UserNotExistsException(isExist);
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
      throw new UserNotExistsException(isExist);
    }
    try {
      if (this.checkPassword(u)) {
        return u.getToken();
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
