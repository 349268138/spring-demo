package com.meituan.pay.finsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhoutianji on 17/7/17.
 */
@RestController
public class MonitorController {
    @RequestMapping(value = "monitor/alive")
    @ResponseBody
    public String alive() {
        return "OK";
    }
}
