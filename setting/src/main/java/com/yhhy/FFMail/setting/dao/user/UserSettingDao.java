package com.yhhy.FFMail.setting.dao.user;

import com.yhhy.FFMail.setting.domain.user.User;

public interface UserSettingDao {

  public void saveUserInfo(User u);

  public Boolean queryUserIsExist(User u);
  
  public int checkPassword(User u);
}
