package com.adminapp.business.entity.dw_supervise;


import java.util.Date;

public class PrisonSettingInformation {

  private int id;
  private String personid;
  private String settingname;
  private String settingcheck;
  private Date settingtime;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getPersonid() {
    return personid;
  }

  public void setPersonid(String personid) {
    this.personid = personid;
  }


  public String getSettingname() {
    return settingname;
  }

  public void setSettingname(String settingname) {
    this.settingname = settingname;
  }


  public String getSettingcheck() {
    return settingcheck;
  }

  public void setSettingcheck(String settingcheck) {
    this.settingcheck = settingcheck;
  }

  public Date getSettingtime() {
    return settingtime;
  }

  public void setSettingtime(Date settingtime) {
    this.settingtime = settingtime;
  }
}
