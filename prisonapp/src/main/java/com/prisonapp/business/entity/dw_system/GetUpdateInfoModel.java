package com.prisonapp.business.entity.dw_system;


public class GetUpdateInfoModel {

  private String name;
  private String version;
  private int versionCode;
  private String url;
  private String msg;




  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }


  public int getVersionCode() {
    return versionCode;
  }

  public void setVersionCode(int versionCode) {
    this.versionCode = versionCode;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }



}
