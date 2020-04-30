package com.admin.admin.entity.dw_auditor;

import java.util.Date;

public class AuditorInformation {
    private String leaveorder;
    private String leavingmessage;
    private int statuscode;
    private String states;
    private String accountname;
    private Date auditordatetime;
    private int Userid;

    public int getUserid() {
        return Userid;
    }

    public AuditorInformation setUserid(int userid) {
        Userid = userid;
        return this;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
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
