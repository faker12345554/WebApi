package com.admin.admin.entity.dw_log;

import java.util.Date;

public class LogInformation {

  private int id;
  private String operator;
  private String modular;

  public int getId() {
    return id;
  }

  public LogInformation setId(int id) {
    this.id = id;
    return this;
  }

  public String getOperator() {
    return operator;
  }

  public LogInformation setOperator(String operator) {
    this.operator = operator;
    return this;
  }

  private String action;
  private Date operatingtime;


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
