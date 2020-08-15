package org.yxs.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yxs.config.propertiey.BlogProperties;
import org.yxs.config.propertiey.BlogProperties1;
import org.yxs.config.propertiey.YxsProperties;

@RestController
public class BlogController {

    @Autowired
    private BlogProperties blogProperties;

    @Autowired
    private BlogProperties1 blogProperties1;

    @Autowired
    private YxsProperties yxsProperties;

    @GetMapping("/")
    public String index() {
//        return blogProperties.getName() + " -------> " + blogProperties.getTitle() + blogProperties1.getWholeTitle();
        return blogProperties1.getName() + " <-------> " + blogProperties1.getTitle() + "<----->" + blogProperties1.getWholeTitle();
    }

    @GetMapping("/yxs")
    public String indexYxs() {
//        return blogProperties.getName() + " -------> " + blogProperties.getTitle() + blogProperties1.getWholeTitle();
        return yxsProperties.getName() + " -------> " + yxsProperties.getAge();
    }

}
