package com.admin.model.log;

import io.swagger.annotations.ApiModelProperty;

public class LogParamModel {

  @ApiModelProperty(value = "操作人",dataType = "")
  private String operator;
  @ApiModelProperty(value = "模块",dataType = "String")
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

  @ApiModelProperty(value = "页面大小",dataType = "int")
  private int PageSize;
  @ApiModelProperty(value = "页码",dataType = "int")
  private int PageIndex;


  public String getModular() {
    return modular;
  }

  public void setModular(String modular) {
    this.modular = modular;
  }

}
