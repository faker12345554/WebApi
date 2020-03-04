package com.admin.admin.entity.dw_user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Usermodel {
    private String userName;
    private String phone;
    private String status;
    private Date startTime;
    private Date endTime;
    private int pageSize;
    private int pageIndex;
    private Date createtime;
    private int id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) throws ParseException {
        if(startTime!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startTimeDate=sdf.parse(startTime);
            this.startTime = startTimeDate;
        }else{
            this.startTime = null;
        }

    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) throws ParseException {
        if(endTime!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date endTimeDate=sdf.parse(endTime);
            this.endTime = endTimeDate;
        }else{
            this.endTime = null;
        }

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
