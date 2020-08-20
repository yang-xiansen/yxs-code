package org.yxs.test.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/hello")
    public String getName(@RequestParam String name) {
        return name;
    }


    @PostMapping("/user/{id}")
    public String get(@PathVariable String id) {
        return "1";
    }
}
