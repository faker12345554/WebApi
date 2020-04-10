package com.admin.admin.entity.dw_case;


import java.util.Date;

public class TCaseinfo {

  private String casecategory;
  private String casename;
  private String caseno;
  private String receivedtime;
  private String handleunit;
  private String handlepeson;

  public String getMainperson() {
    return mainperson;
  }

  public TCaseinfo setMainperson(String mainperson) {
    this.mainperson = mainperson;
    return this;
  }

  public String getOperationperson() {
    return operationperson;
  }

  public TCaseinfo setOperationperson(String operationperson) {
    this.operationperson = operationperson;
    return this;
  }

  public String getFilingtime() {
    return filingtime;
  }

  public TCaseinfo setFilingtime(String filingtime) {
    this.filingtime = filingtime;
    return this;
  }

  private String personid;
  private String casetype;
  private String casedescription;
  private String mainperson;
  private String operationperson;
  private String filingtime;

  public String getReceivedtime() {
    return receivedtime;
  }

  public TCaseinfo setReceivedtime(String receivedtime) {
    this.receivedtime = receivedtime;
    return this;
  }

  public String getCasecategory() {
    return casecategory;
  }

  public void setCasecategory(String casecategory) {
    this.casecategory = casecategory;
  }


  public String getCasename() {
    return casename;
  }

  public void setCasename(String casename) {
    this.casename = casename;
  }


  public String getCaseno() {
    return caseno;
  }

  public void setCaseno(String caseno) {
    this.caseno = caseno;
  }


  public String getHandleunit() {
    return handleunit;
  }

  public void setHandleunit(String handleunit) {
    this.handleunit = handleunit;
  }


  public String getHandlepeson() {
    return handlepeson;
  }

  public void setHandlepeson(String handlepeson) {
    this.handlepeson = handlepeson;
  }


  public String getPersonid() {
    return personid;
  }

  public void setPersonid(String personid) {
    this.personid = personid;
  }


  public String getCasetype() {
    return casetype;
  }

  public void setCasetype(String casetype) {
    this.casetype = casetype;
  }


  public String getCasedescription() {
    return casedescription;
  }

  public void setCasedescription(String casedescription) {
    this.casedescription = casedescription;
  }

}
