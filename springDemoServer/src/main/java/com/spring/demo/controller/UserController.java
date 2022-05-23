package com.spring.demo.controller;

import com.spring.demo.dao.mapper.UserMapper;
import com.spring.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjinping
 * @time 2016/12/15
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public String findById(
            @RequestParam(value = "id", required = true) Long id) {
        User user = userMapper.findById(id);
        if (user == null) {
            return "id=" + id + " is not exsit";
        } else {
            return user.toString();
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public String add(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "sex", required = true) Boolean sex) {
        User student = new User();
        student.setName(name);
        student.setSex(sex);
        return userMapper.add(student).toString();
    }

    @RequestMapping(value = "/updateById", method = RequestMethod.GET)
    @ResponseBody
    public String updateById(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "sex", required = false) Boolean sex) {
        if(name == null && sex == null) {
            return "0";
        }

        User student = new User();
        student.setName(name);
        student.setSex(sex);
        return userMapper.update(id, student) + "";
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.GET)
    @ResponseBody
    public String deleteById(
            @RequestParam(value = "id", required = true) Long id) {
        return userMapper.delete(id) + "";
    }
}
