package com.admin.admin.entity.dw_reminder;


import java.util.Date;

public class Remindersettings {

  private int id;
  private String code;

  public int getId() {
    return id;
  }

  public Remindersettings setId(int id) {
    this.id = id;
    return this;
  }

  public String getSettingday() {
    return settingday;
  }

  public Remindersettings setSettingday(String settingday) {
    this.settingday = settingday;
    return this;
  }

  public String getSettingtime() {
    return settingtime;
  }

  public Remindersettings setSettingtime(String settingtime) {
    this.settingtime = settingtime;
    return this;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public Remindersettings setCreatetime(Date createtime) {
    this.createtime = createtime;
    return this;
  }

  public boolean isStatus() {
    return status;
  }

  public Remindersettings setStatus(boolean status) {
    this.status = status;
    return this;
  }

  private String settingname;
  private String settingday;
  private String settingtime;
  private Date createtime;
  private boolean status;




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



}
