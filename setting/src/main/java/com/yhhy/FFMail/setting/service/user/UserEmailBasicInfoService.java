package com.yhhy.FFMail.setting.service.user;

import com.yhhy.FFMail.setting.domain.user.UserEmailBasicInfo;

public interface UserEmailBasicInfoService {
  /**
   * 用于保存用户的邮箱信息
   */
  public void saveEmailInfo(UserEmailBasicInfo email);

}
