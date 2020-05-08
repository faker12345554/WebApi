package com.admin.model.enummodel;


import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EnumSearchModel {
    @ApiModelProperty(value = "枚举名称",dataType = "String")
    private String typeName;
    @ApiModelProperty(value = "枚举编码",dataType = "String")
    private String typeCode;
    @ApiModelProperty(value = "枚举状态",dataType = "boolean")
    private Boolean status;
    @ApiModelProperty(value = "开始日期",dataType = "String")
    private String startTime;
    @ApiModelProperty(value = "结束日期",dataType = "String")
    private String endTime;
    @ApiModelProperty(value = "页面大小",dataType = "int")
    private int PageSize;
    @ApiModelProperty(value = "页码",dataType = "int")
    private int PageIndex;

    public int getPageIndex() {
        return PageIndex;
    }

    public EnumSearchModel setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
        return this;
    }

    public int getPageSize() {
        return PageSize;
    }

    public EnumSearchModel setPageSize(int pageSize) {
        PageSize = pageSize;
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
