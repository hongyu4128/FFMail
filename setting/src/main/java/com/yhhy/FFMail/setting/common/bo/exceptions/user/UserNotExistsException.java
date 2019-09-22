package com.yhhy.FFMail.setting.common.bo.exceptions.user;

public class UserNotExistsException extends UserException {

  /**
   * 
   */
  private static final long serialVersionUID = -2066781468300463328L;

  public UserNotExistsException(Boolean exists) {
    super();
    String msg1 = "用户已经存在,请直接登录";
    String msg2 = "用户不存在,请先注册";
    
    if (exists) {
      this.setMsg(msg1);
    } else {
      this.setMsg(msg2);

    }
  }
}
