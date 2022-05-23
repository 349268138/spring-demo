package com.spring.demo.dao.mapper;

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
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findByIdTest() {
        User user = userMapper.findById(2L);
        Assert.assertNotNull(user);
        Assert.assertEquals("jack", user.getName());
    }

    @Test
    public void findTest() {
        User userExample = new User();
        userExample.setName("jack");
        List<User> userList = userMapper.find(userExample);
        Assert.assertTrue(userList.size() == 1);
    }

    @Test
    public void addTest() {
        User userExample = new User();
        userExample.setName("lucy");
        userExample.setSex(true);
        Integer affectLine = userMapper.add(userExample);
        Assert.assertTrue(affectLine == 1);
    }

    @Test
    public void updateByIdTest() {
        User userExample = new User();
        userExample.setSex(false);
        Integer affectLine = userMapper.update(3L, userExample);
        Assert.assertTrue(affectLine == 1);
    }

    @Test
    public void deleteByIdTest() {
        Integer affectLine = userMapper.delete(3L);
        Assert.assertTrue(affectLine == 1);
    }
}
