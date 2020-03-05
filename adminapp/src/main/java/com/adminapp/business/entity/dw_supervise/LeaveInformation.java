package com.adminapp.business.entity.dw_supervise;


public class LeaveInformation {

  private long id;
  private String leaveorder;
  private String reason;
  private String leavedestination;
  private String personid;
  private String personname;
  private String statuscode;
  private String states;
  private java.sql.Timestamp starttimestamp;
  private java.sql.Timestamp endtimestamp;
  private java.sql.Timestamp subittimestamp;
  private String recording;
  private String provincecode;
  private String provincename;
  private String citycode;
  private String cityname;
  private String areacode;
  private String areaname;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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


  public String getStatuscode() {
    return statuscode;
  }

  public void setStatuscode(String statuscode) {
    this.statuscode = statuscode;
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


  public String getRecording() {
    return recording;
  }

  public void setRecording(String recording) {
    this.recording = recording;
  }


  public String getProvincecode() {
    return provincecode;
  }

  public void setProvincecode(String provincecode) {
    this.provincecode = provincecode;
  }


  public String getProvincename() {
    return provincename;
  }

  public void setProvincename(String provincename) {
    this.provincename = provincename;
  }


  public String getCitycode() {
    return citycode;
  }

  public void setCitycode(String citycode) {
    this.citycode = citycode;
  }


  public String getCityname() {
    return cityname;
  }

  public void setCityname(String cityname) {
    this.cityname = cityname;
  }


  public String getAreacode() {
    return areacode;
  }

  public void setAreacode(String areacode) {
    this.areacode = areacode;
  }


  public String getAreaname() {
    return areaname;
  }

  public void setAreaname(String areaname) {
    this.areaname = areaname;
  }

}
