package org.yxs.exception.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.yxs.exception.exce.MyException;

@Controller
public class ExceptionController {

    @GetMapping
    public void get() {
        throw new MyException("运行错误");
    }
}
