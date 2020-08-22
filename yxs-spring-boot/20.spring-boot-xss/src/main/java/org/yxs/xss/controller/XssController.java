package org.yxs.xss.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XssController {

    @GetMapping
    public String get(String path) {
        return path;
    }
}
