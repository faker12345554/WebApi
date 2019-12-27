package com.prisonapp.business.entity.dw_enum;

import java.util.Date;

public class AreaAddressInfo {

  private int addressinfoId;
  private String darentid;
  private String code;
  private String parentcode;
  private String name;
  private String daima;
  private String level;
  private String pycode;
  private String wbcode;
  private String remark;
  private String creater;
  private Date createtime;
  private int status;

  public String getDarentid() {
    return darentid;
  }

  public void setDarentid(String darentid) {
    this.darentid = darentid;
  }


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  public String getParentcode() {
    return parentcode;
  }

  public void setParentcode(String parentcode) {
    this.parentcode = parentcode;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDaima() {
    return daima;
  }

  public void setDaima(String daima) {
    this.daima = daima;
  }


  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }


  public String getPycode() {
    return pycode;
  }

  public void setPycode(String pycode) {
    this.pycode = pycode;
  }


  public String getWbcode() {
    return wbcode;
  }

  public void setWbcode(String wbcode) {
    this.wbcode = wbcode;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getCreater() {
    return creater;
  }

  public void setCreater(String creater) {
    this.creater = creater;
  }

  public int getAddressinfoId() {
    return addressinfoId;
  }

  public void setAddressinfoId(int addressinfoId) {
    this.addressinfoId = addressinfoId;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
