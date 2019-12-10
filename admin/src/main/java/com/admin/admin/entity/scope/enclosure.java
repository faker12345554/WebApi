package com.admin.admin.entity.scope;


import java.util.Date;

public class enclosure {

  private long id;
  private String personId;
  private String type;
  private String longitude;
  private Date areaName;

  public boolean isStatus() {
    return status;
  }

  public enclosure setStatus(boolean status) {
    this.status = status;
    return this;
  }

  private Date latitude;
  private boolean status;

  public Date getAreaName() {
    return areaName;
  }

  public enclosure setAreaName(Date areaName) {
    this.areaName = areaName;
    return this;
  }

  public Date getLatitude() {
    return latitude;
  }

  public enclosure setLatitude(Date latitude) {
    this.latitude = latitude;
    return this;
  }

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
