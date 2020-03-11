package com.admin.model.log;

import com.admin.model.person.PersonModel;

public class LogModel {
    private int id;
    private int operator;
    private String modular;
    private String action;
    private String operatingTime;
    private String UserName;

    public String getUserName() {
        return UserName;
    }

    public LogModel setUserName(String userName) {
        UserName = userName;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOperatingTime() {
        return operatingTime;
    }

    public void setOperatingTime(String operatingTime) {
        this.operatingTime = operatingTime;
    }
}
