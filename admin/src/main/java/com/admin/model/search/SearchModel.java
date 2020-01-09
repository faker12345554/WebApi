package com.admin.model.search;

import com.admin.model.Execl.ExeclModel;

import java.util.Date;

public class SearchModel extends ExeclModel {

    private String Condition;
    private String PersonName;
    private String StartTime;
    private String EndTime;
    private String FuzzyCondition;
    private boolean flag;
    private int PageSize;

    public String getStartTime() {
        return StartTime;
    }

    public SearchModel setStartTime(String startTime) {
        StartTime = startTime;
        return this;
    }

    public String getEndTime() {
        return EndTime;
    }

    public SearchModel setEndTime(String endTime) {
        EndTime = endTime;
        return this;
    }

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

    public String getCondition() {
        return Condition;
    }

    public SearchModel setCondition(String condition) {
        Condition = condition;
        return this;
    }

    public String getPersonName() {
        return PersonName;
    }

    public SearchModel setPersonName(String personName) {
        PersonName = personName;
        return this;
    }

    public SearchModel setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
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
