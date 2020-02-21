package com.admin.model.enummodel;


import java.sql.Timestamp;
import java.util.Date;

public class EnumModel {

  private int enumid;       //字典编号
  private String typecode;  //字典类型
  private String typename;  //字典名称
  private String enumname;  //备注
  private String status;    //状态
  private java.sql.Timestamp createtime;  //创建时间


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

  public void setStatus(boolean status) {
    String Status="";
    if(status){
      Status="正常";
    }
    else{
      Status="停用";
    }
    this.status = Status;
  }

  public Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Timestamp createtime) {
    this.createtime = createtime;
  }
}
