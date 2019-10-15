package com.yhhy.FFMail.setting.dao.user;

import com.yhhy.FFMailBasic.basic.domain.user.email.UserEmailBasicInfo;

public interface UserEmailBasicInfoDao {

    public void saveUserEmail(UserEmailBasicInfo email);

    public Boolean queryEmailIsExist(UserEmailBasicInfo email);
}
