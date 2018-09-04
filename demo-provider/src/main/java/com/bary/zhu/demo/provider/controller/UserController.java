package com.bary.zhu.demo.provider.controller;

import com.bary.zhu.demo.biz.UserBiz;
import com.bary.zhu.demo.service.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: TODO
 * User: zb132
 * Date: 2018/9/4 17:42
 */
@Controller
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/test")
    public void createUser(){
        User user = new User();
        user.setName("test");
        userBiz.creat(user);
    }
}
