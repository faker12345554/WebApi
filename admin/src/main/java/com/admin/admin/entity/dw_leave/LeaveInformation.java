package com.admin.admin.entity.dw_leave;

import java.util.Date;

public class LeaveInformation {
    private String leaveorder;
    private String reason;
    private String leavedestination;
    private Date starttimestamp;
    private Date endtimestamp;
    private Date subittimestamp;
    private String personid;
    private String personname;

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

    private int statuscode;
    private String states;

    public String getLeaveorder() {
        return leaveorder;
    }

    public void setLeaveorder(String leaveorder) {
        this.leaveorder = leaveorder;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getLeavedestination() {
        return leavedestination;
    }

    public void setLeavedestination(String leavedestination) {
        this.leavedestination = leavedestination;
    }

    public Date getStarttimestamp() {
        return starttimestamp;
    }

    public void setStarttimestamp(Date starttimestamp) {
        this.starttimestamp = starttimestamp;
    }

    public Date getEndtimestamp() {
        return endtimestamp;
    }

    public void setEndtimestamp(Date endtimestamp) {
        this.endtimestamp = endtimestamp;
    }

    public Date getSubittimestamp() {
        return subittimestamp;
    }


    public void setSubittimestamp(Date subittimestamp) {
        this.subittimestamp = subittimestamp;
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

}
