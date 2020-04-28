package com.admin.model.enummodel;


import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EnumSearchModel {
    private String typeName;
    private String typeCode;
    private Boolean status;
    private String startTime;
    private String endTime;
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
