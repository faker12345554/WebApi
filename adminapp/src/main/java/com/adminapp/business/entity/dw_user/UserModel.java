package com.adminapp.business.entity.dw_user;

public class UserModel {

  private int id;
  private String accountname;
  private String password;
  private String aliasname;
  private int permissionid;
  private int createid;
  private String createname;
  private String areacode;
  private String status;
  private String phone;
  private int usersystem;
  private String officephone;
  private String police;
  private String areaname;
  private String department;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getPermissionid() {
    return permissionid;
  }

  public void setPermissionid(int permissionid) {
    this.permissionid = permissionid;
  }

  public int getCreateid() {
    return createid;
  }

  public void setCreateid(int createid) {
    this.createid = createid;
  }

  public int getUsersystem() {
    return usersystem;
  }

  public void setUsersystem(int usersystem) {
    this.usersystem = usersystem;
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



  public String getOfficephone() {
    return officephone;
  }

  public void setOfficephone(String officephone) {
    this.officephone = officephone;
  }


  public String getPolice() {
    return police;
  }

  public void setPolice(String police) {
    this.police = police;
  }


  public String getAreaname() {
    return areaname;
  }

  public void setAreaname(String areaname) {
    this.areaname = areaname;
  }


  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }
}
