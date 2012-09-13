package com.xjgzinfo.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TQjgc {
    private BigDecimal id;

    private String qjr;

    private Date qjsj;

    private BigDecimal qjts;

    private String spyj;

    private String spr;

    private Date spsj;

    private Object proid;

    private String taskname;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getQjr() {
        return qjr;
    }

    public void setQjr(String qjr) {
        this.qjr = qjr == null ? null : qjr.trim();
    }

    public Date getQjsj() {
        return qjsj;
    }

    public void setQjsj(Date qjsj) {
        this.qjsj = qjsj;
    }

    public BigDecimal getQjts() {
        return qjts;
    }

    public void setQjts(BigDecimal qjts) {
        this.qjts = qjts;
    }

    public String getSpyj() {
        return spyj;
    }

    public void setSpyj(String spyj) {
        this.spyj = spyj == null ? null : spyj.trim();
    }

    public String getSpr() {
        return spr;
    }

    public void setSpr(String spr) {
        this.spr = spr == null ? null : spr.trim();
    }

    public Date getSpsj() {
        return spsj;
    }

    public void setSpsj(Date spsj) {
        this.spsj = spsj;
    }

    public Object getProid() {
        return proid;
    }

    public void setProid(Object proid) {
        this.proid = proid;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname == null ? null : taskname.trim();
    }
}