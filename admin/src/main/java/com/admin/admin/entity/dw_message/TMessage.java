package com.admin.admin.entity.dw_message;


import java.util.Date;

public class TMessage {


  private long id;



    public String getLeaveorder() {
    return leaveorder;
  }

  public TMessage setLeaveorder(String leaveorder) {
    this.leaveorder = leaveorder;
    return this;
  }

  private int modular;
  private String content;
 // private String workcontent;
  private String personid;
  private String modularname;
  private Date messagetime;
  private boolean readmessage;
  private long detailtype;
  private String detailtypename;
  private String leaveorder;

  public String getContent() {
    return content;
  }

  public TMessage setContent(String content) {
    this.content = content;
    return this;
  }



  public boolean isReadmessage() {
    return readmessage;
  }

  public TMessage setReadmessage(boolean readmessage) {
    this.readmessage = readmessage;
    return this;
  }
//  public String getWorkcontent() {
//    return workcontent;
//  }
//
//  public TMessage setWorkcontent(String workcontent) {
//    this.workcontent = workcontent;
//    return this;
//  }



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
