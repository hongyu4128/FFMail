package com.yhhy.FFMail.recv.domain;

import java.util.Date;


public class RecvEmail {
	private String emailAddress;
	private String emailFrom;
	private String emailSubject;
	private String emailContent;
	private Date sendDate;
	private Date recvDate;
	private String uid;
	
	public RecvEmail(String emailAddress, String emailFrom, String emailSubject, String emailContent, Date sendDate, Date recvDate, String uid) {
		this.emailAddress = emailAddress;
		this.emailFrom = emailFrom;
		this.emailSubject = emailSubject;
		this.emailContent = emailContent;
		this.sendDate = sendDate;
		this.recvDate = recvDate;
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid2) {
		this.uid = uid2;
	}

	public RecvEmail() {

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
