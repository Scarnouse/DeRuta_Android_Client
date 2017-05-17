package com.iesvdc.lolo.deruta.model;

/**
 * Created by lolo on 15/05/17.
 */

public class RequestError {

    int code;
    String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RequestError{" + code +"}:" + message;
    }
}
