package com.xjgzinfo.organizational;

import java.math.BigDecimal;

public class UserVo {
    private BigDecimal userid;

    private String name;

    private String username;

    private String password;

    private BigDecimal groupid;

    public BigDecimal getUserid() {
        return userid;
    }

    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public BigDecimal getGroupid() {
        return groupid;
    }

    public void setGroupid(BigDecimal groupid) {
        this.groupid = groupid;
    }
}