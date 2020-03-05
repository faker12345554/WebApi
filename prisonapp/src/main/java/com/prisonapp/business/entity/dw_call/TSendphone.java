package com.prisonapp.business.entity.dw_call;


public class TSendphone {

  private long id;
  private String calltoken;
  private String calltimestamp;
  private java.sql.Timestamp createtime;
  private String canceltype;
  private String canceltimestamp;
  private String serverurl;
  private String roomcode;
  private String calltype;
  private String callname;
  private String sendname;
  private String personid;
  private String accountname;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getCalltoken() {
    return calltoken;
  }

  public void setCalltoken(String calltoken) {
    this.calltoken = calltoken;
  }


  public String getCalltimestamp() {
    return calltimestamp;
  }

  public void setCalltimestamp(String calltimestamp) {
    this.calltimestamp = calltimestamp;
  }


  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }


  public String getCanceltype() {
    return canceltype;
  }

  public void setCanceltype(String canceltype) {
    this.canceltype = canceltype;
  }


  public String getCanceltimestamp() {
    return canceltimestamp;
  }

  public void setCanceltimestamp(String canceltimestamp) {
    this.canceltimestamp = canceltimestamp;
  }


  public String getServerurl() {
    return serverurl;
  }

  public void setServerurl(String serverurl) {
    this.serverurl = serverurl;
  }


  public String getRoomcode() {
    return roomcode;
  }

  public void setRoomcode(String roomcode) {
    this.roomcode = roomcode;
  }


  public String getCalltype() {
    return calltype;
  }

  public void setCalltype(String calltype) {
    this.calltype = calltype;
  }


  public String getCallname() {
    return callname;
  }

  public void setCallname(String callname) {
    this.callname = callname;
  }


  public String getSendname() {
    return sendname;
  }

  public void setSendname(String sendname) {
    this.sendname = sendname;
  }


  public String getPersonid() {
    return personid;
  }

  public void setPersonid(String personid) {
    this.personid = personid;
  }


  public String getAccountname() {
    return accountname;
  }

  public void setAccountname(String accountname) {
    this.accountname = accountname;
  }

}
