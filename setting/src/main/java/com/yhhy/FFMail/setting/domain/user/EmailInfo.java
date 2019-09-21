package com.yhhy.FFMail.setting.domain.user;

public class EmailInfo {
  private String emailAddress;
	private String emailPwd;
	
	public EmailInfo() {
	}
	
	public EmailInfo(String emailAddress, String emailPwd) {
    this.setUserName(emailAddress);
	  this.setSendName(emailPwd);
	}
	
	public String getUserName() {
	  return emailAddress;
	}
	
	public String getEmailPwd() {
	  return emailPwd;
	}
	public void setUserName(String emailAddress) {
	  this.emailAddress = emailAddress;
	}
	
	public void setSendName(String emailPwd) {
	  this.emailPwd = emailPwd;
	}
}
