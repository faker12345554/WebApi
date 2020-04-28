package com.admin.admin.entity.dw_group;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Condition {
    private String  groupName;
    private Boolean status;
    private String startTime;
    private String endTime;
    private int pageSize;
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
