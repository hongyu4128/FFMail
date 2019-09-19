package com.yhhy.FFMail.setting.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhhy.FFMail.setting.dao.user.UserSettingDao;
import com.yhhy.FFMail.setting.domain.user.User;
import com.yhhy.FFMail.setting.service.user.UserSettingService;

@Service("UserSettingService")
public class UserSettingServiceImpl implements UserSettingService {

  @Autowired
  private UserSettingDao userSettingDao;

  @Override
  public void saveUser(User u) {
    userSettingDao.saveUserInfo(u);
  }

}
