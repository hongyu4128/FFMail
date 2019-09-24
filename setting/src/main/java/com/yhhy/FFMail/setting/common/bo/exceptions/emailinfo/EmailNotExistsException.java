package com.yhhy.FFMail.setting.common.bo.exceptions.emailinfo;

public class EmailNotExistsException extends EmailException {
    /**
       * 
       */
    private static final long serialVersionUID = 1L;

    /**
     * 用于判断邮箱是否已经存在
     */
    public EmailNotExistsException(Boolean exists) {
        super();
        String msg1 = "邮箱已经存在,请直接查看";
        String msg2 = "邮箱不存在,请先保存";

        if (exists) {
            this.setMsg(msg1);
        } else {
            this.setMsg(msg2);
        }
    }
}
