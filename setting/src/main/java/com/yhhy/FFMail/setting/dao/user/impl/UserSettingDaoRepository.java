package com.yhhy.FFMail.setting.dao.user.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yhhy.FFMail.setting.dao.user.UserSettingDao;
import com.yhhy.FFMail.setting.domain.user.User;

@Repository("UserSettingDao")
public class UserSettingDaoRepository implements UserSettingDao {

  @Autowired
  @Resource
  private JdbcTemplate jdbcTemplate;

  @Override
  public void saveUserInfo(User u) {
    String sql = "insert into T_SET_USER_INFO (USER_NAME, PASSWORD) values (?, ?)";
    jdbcTemplate.update(sql, new Object[] { u.getUsername(), u.getPassword() });
  }

}
