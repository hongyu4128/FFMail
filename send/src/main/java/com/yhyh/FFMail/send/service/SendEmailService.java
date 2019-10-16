package com.yhyh.FFMail.send.service;

import com.yhhy.FFMailBasic.basic.domain.user.email.UserEmailBasicInfo;
import com.yhyh.FFMail.send.domain.SendEmail;

public interface SendEmailService {
	public void sendEmail(SendEmail sendInfo) throws Exception;
}
