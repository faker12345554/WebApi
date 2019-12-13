package com.admin.model.biosignature;


import java.util.Date;

public class BiosignatureReturnModel {

  private String person_name;
  private String filepath;
  //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createtime;


  public String getPersonName() {
    return person_name;
  }

  public void setPersonName(String person_name) {
    this.person_name = person_name;
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
