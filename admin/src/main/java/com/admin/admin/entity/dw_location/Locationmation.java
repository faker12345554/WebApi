package com.admin.admin.entity.dw_location;


import java.util.Date;

public class Locationmation {

  private long id;
  private String latitude;
  private String longitude;
  private String address;
  private String personid;
  private Date timestamp;
  private String locationtype;
  private String devicecoding;
  private String alarmstate;
  private long surpluselectricity;
  private String signalintensity;

  public String getReceivingtime() {
    return Receivingtime;
  }

  public Locationmation setReceivingtime(String receivingtime) {
    Receivingtime = receivingtime;
    return this;
  }

  private String fscope;
  private String goout;
  private String Receivingtime;

  public Locationmation setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
    return this;
  }

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

  public Date getTimestamp() {
    return timestamp;
  }

  public String getPersonid() {
    return personid;
  }

  public Locationmation setPersonid(String personid) {
    this.personid = personid;
    return this;
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


  public String getFscope() {
    return fscope;
  }

  public void setFscope(String fscope) {
    this.fscope = fscope;
  }


  public String getGoout() {
    return goout;
  }

  public void setGoout(String goout) {
    this.goout = goout;
  }

}
