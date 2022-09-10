package com.spring.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2021/12/12.
 */
@Service
public class HelloService {

    public String welcome(String name) {
        return name + ", 你好啊";
    }
}
