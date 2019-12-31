package com.admin.admin.entity.dw_summons;


import java.util.Date;

public class TSummons {

  private long id;
  private String summontime;
  private String content;
  private Date reporttime;
  private String personid;
  private String personname;


  public long getId() {
    return id;
  }

  public String getSummontime() {
    return summontime;
  }

  public TSummons setSummontime(String summontime) {
    this.summontime = summontime;
    return this;
  }

  public void setId(long id) {
    this.id = id;
  }


  public Date getReporttime() {
    return reporttime;
  }

  public TSummons setReporttime(Date reporttime) {
    this.reporttime = reporttime;
    return this;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }




  public String getPersonid() {
    return personid;
  }

  public void setPersonid(String personid) {
    this.personid = personid;
  }


  public String getPersonname() {
    return personname;
  }

  public void setPersonname(String personname) {
    this.personname = personname;
  }

}
