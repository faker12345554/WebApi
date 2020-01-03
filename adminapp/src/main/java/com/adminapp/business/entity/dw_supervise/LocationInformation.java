package com.adminapp.business.entity.dw_supervise;


import java.util.Date;

public class LocationInformation {

  private int id;
  private String latitude;
  private String longitude;
  private String address;
  private String personid;
  private String timestamp;
  private String locationtype;
  private String devicecoding;
  private String alarmstate;
  private float surpluselectricity;
  private String signalintensity;
  private boolean fscope;
  private boolean goout;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public float getSurpluselectricity() {
    return surpluselectricity;
  }

  public void setSurpluselectricity(float surpluselectricity) {
    this.surpluselectricity = surpluselectricity;
  }

  public boolean isFscope() {
    return fscope;
  }

  public void setFscope(boolean fscope) {
    this.fscope = fscope;
  }

  public boolean isGoout() {
    return goout;
  }

  public void setGoout(boolean goout) {
    this.goout = goout;
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


  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    String timeStamp=String.valueOf(timestamp.getTime());
    this.timestamp = timeStamp;
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




  public String getSignalintensity() {
    return signalintensity;
  }

  public void setSignalintensity(String signalintensity) {
    this.signalintensity = signalintensity;
  }



}
