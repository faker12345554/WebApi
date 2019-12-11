package com.admin.admin.entity.scope;


import java.util.Date;

public class enclosure {

  private long id;
  private String personId;
  private String type;

  public String getAreaName() {
    return areaName;
  }

  public enclosure setAreaName(String areaName) {
    this.areaName = areaName;
    return this;
  }

  public String getLatitude() {
    return latitude;
  }

  public enclosure setLatitude(String latitude) {
    this.latitude = latitude;
    return this;
  }

  private String longitude;
  private String areaName;

  public boolean isStatus() {
    return status;
  }

  public enclosure setStatus(boolean status) {
    this.status = status;
    return this;
  }

  private String latitude;
  private boolean status;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getPersonId() {
    return personId;
  }

  public void setPersonId(String personId) {
    this.personId = personId;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }



}
