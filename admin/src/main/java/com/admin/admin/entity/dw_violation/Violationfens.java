package com.admin.admin.entity.dw_violation;


import java.util.Date;

public class Violationfens {

  private int id;
  private String code;
  private String violationname;

  public String getViolationname() {
    return violationname;
  }

  public Violationfens setViolationname(String violationname) {
    this.violationname = violationname;
    return this;
  }


  public int getRangefens() {
    return rangefens;
  }

  public Violationfens setRangefens(int rangefens) {
    this.rangefens = rangefens;
    return this;
  }

  public int getCreateperson() {
    return createperson;
  }

  public Violationfens setCreateperson(int createperson) {
    this.createperson = createperson;
    return this;
  }

  public String getAccountname() {
    return accountname;
  }

  public Violationfens setAccountname(String accountname) {
    this.accountname = accountname;
    return this;
  }

  private int rangefens;
  private int createperson;
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


  public int getId() {
    return id;
  }

  public Violationfens setId(int id) {
    this.id = id;
    return this;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public Violationfens setCreatetime(Date createtime) {
    this.createtime = createtime;
    return this;
  }



  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }





}
