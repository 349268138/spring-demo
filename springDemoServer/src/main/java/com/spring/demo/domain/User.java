package com.spring.demo.domain;
import java.io.Serializable;

/**
 * Created by wangjinping on 2017/7/7.
 */
public class User implements Serializable {
    private Long id;
    private String name;
    private Boolean sex;

    public User(){

    }

    public User(Long id, String name, Boolean sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

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

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", sex=").append(sex);
        sb.append('}');
        return sb.toString();
    }
}
