package com.spring.demo.service;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2021/12/12.
 */
public class HelloService {
    public HelloService() {
        System.out.println("HelloService create");
    }

    public String welcome(String name) {
        return name + ", 你好啊";
    }
}
