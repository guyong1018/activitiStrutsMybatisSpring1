package com.xjgzinfo.vo;

import java.math.BigDecimal;
/**
 * 表单对象类
 * @author Administrator
 *
 */
public class TForm {
    private BigDecimal id;

    private Object actId;

    private String url;

    private Object processdefid;

    private Object actName;

    private Object processdefname;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Object getActId() {
        return actId;
    }

    public void setActId(Object actId) {
        this.actId = actId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Object getProcessdefid() {
        return processdefid;
    }

    public void setProcessdefid(Object processdefid) {
        this.processdefid = processdefid;
    }

    public Object getActName() {
        return actName;
    }

    public void setActName(Object actName) {
        this.actName = actName;
    }

    public Object getProcessdefname() {
        return processdefname;
    }

    public void setProcessdefname(Object processdefname) {
        this.processdefname = processdefname;
    }
}