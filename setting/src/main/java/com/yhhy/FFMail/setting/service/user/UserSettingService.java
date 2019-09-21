package com.yhhy.FFMail.setting.service.user;

import com.yhhy.FFMail.setting.domain.user.User;

public interface UserSettingService {
  /**
   * 对用户数据进行判断,包括用户是否已经存在(用户名+密码),用户名需要控制在10位,手机号需要是11位,密码需要大于6位 保存user数据
   * 
   * @param u
   */

  public void saveUser(User u);
  
  /**
   * 用于登录
   * @param u
   * @return
   */
  public String login(User u);
}
