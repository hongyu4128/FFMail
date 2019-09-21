package com.yhhy.FFMail.setting.common.bo.exceptions.user;

public class UserWrongPasswordException extends UserException {

  /**
   * 
   */
  private static final long serialVersionUID = -8673418514564047073L;

  public UserWrongPasswordException() {
    super("密码错误,请重试");
  }
}
