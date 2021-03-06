package com.yhhy.FFMail.setting.dao.user.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yhhy.FFMail.setting.dao.user.UserEmailBasicInfoDao;
import com.yhhy.FFMailBasic.basic.domain.user.email.UserEmailBasicInfo;

@Repository("UserEmailBasicInfoDao")
public class UserEmailBasicInfoRepository implements UserEmailBasicInfoDao {

    @Autowired
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveUserEmail(UserEmailBasicInfo email) {
        // TODO Auto-generated method stub
        String sql = "insert into T_SET_EMAIL_INFO (" + "USER_ID, RECV_SERVICETYPE,EMAIL_ADDRESS,"
                + "EMAIL_PASSWORD, EMAIL_RECVSERVICE," + "EMAIL_RECVPORT, EMAIL_SENDSERVICE,"
                + "EMAIL_SENDPORT) values (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                new Object[] { email.getUserId(), email.getRecvServiceType(), email.getEmailAddress(),
                        email.getEmailPwd(), email.getRecvService(), email.getRecvPort(), email.getSendService(),
                        email.getSendPort() });
    }

    /**
     * 用来验证某个user_id是否已经存在该邮箱地址,如果没有,返回false,有则返回true
     */
    @Override
    public Boolean queryEmailIsExist(UserEmailBasicInfo email) {
        // TODO Auto-generated method stub
        String sql = "select * from T_SET_EMAIL_INFO t where t.USER_ID = ? and t.EMAIL_ADDRESS = ?";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
                new Object[] { email.getUserId(), email.getEmailAddress() });
        if (list.isEmpty() == true)
            return false;
        else
            return true;
    }

}
