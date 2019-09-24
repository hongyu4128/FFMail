package com.yhhy.FFMailBasic.basic.common.bo;

public class JSONStringCastException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 8000437461425243712L;

    private String jsonText;

    public JSONStringCastException(String originalJsonText) {
        super();
        this.setJsonText(originalJsonText);
    }

    public JSONStringCastException() {
        super();
    }

    public String getJsonText() {
        return jsonText;
    }

    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }
}
