package com.prisonapp.business.entity.dw_supervise;

public class SuperviseModel {

  public int id;
  public String leaveorder;
  public String reason;
  public String leavedestination;
  public String personid;
  public String personname;
  public String statuscode;
  public String states;
  public java.sql.Timestamp starttimestamp;
  public java.sql.Timestamp endtimestamp;
  public java.sql.Timestamp subittimestamp;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStatuscode() {
    return statuscode;
  }

  public void setStatuscode(String statuscode) {
    this.statuscode = statuscode;
  }

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


  public String getStates() {
    return states;
  }

  public void setStates(String states) {
    this.states = states;
  }


  public java.sql.Timestamp getStarttimestamp() {
    return starttimestamp;
  }

  public void setStarttimestamp(java.sql.Timestamp starttimestamp) {
    this.starttimestamp = starttimestamp;
  }


  public java.sql.Timestamp getEndtimestamp() {
    return endtimestamp;
  }

  public void setEndtimestamp(java.sql.Timestamp endtimestamp) {
    this.endtimestamp = endtimestamp;
  }


  public java.sql.Timestamp getSubittimestamp() {
    return subittimestamp;
  }

  public void setSubittimestamp(java.sql.Timestamp subittimestamp) {
    this.subittimestamp = subittimestamp;
  }

}
