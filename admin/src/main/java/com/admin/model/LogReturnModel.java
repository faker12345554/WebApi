package com.admin.model;

import java.util.Date;

public class LogReturnModel {

  private String modular;
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
