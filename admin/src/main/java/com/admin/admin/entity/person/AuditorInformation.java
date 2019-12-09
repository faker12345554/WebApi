package com.admin.admin.entity.person;

import java.util.Date;

public class AuditorInformation {
    private int id;
    private String leaveorder;
    private String user_id;
    private String leavingmessage;
    private int statuscode;
    private String states;
    private String account_name;
    private Date auditordatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeaveorder() {
        return leaveorder;
    }

    public void setLeaveorder(String leaveorder) {
        this.leaveorder = leaveorder;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLeavingmessage() {
        return leavingmessage;
    }

    public void setLeavingmessage(String leavingmessage) {
        this.leavingmessage = leavingmessage;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public Date getAuditordatetime() {
        return auditordatetime;
    }

    public void setAuditordatetime(Date auditordatetime) {
        this.auditordatetime = auditordatetime;
    }
}
