package org.yxs.exception.core;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;

    private T data;

    private String msg;


    private Result(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }


    public static <T> Result<T> success(T data) {
        return new Result<T>(200, data, null);
    }

    public static <T> Result<T> fail(Integer code, String msg) {
        return new Result<T>(code, null, msg);
    }


}
