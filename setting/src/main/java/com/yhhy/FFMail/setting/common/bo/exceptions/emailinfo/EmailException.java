package com.yhhy.FFMail.setting.common.bo.exceptions.emailinfo;

public class EmailException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -4998736163544485086L;

    private String msg;

    public EmailException() {
        super();
    }

    public EmailException(String msg) {
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
