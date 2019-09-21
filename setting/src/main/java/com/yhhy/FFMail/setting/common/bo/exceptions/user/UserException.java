package com.yhhy.FFMail.setting.common.bo.exceptions.user;

public class UserException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 709399578957493270L;

  private String msg;

  public UserException() {
    super();
  }

  public UserException(String msg) {
    super();
    this.setMsg(msg);
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

}
