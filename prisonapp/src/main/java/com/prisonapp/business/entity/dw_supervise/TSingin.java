package com.prisonapp.business.entity.dw_supervise;

import java.util.Date;

public class TSingin {

  private int id;
  private String personid;
  private int  type;
  private String face;
  private String audio;
  private int result;
  private String filepath;
  private Date createtime;
  private String reporttype;
  private String reportstatus;
  private String address;
  private String activityarea;
  private double durationtime;


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

  public int getResult() {
    return result;
  }

  public void setResult(int result) {
    this.result = result;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public String getPersonid() {
    return personid;
  }

  public void setPersonid(String personid) {
    this.personid = personid;
  }


  public String getFace() {
    return face;
  }

  public void setFace(String face) {
    this.face = face;
  }


  public String getAudio() {
    return audio;
  }

  public void setAudio(String audio) {
    this.audio = audio;
  }



  public String getFilepath() {
    return filepath;
  }

  public void setFilepath(String filepath) {
    this.filepath = filepath;
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

}
