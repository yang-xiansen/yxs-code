package org.yxs.exception.exce;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.yxs.exception.core.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 异常处理器
 * @Author: yang-xiansen
 * @Date: 2020/08/22 10:29
 */
@ControllerAdvice
public class MyExceptionHandler {

//    @ExceptionHandler(value = MyException.class)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  //指定异常处理方法返回的HTTP状态码
//    public Map<String, Object> exceptionHandler(MyException e) {
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("message", e.getMessage());
//        return map;
//    }


    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result exceptionHandler(MyException e) {
        return Result.fail(500, e.getMessage());
    }

}
