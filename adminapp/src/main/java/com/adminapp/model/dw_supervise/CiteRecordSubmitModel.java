package com.adminapp.model.dw_supervise;

import java.util.Date;

public class CiteRecordSubmitModel {
    private int id;
    private String personid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String personname;
    private Date summonsTime;

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public Date getSummonsTime() {
        return summonsTime;
    }

    public void setSummonsTime(Date summonsTime) {
        this.summonsTime = summonsTime;
    }
}
