package com.admin.model.enummodel;


import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EnumSearchModel {
    private String typeName;
    private String typeCode;
    private String status;
    private Date startTime;
    private Date endTime;
    private int PageSize;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
