package com.yhhy.FFMail.send.service;

import com.yhhy.FFMail.send.domain.SendEmailinfo;

public interface SendEmailService {
	public void sendEmail(SendEmailinfo sendInfo) throws Exception;
}
