package com.spring.demo.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author wangjinping
 * @time 2016/12/15
 */
@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @RequestMapping("/check")
    @ResponseBody
    public String check() {
        return "T";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String check(
            @RequestParam(value = "name", required = true) String name) {
        return name + ", 你好啊";
    }
}
