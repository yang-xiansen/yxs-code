package org.yxs.fi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FIController {

    @GetMapping
    public String get() {
        return "首页";
    }
}
