package com.yhhy.FFMail.setting.dao.user.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.yhhy.FFMail.setting.dao.user.RecvEmailInfoDao;
import com.yhhy.FFMail.setting.domain.user.RecvEmailInfo;

@Repository("RecvEmailInfoDao")
public class RecvEmailInfoRepository implements RecvEmailInfoDao{
	@Autowired
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveRecvEmailInfo(RecvEmailInfo recvemail) {
		// TODO Auto-generated method stub
		String sql = "insert into T_RECV_EMAIL_INFO (" + "EMAIL_ADDRESS," + "EMAIL_FROM, EMAIL_SUBJECT,"
		        + "EMAIL_CONTENT) values (?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { recvemail.getEmailAddress(),recvemail.getEmailFrom(),recvemail.getEmailSubject(),recvemail.getEmailContent() });
	}

}
