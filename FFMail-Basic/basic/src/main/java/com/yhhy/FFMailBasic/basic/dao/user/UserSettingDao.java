package com.yhhy.FFMailBasic.basic.dao.user;

import com.yhhy.FFMailBasic.basic.domain.user.User;

public interface UserSettingDao {

    public void saveUserInfo(User u);

    public Boolean queryUserIsExist(User u);

    public int checkPassword(User u);
}
