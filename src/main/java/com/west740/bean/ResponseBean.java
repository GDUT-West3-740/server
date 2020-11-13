package com.west740.bean;

/**
 * @author lucifer
 */
public class ResponseBean {

    private String status;
    private String msg;

    public ResponseBean() {
    }

    public ResponseBean(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
