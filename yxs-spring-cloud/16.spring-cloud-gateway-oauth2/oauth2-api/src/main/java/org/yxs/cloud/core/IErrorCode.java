package org.yxs.cloud.core;


/**
* @Description: 封装API的错误码
* @Author: y-xs
* @Date: 2020/10/12 15:07
*/
public interface IErrorCode {
    long getCode();

    String getMessage();
}
