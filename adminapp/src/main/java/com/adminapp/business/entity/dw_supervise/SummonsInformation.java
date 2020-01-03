package com.adminapp.business.entity.dw_supervise;


import java.util.Date;

public class SummonsInformation {

  private long id;
  private String summontime;
  private String content;
  private String reporttime;
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


  public String getSummontime() {
    return summontime;
  }

  public void setSummontime(Date summontime) {
    String summonTime=String.valueOf(summontime.getTime());
    this.summontime = summonTime;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getReporttime() {
    return reporttime;
  }

  public void setReporttime(Date reporttime) {
    String reprotTime=String.valueOf(reporttime.getTime());
    this.reporttime = reprotTime;
  }


  public String getPersonid() {
    return personid;
  }

  public void setPersonid(String personid) {
    this.personid = personid;
  }

}
