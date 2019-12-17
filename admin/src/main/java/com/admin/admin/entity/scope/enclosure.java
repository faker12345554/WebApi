package com.admin.admin.entity.scope;


public class enclosure {

  private long id;
  private String personId;
  private String type;
  private String areaname;
  private String status;
  private String citycode;

  public String getPersonId() {
    return personId;
  }

  public enclosure setPersonId(String personId) {
    this.personId = personId;
    return this;
  }

  public String getAreaname() {
    return areaname;
  }

  public enclosure setAreaname(String areaname) {
    this.areaname = areaname;
    return this;
  }

  private String citylevel;


  private String areaArr;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }




  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }




  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getCitycode() {
    return citycode;
  }

  public void setCitycode(String citycode) {
    this.citycode = citycode;
  }


  public String getCitylevel() {
    return citylevel;
  }

  public void setCitylevel(String citylevel) {
    this.citylevel = citylevel;
  }


  public String getAreaArr() {
    return areaArr;
  }

  public void setAreaArr(String areaArr) {
    this.areaArr = areaArr;
  }

}
