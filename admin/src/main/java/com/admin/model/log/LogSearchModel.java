package com.admin.model.log;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LogSearchModel {

    @ApiModelProperty(value = "操作人",dataType = "String")
    private String operator;
    @ApiModelProperty(value = "功能",dataType = "String")
    private String modular;
    @ApiModelProperty(value = "操作日期 开始日期",dataType = "Date")
    private Date startTime;
    @ApiModelProperty(value = "操作日期 结束日期",dataType = "Date")
    private Date endTime;

    @ApiModelProperty(value = "页面大小",dataType = "int")
    private int PageSize;
    @ApiModelProperty(value = "页码",dataType = "int")
    private int PageIndex;

    public int getPageIndex() {
        return PageIndex;
    }

    public LogSearchModel setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
        return this;
    }

    public int getPageSize() {
        return PageSize;
    }

    public LogSearchModel setPageSize(int pageSize) {
        PageSize = pageSize;
        return this;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        if(endTime==null) {
            this.endTime = null;
        }
        else {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(endTime);
            calendar.add(calendar.DATE, 1);
            java.util.Date date = calendar.getTime();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            this.endTime = sqlDate;
        }
    }
}
