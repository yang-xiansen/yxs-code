package org.yxs.cloud.core;


public class CommonResult<T> {
    private T data;
    private String msg;
    private Integer code;

    public CommonResult() {
    }

    public CommonResult(T data, String msg, Integer code) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public CommonResult(String msg, Integer code) {
        this(null, msg, code);
    }

    public CommonResult(T data) {
        this(data, "操作成功", 200);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
