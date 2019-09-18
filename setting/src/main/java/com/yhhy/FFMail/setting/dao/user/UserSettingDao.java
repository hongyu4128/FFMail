package com.yhhy.FFMail.setting.dao.user;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yhhy.FFMail.setting.domain.user.User;

public class UserSettingDao {
    @Resource
    private JdbcTemplate jdbcTemplate;

    public Integer saveUserInfo(User u) {
        
        
        return 0;
    }
}
