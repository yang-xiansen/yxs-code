package org.yxs.devtool.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevtoolController {

    @GetMapping("/")
    String index() {
        return "hello spring boot";
    }

}
