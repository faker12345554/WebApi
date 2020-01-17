package com.admin.admin.entity.dw_summons;


import java.util.Date;

public class TSummons {

  private long id;
  private String summontime;
  private String content;
  private Date reporttime;
  private String personid;
  private String personname;
  private String severity;
  private String summonsbegintime;
  private String summonsendtime;

  public TSummons setReporttime(Date reporttime) {
    this.reporttime = reporttime;
    return this;
  }

  public Date getReporttime() {
    return reporttime;
  }

  public String getSummonsbegintime() {
    return summonsbegintime;
  }

  public TSummons setSummonsbegintime(String summonsbegintime) {
    this.summonsbegintime = summonsbegintime;
    return this;
  }

  public String getSummonsendtime() {
    return summonsendtime;
  }

  public TSummons setSummonsendtime(String summonsendtime) {
    this.summonsendtime = summonsendtime;
    return this;
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

  public void setSummontime(String summontime) {
    this.summontime = summontime;
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


  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }




}
