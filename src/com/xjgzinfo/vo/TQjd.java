package com.xjgzinfo.vo;

import java.math.BigDecimal;

public class TQjd {
    private BigDecimal qjid;

    private String qjr;

    private BigDecimal qjts;

    private String qjly;

    private Object proid;

    public BigDecimal getQjid() {
        return qjid;
    }

    public void setQjid(BigDecimal qjid) {
        this.qjid = qjid;
    }

    public String getQjr() {
        return qjr;
    }

    public void setQjr(String qjr) {
        this.qjr = qjr == null ? null : qjr.trim();
    }

    public BigDecimal getQjts() {
        return qjts;
    }

    public void setQjts(BigDecimal qjts) {
        this.qjts = qjts;
    }

    public String getQjly() {
        return qjly;
    }

    public void setQjly(String qjly) {
        this.qjly = qjly == null ? null : qjly.trim();
    }

    public Object getProid() {
        return proid;
    }

    public void setProid(Object proid) {
        this.proid = proid;
    }
}