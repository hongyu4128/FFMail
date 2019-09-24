package com.yhhy.FFMailBasic.basic.common.bo;

public class JSONNumberCastException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -2922753168734753155L;

    private String jsonText;

    public JSONNumberCastException(String originalJsonText) {
        super();
        this.jsonText = originalJsonText;
    }

    public JSONNumberCastException() {
        super();
    }

    public String getJsonText() {
        return jsonText;
    }

    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }
}
