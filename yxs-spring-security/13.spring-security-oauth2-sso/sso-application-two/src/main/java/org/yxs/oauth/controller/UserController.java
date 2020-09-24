package org.yxs.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @GetMapping("user1")
    public Principal user(Principal principal) {
        return principal;
    }
}
