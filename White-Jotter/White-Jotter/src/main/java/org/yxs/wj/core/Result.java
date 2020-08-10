package org.yxs.wj.core;

public class Result<T> {
    //响应码
    private int code;

    private String message;


    private T result;

    public Result(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;


    }

    public Result(int code) {
        this.code = code;
    }

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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
