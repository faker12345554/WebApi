package com.adminapp.model.dw_supervise;

import java.util.Date;

public class PersoninformationModel {

  private String code;
  private String name;
  private String number;
  private long age;
  private String gender;
  private String headUrl;
  private String state;
  public String violateCode;
  public String violate;
  public Date execStartDate;
  public Date execEndDate;
  public String phone;
  public String[] tags;

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
    return code;
  }

  public void setNumber(String code) {
    this.number = code;
  }

  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getHeadUrl() {
    return headUrl;
  }

  public void setHeadUrl(String headUrl) {
    this.headUrl = headUrl;
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

  public Date getExecStartDate() {
    return execStartDate;
  }

  public void setExecStartDate(Date execStartDate) {
    this.execStartDate = execStartDate;
  }

  public Date getExecEndDate() {
    return execEndDate;
  }

  public void setExecEndDate(Date execEndDate) {
    this.execEndDate = execEndDate;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String[] getTags() {
    return tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }
}
