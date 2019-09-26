package com.yhhy.FFMail.setting.domain.user;

import javax.mail.Address;

public class RecvEmailInfo {
	private String emailAddress;
	private Address[] emailFrom;
	private String emailSubject;
	private String emailContent;
	
	public RecvEmailInfo() {
		
	}
	
	public RecvEmailInfo(String emailAddress, Address[] emailFrom, String emailSubject,String emailContent) {
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
	public Address[] getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(Address[] from) {
		this.emailFrom = from;
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
