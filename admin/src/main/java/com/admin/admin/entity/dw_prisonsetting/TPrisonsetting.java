package com.admin.admin.entity.dw_prisonsetting;


import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TPrisonsetting {

  @ApiModelProperty(value = "编号",dataType = "int")
  private int id;
  @ApiModelProperty(value = "人员编号",dataType = "String")
  private String personid;
  @ApiModelProperty(value = "设置名称",dataType = "String")
  private String settingname;
  @ApiModelProperty(value = "启用状态",dataType = "String")
  private boolean settingcheck;
  @ApiModelProperty(value = "设置时间",dataType = "String")
  private Date settingtime;
  @ApiModelProperty(value = "设置编码",dataType = "int")
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



