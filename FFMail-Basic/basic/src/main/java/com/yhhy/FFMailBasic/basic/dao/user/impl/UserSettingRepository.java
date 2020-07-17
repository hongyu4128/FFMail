package com.yhhy.FFMailBasic.basic.dao.user.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yhhy.FFMailBasic.basic.dao.user.UserSettingDao;
import com.yhhy.FFMailBasic.basic.domain.user.User;
import com.yhhy.FFMailBasic.basic.domain.user.User.LoginType;

@Repository("UserSettingDao")
public class UserSettingRepository implements UserSettingDao {
    private Logger log = LoggerFactory.getLogger(UserSettingRepository.class);

    @Autowired
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    // 判断数据库中是否有同样的数据,有就返回true,没有返回false
    public Boolean queryUserIsExist(User u) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (u.getLoginType().equals(LoginType.ACCOUNT)) {
            // 账号登陆
            String sql = "select USER_ID from T_SET_USER_INFO t where t.account = ?";
            list = jdbcTemplate.queryForList(sql, new Object[] { u.getAccount() });
        } else {
            // 手机登陆
            String sql = "select USER_ID from T_SET_USER_INFO t where t.TELEPHONE = ?";
            list = jdbcTemplate.queryForList(sql, new Object[] { u.getTelephone() });
        }
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

    /**
     * 登录时验证密码正确性的函数
     */
    @Override
    public int checkPassword(User u) {
        if (u.getLoginType().equals(LoginType.ACCOUNT)) {
            // 账号登陆
            String sql = "select USER_ID from T_SET_USER_INFO t where t.account = ? and t.PASSWORD = ?";
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
                    new Object[] { u.getUserName(), u.getPassword() });
            list.forEach(item -> {
                String t = String.valueOf(item.get("USER_ID"));
                log.debug(t);
            });
            if (list.isEmpty() == true)
                return 0;
            else {
                u.setToken("abcde-12345-abcde-123456");
                return 1;
            }
        } else {
            return 0;
        }
    }
}
