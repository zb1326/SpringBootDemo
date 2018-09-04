package com.bary.zhu.demo.service.domain;

import java.util.Date;

/**
 * Description: TODO
 * User: zb132
 * Date: 2018/9/4 15:30
 */
public class User {
    private Long id;

    private String name;

    private Date create_time;

    private Date update_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
