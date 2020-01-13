package com.admin.model.leave;

import java.util.Date;

public class LeavefModel {
    private int Id;
    private String reason;
    private String leavedestination;
    private Date starttimestamp;

    public String getLeaveorder() {
        return leaveorder;
    }

    public void setLeaveorder(String leaveorder) {
        this.leaveorder = leaveorder;
    }

    private Date endtimestamp;
    private Date subittimestamp;
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
