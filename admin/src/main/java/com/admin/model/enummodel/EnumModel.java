package com.admin.model.enummodel;


import java.util.Date;

public class EnumModel {

  private int enumid;
  private String typecode;
  private String typename;
  private String enumname;
  private String status;
  private Date createtime;


  public int getEnumid() {
    return enumid;
  }

  public void setEnumid(int enumid) {
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
