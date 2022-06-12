package com.spring.demo.service;

import com.spring.demo.dao.mapper.UserMapper;
import com.spring.demo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by wangjinping on 2017/9/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class HelloServiceTest {
    @Autowired
    private HelloService helloService;

    @Test
    public void findByIdTest() {
        Assert.assertEquals("王谨平, 你好啊", helloService.welcome("王谨平"));
    }
}
