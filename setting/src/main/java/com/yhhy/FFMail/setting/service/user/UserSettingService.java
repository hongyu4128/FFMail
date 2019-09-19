package com.yhhy.FFMail.setting.service.user;

import com.yhhy.FFMail.setting.domain.user.User;

public interface UserSettingService {
  /**
   * 保存user数据
   * 
   * @param u
   */
  public void saveUser(User u);
}
