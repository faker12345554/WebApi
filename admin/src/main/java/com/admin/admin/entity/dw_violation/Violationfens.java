package com.admin.admin.entity.dw_violation;


import java.util.Date;

public class Violationfens {

  private long id;
  private String code;
  private String violationname;
  private String rangefens;
  private long createperson;
  private String accountname;
  private Date createtime;
  private boolean status;

  public boolean isStatus() {
    return status;
  }

  public Violationfens setStatus(boolean status) {
    this.status = status;
    return this;
  }



  public Date getCreatetime() {
    return createtime;
  }

  public Violationfens setCreatetime(Date createtime) {
    this.createtime = createtime;
    return this;
  }

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


  public String getViolationname() {
    return violationname;
  }

  public void setViolationname(String violationname) {
    this.violationname = violationname;
  }


  public String getRangefens() {
    return rangefens;
  }

  public void setRangefens(String rangefens) {
    this.rangefens = rangefens;
  }


  public long getCreateperson() {
    return createperson;
  }

  public void setCreateperson(long createperson) {
    this.createperson = createperson;
  }


  public String getAccountname() {
    return accountname;
  }

  public void setAccountname(String accountname) {
    this.accountname = accountname;
  }



}
