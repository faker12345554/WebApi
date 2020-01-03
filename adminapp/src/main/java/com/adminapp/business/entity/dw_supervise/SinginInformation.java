package com.adminapp.business.entity.dw_supervise;


import java.util.Date;

public class SinginInformation {

  private int id;
  private String personid;
  private int type;
  private int result;
  private String filepath;
  private Date createtime;
  private String reporttype;
  private String reportstatus;
  private String address;
  private String activityarea;
  private double durationtime;
  private String typename;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPersonid() {
    return personid;
  }

  public void setPersonid(String personid) {
    this.personid = personid;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getResult() {
    return result;
  }

  public void setResult(int result) {
    this.result = result;
  }

  public String getFilepath() {
    return filepath;
  }

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public String getReporttype() {
    return reporttype;
  }

  public void setReporttype(String reporttype) {
    this.reporttype = reporttype;
  }

  public String getReportstatus() {
    return reportstatus;
  }

  public void setReportstatus(String reportstatus) {
    this.reportstatus = reportstatus;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getActivityarea() {
    return activityarea;
  }

  public void setActivityarea(String activityarea) {
    this.activityarea = activityarea;
  }

  public double getDurationtime() {
    return durationtime;
  }

  public void setDurationtime(double durationtime) {
    this.durationtime = durationtime;
  }

  public String getTypename() {
    return typename;
  }

  public void setTypename(String typename) {
    this.typename = typename;
  }
}
