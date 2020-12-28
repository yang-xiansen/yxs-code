package org.yxs.cors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CorsController {

    @GetMapping("index")
    public String index() {
        return "index";
    }

//    @CrossOrigin(value = "*")   //注解方式解决跨域
    @GetMapping("hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
