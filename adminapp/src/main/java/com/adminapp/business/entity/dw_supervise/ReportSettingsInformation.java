package com.adminapp.business.entity.dw_supervise;


import java.sql.Timestamp;

public class ReportSettingsInformation {

  private int id;
  private int type;
  private String locationstime;
  private int reportcount;
  private int reporttype;
  private int reportday;
  private java.sql.Timestamp begindate;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getLocationstime() {
    return locationstime;
  }

  public void setLocationstime(String locationstime) {
    this.locationstime = locationstime;
  }

  public int getReportcount() {
    return reportcount;
  }

  public void setReportcount(int reportcount) {
    this.reportcount = reportcount;
  }

  public int getReporttype() {
    return reporttype;
  }

  public void setReporttype(int reporttype) {
    this.reporttype = reporttype;
  }

  public int getReportday() {
    return reportday;
  }

  public void setReportday(int reportday) {
    this.reportday = reportday;
  }

  public Timestamp getBegindate() {
    return begindate;
  }

  public void setBegindate(Timestamp begindate) {
    this.begindate = begindate;
  }
}
