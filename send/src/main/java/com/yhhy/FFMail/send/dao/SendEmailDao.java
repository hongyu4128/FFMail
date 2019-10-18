package com.yhhy.FFMail.send.dao;

import com.yhhy.FFMail.send.domain.SendEmailinfo;

public interface SendEmailDao {
	public void saveSendEmail(SendEmailinfo sendInfo);
}
