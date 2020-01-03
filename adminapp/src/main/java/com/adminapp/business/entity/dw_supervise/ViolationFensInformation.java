package com.adminapp.business.entity.dw_supervise;

import java.util.Date;

public class ViolationFensInformation {

  private int id;
  private String code;
  private String violationname;
  private int rangefens;
  private int createperson;
  private String accountname;
  private Date createtime;
  private String status;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCreateperson() {
    return createperson;
  }

  public void setCreateperson(int createperson) {
    this.createperson = createperson;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getViolationname() {
    return violationname;
  }

  public void setViolationname(String violationname) {
    this.violationname = violationname;
  }

  public int getRangefens() {
    return rangefens;
  }

  public void setRangefens(int rangefens) {
    this.rangefens = rangefens;
  }

  public String getAccountname() {
    return accountname;
  }

  public void setAccountname(String accountname) {
    this.accountname = accountname;
  }



  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
