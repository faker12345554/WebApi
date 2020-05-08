package com.admin.admin.entity.dw_user;

import io.swagger.annotations.ApiModelProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Usermodel {
    @ApiModelProperty(value = "用户名",dataType = "String")
    private String userName;
    @ApiModelProperty(value = "手机号码",dataType = "String")
    private String phone;
    @ApiModelProperty(value = "状态",dataType = "String")
    private Boolean status;
    @ApiModelProperty(value = "搜索日期 开始日期",dataType = "String")
    private String startTime;
    @ApiModelProperty(value = "搜索日期 结束日期",dataType = "String")
    private String endTime;
    @ApiModelProperty(value = "页面大小",dataType = "int")
    private int pageSize;
    @ApiModelProperty(value = "页码",dataType = "int")
    private int pageIndex;
    @ApiModelProperty(value = "创建日期",dataType = "date")
    private Date createtime;

    private int id;
    @ApiModelProperty(value = "用户id",dataType = "int")
    private String userid;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
