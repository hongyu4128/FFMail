package com.yhhy.FFMail.setting.common.bo;

/**
 * 标志型方法 - 通用返回值
 * 
 * @author hongyu
 *
 */
public class FlagResultMessage {
  private Boolean result;
  private String msg;

  public FlagResultMessage(Boolean result, String msg) {
    this.result = result;
    this.msg = msg;
  }

  public static FlagResultMessage isTrue(String msg) {
    return new FlagResultMessage(true, msg);
  }

  public static FlagResultMessage isFalse(String msg) {
    return new FlagResultMessage(false, msg);
  }

  public Boolean getResult() {
    return result;
  }

  public void setResult(Boolean result) {
    this.result = result;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
