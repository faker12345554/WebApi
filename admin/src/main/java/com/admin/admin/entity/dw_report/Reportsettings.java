package com.admin.admin.entity.dw_report;


import java.util.Date;

public class Reportsettings {

  private long id;
  private long type;
  private String locationstime;
  private long reportcount;
  private long reporttype;
  private long reportday;
  private Date begindate;
  private String typename;

  public String getTypename() {
    return typename;
  }

  public Reportsettings setTypename(String typename) {
    this.typename = typename;
    return this;
  }

  public Date getBegindate() {
    return begindate;
  }

  public Reportsettings setBegindate(Date begindate) {
    this.begindate = begindate;
    return this;
  }




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



}
