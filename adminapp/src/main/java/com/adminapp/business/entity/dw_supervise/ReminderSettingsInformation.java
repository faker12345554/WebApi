package com.adminapp.business.entity.dw_supervise;


import java.util.Date;

public class ReminderSettingsInformation {

  private String code;
  private String settingname;
  private String settingday;
  private String settingtime;
  private int id;
  private boolean status;
  private Date createtime;


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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }
}
