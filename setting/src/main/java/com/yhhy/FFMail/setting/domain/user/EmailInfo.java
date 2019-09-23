package com.yhhy.FFMail.setting.domain.user;

/**
 * 用户邮箱信息
 * 
 * @author Administrator
 *
 */

public class EmailInfo {
  private int userId;
  private String emailAddress;
  private String emailPwd;
  private String recvService;
  private String recvPort;
  private String sendService;
  private String sendPort;

  public EmailInfo() {
  }

  public EmailInfo(int userId, String emailAddress, String emailPwd, String recvService, String recvPort,
      String sendService, String sendPort) {
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

  public String getRecvPort() {
    return recvPort;
  }

  public void setRecvPort(String recvPort) {
    this.recvPort = recvPort;
  }

  public String getSendService() {
    return sendService;
  }

  public void setSendService(String sendService) {
    this.sendService = sendService;
  }

  public String getSendPort() {
    return sendPort;
  }

  public void setSendPort(String sendPort) {
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
