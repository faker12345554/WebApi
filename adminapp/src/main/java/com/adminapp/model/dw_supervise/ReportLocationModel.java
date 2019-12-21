package com.adminapp.model.dw_supervise;


public class ReportLocationModel {

  private long id;
  private String latitude;
  private String longitude;
  private String address;
  private String personid;
  private java.sql.Timestamp timestamp;
  private String locationtype;
  private String devicecoding;
  private String alarmstate;
  private long surpluselectricity;
  private String signalintensity;
  private boolean fscope;
  private String goout;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }


  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getPersonid() {
    return personid;
  }

  public void setPersonid(String personid) {
    this.personid = personid;
  }


  public java.sql.Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(java.sql.Timestamp timestamp) {
    this.timestamp = timestamp;
  }


  public String getLocationtype() {
    return locationtype;
  }

  public void setLocationtype(String locationtype) {
    this.locationtype = locationtype;
  }


  public String getDevicecoding() {
    return devicecoding;
  }

  public void setDevicecoding(String devicecoding) {
    this.devicecoding = devicecoding;
  }


  public String getAlarmstate() {
    return alarmstate;
  }

  public void setAlarmstate(String alarmstate) {
    this.alarmstate = alarmstate;
  }


  public long getSurpluselectricity() {
    return surpluselectricity;
  }

  public void setSurpluselectricity(long surpluselectricity) {
    this.surpluselectricity = surpluselectricity;
  }


  public String getSignalintensity() {
    return signalintensity;
  }

  public void setSignalintensity(String signalintensity) {
    this.signalintensity = signalintensity;
  }

  public boolean isFscope() {
    return fscope;
  }

  public void setFscope(boolean fscope) {
    this.fscope = fscope;
  }

  public String getGoout() {
    return goout;
  }

  public void setGoout(String goout) {
    this.goout = goout;
  }

}
