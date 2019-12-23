package com.admin.model.search;

import com.admin.model.Execl.ExeclModel;

import java.util.Date;

public class SearchModel extends ExeclModel {

    private String Condition1;
    private String Condition2;
    private Date StartTime;
    private Date EndTime;
    private String FuzzyCondition;
    private boolean flag;
    private int PageSize;
    private int PageIndex;


    public int getPageSize() {
        return PageSize;
    }

    public SearchModel setPageSize(int pageSize) {
        PageSize = pageSize;
        return this;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public SearchModel setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
        return this;
    }



    public String getCondition1() {
        return Condition1;
    }

    public SearchModel setCondition1(String condition1) {
        Condition1 = condition1;
        return this;
    }

    public String getCondition2() {
        return Condition2;
    }

    public SearchModel setCondition2(String condition2) {
        Condition2 = condition2;
        return this;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public SearchModel setStartTime(Date startTime) {
        StartTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public SearchModel setEndTime(Date endTime) {
        EndTime = endTime;
        return this;
    }

    public String getFuzzyCondition() {
        return FuzzyCondition;
    }

    public SearchModel setFuzzyCondition(String fuzzyCondition) {
        FuzzyCondition = fuzzyCondition;
        return this;
    }

    public boolean isFlag() {
        return flag;
    }

    public SearchModel setFlag(boolean flag) {
        this.flag = flag;
        return this;
    }



}
