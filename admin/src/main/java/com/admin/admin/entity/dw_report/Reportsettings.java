package com.admin.admin.entity.dw_report;


public class Reportsettings {

  private long id;
  private long type;
  private String locationstime;
  private long reportcount;
  private long reporttype;
  private long reportday;
  private java.sql.Timestamp begindate;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
  }


  public String getLocationstime() {
    return locationstime;
  }

  public void setLocationstime(String locationstime) {
    this.locationstime = locationstime;
  }


  public long getReportcount() {
    return reportcount;
  }

  public void setReportcount(long reportcount) {
    this.reportcount = reportcount;
  }


  public long getReporttype() {
    return reporttype;
  }

  public void setReporttype(long reporttype) {
    this.reporttype = reporttype;
  }


  public long getReportday() {
    return reportday;
  }

  public void setReportday(long reportday) {
    this.reportday = reportday;
  }


  public java.sql.Timestamp getBegindate() {
    return begindate;
  }

  public void setBegindate(java.sql.Timestamp begindate) {
    this.begindate = begindate;
  }

}
