package com.yhhy.FFMail.setting.service.user;

import java.io.IOException;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

import com.yhhy.FFMail.setting.domain.user.UserEmailBasicInfo;

public interface RecvEmailService {
	
	public void recvEmail(UserEmailBasicInfo email) throws AuthenticationFailedException,NoSuchProviderException,MessagingException,IOException,Exception;

}
