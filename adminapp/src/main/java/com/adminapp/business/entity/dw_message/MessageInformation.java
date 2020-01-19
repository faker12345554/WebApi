package com.adminapp.business.entity.dw_message;


import java.util.Date;

public class MessageInformation {

  private int id;
  private String modular;
  private String content;
  private String personid;
  private String modularname;
  private Date messagetime;
  private boolean readmessage;
  private int detailtype;
  private String detailtypename;
  private String personcontent;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getModular() {
    return modular;
  }

  public void setModular(int modular) {
    String Modual=Integer.toString(modular);
    this.modular = Modual;
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

  public void setMessagetime(Date messagetime) {
    this.messagetime = messagetime;
  }


  public boolean getReadmessage() {
    return readmessage;
  }

  public void setReadmessage(boolean readmessage) {
    this.readmessage = readmessage;
  }


  public int getDetailtype() {
    return detailtype;
  }

  public void setDetailtype(int detailtype) {
    this.detailtype = detailtype;
  }


  public String getDetailtypename() {
    return detailtypename;
  }

  public void setDetailtypename(String detailtypename) {
    this.detailtypename = detailtypename;
  }


  public String getPersoncontent() {
    return personcontent;
  }

  public void setPersoncontent(String personcontent) {
    this.personcontent = personcontent;
  }

}
