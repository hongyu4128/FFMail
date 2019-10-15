package com.yhhy.FFMail.recv.service;

import java.io.IOException;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;

import com.yhhy.FFMailBasic.basic.domain.user.email.UserEmailBasicInfo;

public interface RecvEmailService {

    public void recvEmail(UserEmailBasicInfo email)
            throws AuthenticationFailedException, NoSuchProviderException, MessagingException, IOException, Exception;

}
