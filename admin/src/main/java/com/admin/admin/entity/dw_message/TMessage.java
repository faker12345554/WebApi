package com.admin.admin.entity.dw_message;


import java.util.Date;

public class TMessage {

  private long id;
  private int modular;
  private String content;
  private String personid;

  public boolean isReadmessage() {
    return readmessage;
  }

  public TMessage setReadmessage(boolean readmessage) {
    this.readmessage = readmessage;
    return this;
  }

  private String modularname;
  private Date messagetime;
  private boolean readmessage;
  private long detailtype;
  private String detailtypename;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public int getModular() {
    return modular;
  }

  public void setModular(int modular) {
    this.modular = modular;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }


  public String getPersonid() {
    return personid;
  }

  public void setPersonid(String personid) {
    this.personid = personid;
  }


  public String getModularname() {
    return modularname;
  }

  public void setModularname(String modularname) {
    this.modularname = modularname;
  }

  public Date getMessagetime() {
    return messagetime;
  }

  public TMessage setMessagetime(Date messagetime) {
    this.messagetime = messagetime;
    return this;
  }



  public long getDetailtype() {
    return detailtype;
  }

  public void setDetailtype(long detailtype) {
    this.detailtype = detailtype;
  }


  public String getDetailtypename() {
    return detailtypename;
  }

  public void setDetailtypename(String detailtypename) {
    this.detailtypename = detailtypename;
  }

}
