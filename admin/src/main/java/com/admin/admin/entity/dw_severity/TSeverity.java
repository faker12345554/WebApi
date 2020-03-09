package com.sample;


public class TSeverity {

  private String personid;
  private String stype;
  private String datetimes;
  private String severity;

  public int getSignbool() {
    return signbool;
  }

  public TSeverity setSignbool(int signbool) {
    this.signbool = signbool;
    return this;
  }

  private int signbool;


  public String getPersonid() {
    return personid;
  }

  public void setPersonid(String personid) {
    this.personid = personid;
  }


  public String getStype() {
    return stype;
  }

  public void setStype(String stype) {
    this.stype = stype;
  }


  public String getDatetimes() {
    return datetimes;
  }

  public void setDatetimes(String datetimes) {
    this.datetimes = datetimes;
  }


  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }

}
