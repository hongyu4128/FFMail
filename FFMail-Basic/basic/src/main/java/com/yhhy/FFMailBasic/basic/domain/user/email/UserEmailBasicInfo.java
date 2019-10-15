package com.yhhy.FFMailBasic.basic.domain.user.email;

/**
 * 用户邮箱基本信息
 * 
 * @author Administrator
 *
 */

public class UserEmailBasicInfo {
    private int userId;
    private String recvServiceType;
    private String emailAddress;
    private String emailPwd;
    private String recvService;
    public String getRecvServiceType() {
		return recvServiceType;
	}

	public void setRecvServiceType(String recvServiceType) {
		this.recvServiceType = recvServiceType;
	}

	private int recvPort;
    private String sendService;
    private int sendPort;

    public UserEmailBasicInfo() {
    }

    public UserEmailBasicInfo(int userId, String emailAddress, String emailPwd, String recvService, int recvPort,
            String sendService, int sendPort) {
        this.setUserId(userId);
        this.setEmailAddress(emailAddress);
        this.setEmailPwd(emailPwd);
        this.setRecvService(recvService);
        this.setRecvPort(recvPort);
        this.setRecvService(recvService);
        this.setRecvPort(recvPort);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRecvService() {
        return recvService;
    }

    public void setRecvService(String recvService) {
        this.recvService = recvService;
    }

    public int getRecvPort() {
        return recvPort;
    }

    public void setRecvPort(int recvPort) {
        this.recvPort = recvPort;
    }

    public String getSendService() {
        return sendService;
    }

    public void setSendService(String sendService) {
        this.sendService = sendService;
    }

    public int getSendPort() {
        return sendPort;
    }

    public void setSendPort(int sendPort) {
        this.sendPort = sendPort;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setEmailPwd(String emailPwd) {
        this.emailPwd = emailPwd;
    }

    public String getEmailPwd() {
        return emailPwd;
    }

}
