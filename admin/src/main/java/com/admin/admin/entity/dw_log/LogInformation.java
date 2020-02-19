package com.admin.admin.entity.dw_log;

import java.util.Date;

public class LogInformation {

  private int id;
  private int operator;
  private String modular;

  public int getOperator() {
    return operator;
  }

  public LogInformation setOperator(int operator) {
    this.operator = operator;
    return this;
  }

  public int getId() {
    return id;
  }

  public LogInformation setId(int id) {
    this.id = id;
    return this;
  }



  private String action;
  private Date operatingtime;

  public String getSystemtype() {
    return systemtype;
  }

  public LogInformation setSystemtype(String systemtype) {
    this.systemtype = systemtype;
    return this;
  }

  private String systemtype;


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
