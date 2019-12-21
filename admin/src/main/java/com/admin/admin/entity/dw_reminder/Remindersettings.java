package com.admin.admin.entity.dw_reminder;


public class Remindersettings {

  private long id;
  private String code;
  private String settingname;
  private long settingday;
  private java.sql.Date settingtime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getSettingname() {
    return settingname;
  }

  public void setSettingname(String settingname) {
    this.settingname = settingname;
  }


  public long getSettingday() {
    return settingday;
  }

  public void setSettingday(long settingday) {
    this.settingday = settingday;
  }


  public java.sql.Date getSettingtime() {
    return settingtime;
  }

  public void setSettingtime(java.sql.Date settingtime) {
    this.settingtime = settingtime;
  }

}
