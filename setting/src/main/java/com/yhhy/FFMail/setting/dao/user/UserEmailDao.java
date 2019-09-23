package com.yhhy.FFMail.setting.dao.user;

import com.yhhy.FFMail.setting.domain.user.EmailInfo;

public interface UserEmailDao {
  
  public void saveEmail(EmailInfo email);
  
  public Boolean queryEmailIsExist(EmailInfo email);
}
