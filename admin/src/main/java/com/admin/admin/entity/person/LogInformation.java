package com.admin.admin.entity.person;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;

public class LogInformation {

  private int operator;
  private String modular;
  private String action;
  private Date operatingtime;

  public int getOperator() {
    return operator;
  }

  public LogInformation setOperator(int operator) {
    this.operator = operator;
    return this;
  }

  public String getModular() {
    return modular;
  }

  public void setModular(String modular) {
    this.modular = modular;
  }


  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }


  public Date getOperatingtime() {
    return operatingtime;
  }

  public void setOperatingtime(Date operatingtime) {
    this.operatingtime = operatingtime;
  }

}
