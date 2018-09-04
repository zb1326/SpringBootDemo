package com.bary.zhu.demo.service.mapper;

import com.bary.zhu.demo.service.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description: TODO
 * User: zb132
 * Date: 2018/9/4 15:32
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(name) values(#{name})")
    void create(User user);
}
