package com.prisonapp.business.entity.dw_supervise;

public class TRemindersettings {

  private String code;
  private String settingname;
  private String settingday;
  private String settingtime;
  private long id;
  private boolean status;
  private java.sql.Timestamp createtime;


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


  public String getSettingday() {
    return settingday;
  }

  public void setSettingday(String settingday) {
    this.settingday = settingday;
  }


  public String getSettingtime() {
    return settingtime;
  }

  public void setSettingtime(String settingtime) {
    this.settingtime = settingtime;
  }


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }

}
