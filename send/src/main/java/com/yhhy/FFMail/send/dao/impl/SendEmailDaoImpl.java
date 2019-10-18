package com.yhhy.FFMail.send.dao.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yhhy.FFMail.send.dao.SendEmailDao;
import com.yhhy.FFMail.send.domain.SendEmailinfo;
@Repository("SendEmailDao")
public class SendEmailDaoImpl implements SendEmailDao{
	@Autowired
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void saveSendEmail(SendEmailinfo sendInfo) {
		// TODO Auto-generated method stub
		String sql = "insert into t_send_email_info (" + "EMAIL_FROM,EMAIL_RECIPIENT,EMAIL_SUBJECT,EMAIL_SEND_DATE,EMAIL_CONTENT" + ") values (?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {sendInfo.getEmailFrom(),sendInfo.getEmailRecipient(),sendInfo.getEmailSubject(),sendInfo.getSendDate(),sendInfo.getEmailContent()});
	}
	
}
