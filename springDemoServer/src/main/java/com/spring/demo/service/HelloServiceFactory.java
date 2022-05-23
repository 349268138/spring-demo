package com.spring.demo.service;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2021/12/13.
 */
public class HelloServiceFactory {
    public HelloService createHelloService(){
        return new HelloService();
    }
}
