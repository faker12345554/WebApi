package com.admin.admin.entity.dw_group;

import io.swagger.annotations.ApiModelProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Condition {
    @ApiModelProperty(value = "权限组名称",dataType = "String")
    private String  groupName;
    @ApiModelProperty(value = "状态",dataType = "boolean")
    private Boolean status;
    @ApiModelProperty(value = "开始日期",dataType = "String")
    private String startTime;
    @ApiModelProperty(value = "结束日期",dataType = "String")
    private String endTime;
    @ApiModelProperty(value = "页面大小",dataType = "int")
    private int pageSize;
    @ApiModelProperty(value = "页码",dataType = "int")
    private int pageIndex;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public void setStartTime(String startTime) throws ParseException {
            this.startTime = startTime;

    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) throws ParseException {

            this.endTime = endTime;

    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
