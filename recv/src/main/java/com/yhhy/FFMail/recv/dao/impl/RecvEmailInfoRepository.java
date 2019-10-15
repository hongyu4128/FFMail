package com.yhhy.FFMail.recv.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yhhy.FFMail.recv.dao.RecvEmailDao;
import com.yhhy.FFMail.recv.domain.RecvEmail;

@Repository("RecvEmailInfoDao")
public class RecvEmailInfoRepository implements RecvEmailDao{
	@Autowired
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void saveRecvEmail(RecvEmail recvemail) {
		// TODO Auto-generated method stub
		String sql = "insert into T_RECV_EMAIL_INFO (" + "EMAIL_UID,EMAIL_ADDRESS," + "EMAIL_FROM, EMAIL_SUBJECT,"
				+ "EMAIL_SEND_TIME,EMAIL_RECV_TIME,"
				+ "EMAIL_CONTENT) values (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { recvemail.getUid(),recvemail.getEmailAddress(),recvemail.getEmailFrom(),recvemail.getEmailSubject(),recvemail.getSendDate(),recvemail.getRecvDate(),recvemail.getEmailContent() });
	}

	/**
	 * 判断数据库中该邮箱是否已经收取过此uid对应的邮件，如果没有收取过返回返回false，反之返回true
	 */
	@Override
	public boolean uidIsExists(String emailAddress,String uid) {
		// TODO Auto-generated method stub
		String sql = "select EMAIL_UID from T_RECV_EMAIL_INFO t where t.EMAIL_ADDRESS= ? and t.EMAIL_UID = ?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql,
				new Object[] { emailAddress, uid });
		if (list.isEmpty() == true)
			return false;
		else
			return true;
	}
}
