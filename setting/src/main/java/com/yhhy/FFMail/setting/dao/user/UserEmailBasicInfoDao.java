package com.yhhy.FFMail.setting.dao.user;

import com.yhhy.FFMail.setting.domain.user.UserEmailBasicInfo;

public interface UserEmailBasicInfoDao {

    public void saveUserEmail(UserEmailBasicInfo email);

    public Boolean queryEmailIsExist(UserEmailBasicInfo email);
}
