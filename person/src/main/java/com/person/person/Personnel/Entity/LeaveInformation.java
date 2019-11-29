package com.person.person.Personnel.Entity;

import java.util.Date;

public class LeaveInformation {
    private int id;
    private String leaveorder;
    private String reason;
    private String leavedestination;
    private Date starttimestamp;
    private Date endtimestamp;
    private Date subittimestamp;
    private int person_id;
    private String person_name;
    private int statuscode;
    private String states;

    public String getLeaveorder() {
        return leaveorder;
    }

    public void setLeaveorder(String leaveorder) {
        this.leaveorder = leaveorder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
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
