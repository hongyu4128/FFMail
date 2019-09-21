package com.yhhy.FFMail.setting.common.bo.exceptions.user;

public class UserNotExistsException extends UserException {

  /**
   * 
   */
  private static final long serialVersionUID = -2066781468300463328L;

  public UserNotExistsException() {
    super("用户已经存在,请直接登录");
  }

}