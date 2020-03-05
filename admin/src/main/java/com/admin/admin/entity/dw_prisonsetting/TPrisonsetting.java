package com.admin.admin.entity.dw_prisonsetting;


import java.util.Date;

public class TPrisonsetting {

  private int id;
  private String personid;
  private String settingname;
  private boolean settingcheck;
  private Date settingtime;
  private int settingcode;

  public int getSettingcode() {
    return settingcode;
  }

  public TPrisonsetting setSettingcode(int settingcode) {
    this.settingcode = settingcode;
    return this;
  }

  public boolean isSettingcheck() {
    return settingcheck;
  }

  public TPrisonsetting setSettingcheck(boolean settingcheck) {
    this.settingcheck = settingcheck;
    return this;
  }

  public int getId() {
    return id;
  }

  public TPrisonsetting setId(int id) {
    this.id = id;
    return this;
  }

  public Date getSettingtime() {
    return settingtime;
  }

  public TPrisonsetting setSettingtime(Date settingtime) {
    this.settingtime = settingtime;
    return this;
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
}



