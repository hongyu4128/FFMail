package com.yhhy.FFMail.setting.dao.user.impl;

import java.util.List;
import java.util.Map;

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
  // 判断数据库中是否有同样的数据,有就返回true,没有返回false
  public Boolean queryUserIsExist(User u) {
    String sql = "select USER_ID from T_SET_USER_INFO t where t.USER_NAME = ? or t.TELEPHONE = ?";
    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[] { u.getUserName(), u.getTelephone() });
    if (list.isEmpty() == true)
      return false;
    else
      return true;
  }

  @Override
  public void saveUserInfo(User u) {
    String sql = "insert into T_SET_USER_INFO (USER_NAME, TELEPHONE, PASSWORD) values (?, ?, ?)";
    jdbcTemplate.update(sql, new Object[] { u.getUserName(), u.getTelephone(), u.getPassword() });
  }

  @Override
  public int checkPassword(User u) {
    // TODO: 这个sql不太对 明天再改!
    String sql = "select USER_ID from T_SET_USER_INFO t where (t.USER_NAME = ? or t.TELEPHONE = ? and t.PASSWORD = ?)";
    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, new Object[] { u.getUserName(), u.getTelephone(), u.getPassword() });
    return 0;
  }

}
