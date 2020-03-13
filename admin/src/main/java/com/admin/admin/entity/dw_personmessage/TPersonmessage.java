package com.admin.admin.entity.dw_personmessage;


public class TPersonmessage {

  private long id;
  private long modular;
  private String content;
  private String personid;
  private String modularname;
  private String messagetime;
  private String readmessage;
  private long detailtype;
  private String detailtypename;
  private String leaveorder;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getModular() {
    return modular;
  }

  public void setModular(long modular) {
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


  public String getMessagetime() {
    return messagetime;
  }

  public void setMessagetime(String messagetime) {
    this.messagetime = messagetime;
  }


  public String getReadmessage() {
    return readmessage;
  }

  public void setReadmessage(String readmessage) {
    this.readmessage = readmessage;
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

  public String getLeaveorder() {
    return leaveorder;
  }

  public void setLeaveorder(String leaveorder) {
    this.leaveorder = leaveorder;
  }

}
