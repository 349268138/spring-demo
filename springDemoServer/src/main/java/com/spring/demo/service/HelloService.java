package com.spring.demo.service;

/**
 * @author wangjinping
 * @Description
 * @CreateDateon 2021/12/12.
 */
public class HelloService {
    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    private String envName;

    public HelloService() {
        System.out.println("HelloService create");
    }

    public String welcome(String name) {
        return name + ", 你好啊, " + envName;
    }
}
