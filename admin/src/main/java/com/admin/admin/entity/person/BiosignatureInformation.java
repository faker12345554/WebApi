package com.admin.admin.entity.person;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BiosignatureInformation {

  private String person_id;
  private String person_name;
  private long type;
  private String filepath;

  //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createtime;


  public String getPersonId() {
    return person_id;
  }

  public void setPersonId(String person_id) {
    this.person_id = person_id;
  }


  public String getPersonName() {
    return person_name;
  }

  public void setPersonName(String person_name) {
    this.person_name = person_name;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
    this.type = type;
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
