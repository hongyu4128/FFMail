package com.yhhy.FFMail.setting.dao.user;

import com.yhhy.FFMail.setting.domain.user.EmailInfo;

public interface UserEmailDao {
  
  public void SaveEmail(EmailInfo e);
  
  public Boolean EmailIsExist(EmailInfo e);
}
