package com.spring.demo.controller;

import com.spring.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjinping
 * @time 2016/12/15
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String check(
            @RequestParam(value = "name", required = true) String name) {
        return helloService.welcome(name);
    }
}
