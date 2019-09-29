package com.yhhy.FFMail.setting.domain.user;

import javax.mail.Address;

public class RecvEmailInfo {
	private String emailAddress;
	private String emailFrom;
	private String emailSubject;
	private String emailContent;

	public RecvEmailInfo() {

	}

	public RecvEmailInfo(String emailAddress, String emailFrom, String emailSubject, String emailContent) {
		this.emailAddress = emailAddress;
		this.emailFrom = emailFrom;
		this.emailSubject = emailSubject;
		this.emailContent = emailContent;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String address) {
		this.emailFrom = address;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailContent() {
		return emailContent;
	}

	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}

}
