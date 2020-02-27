package com.prisonapp.business.entity.dw_supervise;


public class TPrisonsetting {

  private int id;
  private String personid;
  private String settingname;
  private boolean settingcheck;
  private String  settingtime;
  private int settingcode;

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

  public boolean isSettingcheck() {
    return settingcheck;
  }

  public void setSettingcheck(boolean settingcheck) {
    this.settingcheck = settingcheck;
  }

  public String getSettingtime() {
    return settingtime;
  }

  public void setSettingtime(String settingtime) {
    this.settingtime = settingtime;
  }

  public int getSettingcode() {
    return settingcode;
  }

  public void setSettingcode(int settingcode) {
    this.settingcode = settingcode;
  }
}
