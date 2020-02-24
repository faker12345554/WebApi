package com.admin.admin.entity.dw_group;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Condition {
    private String  groupName;
    private String status;
    private Date startTime;
    private Date endTime;
    private int pageSize;
    private int pageIndex;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
}
