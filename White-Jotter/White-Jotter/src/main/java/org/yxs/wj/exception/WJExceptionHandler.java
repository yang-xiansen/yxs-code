package org.yxs.wj.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yxs.wj.core.Result;
import org.yxs.wj.core.ResultFactory;

/**
 * @Description: 异常处理器
 * @Author: yang-xiansen
 * @Date: 2020/08/10 10:29
 */
@ControllerAdvice
public class WJExceptionHandler {

    @ExceptionHandler(value = WJException.class)
    @ResponseBody
    public Result exceptionHandler(WJException e) {

        return ResultFactory.buildFailResult(e.getMessage());
    }
}
