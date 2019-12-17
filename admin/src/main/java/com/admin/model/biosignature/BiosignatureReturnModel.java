package com.admin.model.biosignature;


import java.util.Date;

public class BiosignatureReturnModel {

  private String personname;
  private String filepath;
  //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createtime;

  public String getPersonname() {
    return personname;
  }

  public void setPersonname(String personname) {
    this.personname = personname;
  }

  public String getFilepath() {
    return filepath;
  }

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }


  public Date getCreatetime() {
    return createtime;
  }

  public void setCreatetime(Date createtime) {
    this.createtime = createtime;
  }


}
