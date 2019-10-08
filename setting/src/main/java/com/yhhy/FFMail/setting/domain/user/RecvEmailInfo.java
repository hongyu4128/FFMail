package com.yhhy.FFMail.setting.domain.user;

import java.util.Date;


public class RecvEmailInfo {
	private String emailAddress;
	private String emailFrom;
	private String emailSubject;
	private String emailContent;
	private Date sendDate;
	private Date recvDate;
	private long uid;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public RecvEmailInfo() {

	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getRecvDate() {
		return recvDate;
	}

	public void setRecvDate(Date recvDate) {
		this.recvDate = recvDate;
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
