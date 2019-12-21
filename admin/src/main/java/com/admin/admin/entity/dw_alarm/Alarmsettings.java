package com.admin.admin.entity.dw_alarm;


public class Alarmsettings {

  private long id;
  private String code;
  private String alarmname;
  private double alarmcount;
  private String continuous;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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


  public String getContinuous() {
    return continuous;
  }

  public void setContinuous(String continuous) {
    this.continuous = continuous;
  }

}
