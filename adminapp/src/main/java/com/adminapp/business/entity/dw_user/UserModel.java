package com.adminapp.business.entity.dw_user;

public class UserModel {

  private long id;
  private String accountname;
  private String password;
  private String aliasname;
  private long permissionid;
  private long createid;
  private String createname;
  private String areacode;
  private String status;
  private String phone;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getAccountname() {
    return accountname;
  }

  public void setAccountname(String accountname) {
    this.accountname = accountname;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getAliasname() {
    return aliasname;
  }

  public void setAliasname(String aliasname) {
    this.aliasname = aliasname;
  }


  public long getPermissionid() {
    return permissionid;
  }

  public void setPermissionid(long permissionid) {
    this.permissionid = permissionid;
  }


  public long getCreateid() {
    return createid;
  }

  public void setCreateid(long createid) {
    this.createid = createid;
  }


  public String getCreatename() {
    return createname;
  }

  public void setCreatename(String createname) {
    this.createname = createname;
  }


  public String getAreacode() {
    return areacode;
  }

  public void setAreacode(String areacode) {
    this.areacode = areacode;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
