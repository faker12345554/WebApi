package com.adminapp.business.entity.dw_supervise;

import java.util.Date;

public class Personinformation {

  private String code;
  private String name;
  private String number;
  private String idCardNo;
  private long age;
  private int gender;
  private String headUrl;
  private String stateCode;
  private String state;
  public String violateCode;
  public String violate;
  public String execStartDate;
  public String execEndDate;
  public String phone;
  //public String[] tags;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getIdCardNo() {
    return idCardNo;
  }

  public void setIdCardNo(String idCardNo) {
    this.idCardNo = idCardNo;
  }

  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    this.gender = gender;
  }

  public String getHeadUrl() {
    return headUrl;
  }

  public void setHeadUrl(String headUrl) {
    this.headUrl = headUrl;
  }

  public String getStateCode() {
    return stateCode;
  }

  public void setStateCode(String stateCode) {
    this.stateCode = stateCode;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getViolateCode() {
    return violateCode;
  }

  public void setViolateCode(String violateCode) {
    this.violateCode = violateCode;
  }

  public String getViolate() {
    return violate;
  }

  public void setViolate(String violate) {
    this.violate = violate;
  }

  public String getExecStartDate() {
    return execStartDate;
  }

  public void setExecStartDate(Date execStartDate) {
    String execstartDate=String.valueOf(execStartDate.getTime());
    this.execStartDate = execstartDate;
  }

  public String getExecEndDate() {
    return execEndDate;
  }

  public void setExecEndDate(Date execEndDate) {
    String execendDate=String.valueOf(execEndDate.getTime());
    this.execEndDate = execendDate;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

//  public String[] getTags() {
//    return tags;
//  }
//
//  public void setTags(String tags) {
//    String[] a=tags.split("，");
//    this.tags = a;
//  }

}
