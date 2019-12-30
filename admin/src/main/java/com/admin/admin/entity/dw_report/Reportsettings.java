package com.admin.admin.entity.dw_report;


import java.util.Date;

public class Reportsettings {

  private int id;
  private int type;
  private String locationstime;
  private int reportcount;

  public int getId() {
    return id;
  }

  public Reportsettings setId(int id) {
    this.id = id;
    return this;
  }

  public int getType() {
    return type;
  }

  public Reportsettings setType(int type) {
    this.type = type;
    return this;
  }

  public int getReportcount() {
    return reportcount;
  }

  public Reportsettings setReportcount(int reportcount) {
    this.reportcount = reportcount;
    return this;
  }

  public int getReporttype() {
    return reporttype;
  }

  public Reportsettings setReporttype(int reporttype) {
    this.reporttype = reporttype;
    return this;
  }

  public int getReportday() {
    return reportday;
  }

  public Reportsettings setReportday(int reportday) {
    this.reportday = reportday;
    return this;
  }

  public Date getCreattime() {
    return creattime;
  }

  public Reportsettings setCreattime(Date creattime) {
    this.creattime = creattime;
    return this;
  }

  public boolean isStatus() {
    return status;
  }

  public Reportsettings setStatus(boolean status) {
    this.status = status;
    return this;
  }

  private int reporttype;
  private int reportday;
  private Date begindate;
  private String typename;
  private Date creattime;
  private boolean status;

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






  public String getLocationstime() {
    return locationstime;
  }

  public void setLocationstime(String locationstime) {
    this.locationstime = locationstime;
  }






}
