package com.spring.demo.dao.mapper;

import com.spring.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by wangjinping on 2017/9/25.
 */
public interface UserMapper {
    public User findById(@Param("id") Long id);

    public List<User> find(@Param("userExample") User userExample);

    public Integer add(@Param("user") User user);

    public Integer addBatch(@Param("list") List<User> userList);

    public Integer update(@Param("id") Long id, @Param("user") User user);

    public Integer delete(@Param("id") Long id);


    public List<User> resultMapTest(@Param("userExample") User userExample);
}