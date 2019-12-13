package com.admin.admin.entity.scope;


public class enclosure {

  private long id;
  private String person_Id;
  private String type;
  private String area_name;
  private String status;
  private String citycode;
  private String citylevel;

  public String getPerson_Id() {
    return person_Id;
  }

  public enclosure setPerson_Id(String person_Id) {
    this.person_Id = person_Id;
    return this;
  }

  public String getArea_name() {
    return area_name;
  }

  public enclosure setArea_name(String area_name) {
    this.area_name = area_name;
    return this;
  }

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
