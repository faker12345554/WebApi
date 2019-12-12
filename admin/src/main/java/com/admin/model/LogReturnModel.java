package com.admin.model;

import java.util.Date;

public class LogReturnModel {

  //private long id;
//  private long operator;
  private String modular;
  private String action;
  private Date operatingtime;


  //public long getId() {
    //return id;
  //}

  //public void setId(long id) {
    //this.id = id;
  //}


//  public long getOperator() {
//    return operator;
//  }
//
//  public void setOperator(long operator) {
//    this.operator = operator;
//  }


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
