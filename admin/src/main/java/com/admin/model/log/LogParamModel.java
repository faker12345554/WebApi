package com.admin.model.log;

public class LogParamModel {

  private String operator;
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

  public String getOperator() {
    return operator;
  }

  public LogParamModel setOperator(String operator) {
    this.operator = operator;
    return this;
  }

  public LogParamModel setPageIndex(int pageIndex) {
    PageIndex = pageIndex;
    return this;
  }

  private int PageSize;
  private int PageIndex;


  public String getModular() {
    return modular;
  }

  public void setModular(String modular) {
    this.modular = modular;
  }

}
