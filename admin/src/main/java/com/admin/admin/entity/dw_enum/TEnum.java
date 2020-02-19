package com.admin.admin.entity.dw_enum;


import java.util.Date;

public class TEnum {

  private long enumid;
  private String typecode;
  private String typename;
  private String enumcode;
  private String enumname;
  private String status;
  private Date createtime;


  public long getEnumid() {
    return enumid;
  }

  public void setEnumid(long enumid) {
    this.enumid = enumid;
  }


  public String getTypecode() {
    return typecode;
  }

  public void setTypecode(String typecode) {
    this.typecode = typecode;
  }


  public String getTypename() {
    return typename;
  }

  public void setTypename(String typename) {
    this.typename = typename;
  }


  public String getEnumcode() {
    return enumcode;
  }

  public void setEnumcode(String enumcode) {
    this.enumcode = enumcode;
  }


  public String getEnumname() {
    return enumname;
  }

  public void setEnumname(String enumname) {
    this.enumname = enumname;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }
}
