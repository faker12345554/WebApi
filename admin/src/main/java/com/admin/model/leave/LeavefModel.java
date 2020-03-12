package com.admin.model.leave;

import java.util.Date;

public class LeavefModel {
    private int Id;
    private String reason;
    private String leavedestination;
    private String starttimestamp;

    public String getLeaveorder() {
        return leaveorder;
    }

    public void setLeaveorder(String leaveorder) {
        this.leaveorder = leaveorder;
    }

    private String endtimestamp;
    private String subittimestamp;
    private String personname;
    private String states;
    private String auditordatetime;
    private String leaveorder;
    public int getId() {
        return Id;
    }

    public LeavefModel setId(int id) {
        Id = id;
        return this;
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

    public String getStarttimestamp() {
        return starttimestamp;
    }

    public void setStarttimestamp(String starttimestamp) {
        this.starttimestamp = starttimestamp;
    }

    public String getEndtimestamp() {
        return endtimestamp;
    }

    public void setEndtimestamp(String endtimestamp) {
        this.endtimestamp = endtimestamp;
    }

    public String getSubittimestamp() {
        return subittimestamp;
    }

    public void setSubittimestamp(String subittimestamp) {
        this.subittimestamp = subittimestamp;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getAuditordatetime() {
        return auditordatetime;
    }

    public void setAuditordatetime(String auditordatetime) {
        this.auditordatetime = auditordatetime;
    }


}
