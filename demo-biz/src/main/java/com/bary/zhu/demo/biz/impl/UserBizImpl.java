package com.bary.zhu.demo.biz.impl;

import com.bary.zhu.demo.biz.UserBiz;
import com.bary.zhu.demo.service.domain.User;
import com.bary.zhu.demo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: TODO
 * User: zb132
 * Date: 2018/9/4 17:40
 */
@Service
public class UserBizImpl implements UserBiz {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void creat(User user) {
        userMapper.create(user);
    }
}
