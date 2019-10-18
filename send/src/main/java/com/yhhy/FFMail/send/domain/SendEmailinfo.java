package com.yhhy.FFMail.send.domain;

import java.util.Date;

public class SendEmailinfo {
	private String emailFrom;
	private String emailPwd;
	private String emailRecipient;
	private String emailSubject;
	private Date sendDate;
	private String emailContent;
	
	public SendEmailinfo(String emailFrom, String emailPwd, String emailRecipient, String emailSubject, Date sendDate, String emailContent) {
		this.emailFrom = emailFrom;
		this.emailPwd = emailPwd;
		this.emailRecipient = emailRecipient;
		this.emailSubject = emailSubject;
		this.sendDate = sendDate;
		this.emailContent = emailContent;
	}
	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	public String getEmailRecipient() {
		return emailRecipient;
	}
	public void setEmailRecipient(String emailRecipient) {
		this.emailRecipient = emailRecipient;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	public String getEmailPwd() {
		return emailPwd;
	}
	public void setEmailPwd(String emailPwd) {
		this.emailPwd = emailPwd;
	}
	
}
