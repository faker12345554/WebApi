package com.admin.model.log;

public class LogParamModel {

  private int operator;
  private String modular;

  public int getPageSize() {
    return PageSize;
  }

  public LogParamModel setPageSize(int pageSize) {
    PageSize = pageSize;
    return this;
  }

  public int getPageIndex() {
    return PageIndex;
  }

  public LogParamModel setPageIndex(int pageIndex) {
    PageIndex = pageIndex;
    return this;
  }

  private int PageSize;
  private int PageIndex;

  public int getOperator() {
    return operator;
  }

  public void setOperator(int operator) {
    this.operator = operator;
  }

  public String getModular() {
    return modular;
  }

  public void setModular(String modular) {
    this.modular = modular;
  }

}
