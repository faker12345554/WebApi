package com.admin.admin.entity.dw_violation;


public class Violationfens {

  private long id;
  private String code;
  private String violationname;
  private String rangefens;
  private long createperson;
  private String accountname;
  private java.sql.Timestamp createtime;

  public boolean isStatus() {
    return status;
  }

  public Violationfens setStatus(boolean status) {
    this.status = status;
    return this;
  }

  private boolean status;


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


  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }

}
