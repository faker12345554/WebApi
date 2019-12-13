package com.admin.admin.entity.person;

import java.util.Date;

public class AuditorInformation {
    private int id;
    private String leaveorder;
    private String userid;
    private String leavingmessage;
    private int statuscode;
    private String states;
    private String accountname;
    private Date auditordatetime;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

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


    public Date getAuditordatetime() {
        return auditordatetime;
    }

    public void setAuditordatetime(Date auditordatetime) {
        this.auditordatetime = auditordatetime;
    }
}
