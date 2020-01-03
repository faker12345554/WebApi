package com.admin.admin.entity.dw_alarm;


import java.util.Date;

public class Alarmsettings {

  private int id;
  private String code;
  private String alarmname;

  public Date getCreatetime() {
    return createtime;
  }

  public Alarmsettings setCreatetime(Date createtime) {
    this.createtime = createtime;
    return this;
  }

  public boolean isStatus() {
    return status;
  }

  public Alarmsettings setStatus(boolean status) {
    this.status = status;
    return this;
  }

  private double alarmcount;
  private boolean continuous;
  private Date createtime;
  private boolean status;

  public boolean isContinuous() {
    return continuous;
  }

  public int getId() {
    return id;
  }

  public Alarmsettings setId(int id) {
    this.id = id;
    return this;
  }

  public Alarmsettings setContinuous(boolean continuous) {
    this.continuous = continuous;
    return this;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getAlarmname() {
    return alarmname;
  }

  public void setAlarmname(String alarmname) {
    this.alarmname = alarmname;
  }


  public double getAlarmcount() {
    return alarmcount;
  }

  public void setAlarmcount(double alarmcount) {
    this.alarmcount = alarmcount;
  }



}
