package com.adminapp.business.entity.dw_supervise;


public class SummonsInformation {

  private long id;
  private java.sql.Timestamp summontime;
  private String content;
  private java.sql.Timestamp reporttime;
  private String personid;
  private String personname;

  public String getPersonname() {
    return personname;
  }

  public void setPersonname(String personname) {
    this.personname = personname;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public java.sql.Timestamp getSummontime() {
    return summontime;
  }

  public void setSummontime(java.sql.Timestamp summontime) {
    this.summontime = summontime;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public java.sql.Timestamp getReporttime() {
    return reporttime;
  }

  public void setReporttime(java.sql.Timestamp reporttime) {
    this.reporttime = reporttime;
  }


  public String getPersonid() {
    return personid;
  }

  public void setPersonid(String personid) {
    this.personid = personid;
  }

}
