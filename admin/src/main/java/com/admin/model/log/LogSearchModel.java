package com.admin.model.log;

import java.sql.Date;

public class LogSearchModel {
    private String operator;
    private String modular;
    private Date startTime;
    private Date endTime;

    private int PageSize;
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
        this.endTime = endTime;
    }
}
