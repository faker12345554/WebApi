package com.admin.model.search;

import com.admin.model.Execl.ExeclModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class SearchModel extends ExeclModel {
    @ApiModelProperty(value = "搜索条件1",dataType = "String")
    private String Condition;
    @ApiModelProperty(value = "姓名",dataType = "String")
    private String PersonName;
    @ApiModelProperty(value = "搜索开始日期",dataType = "String")
    private String StartTime;
    @ApiModelProperty(value = "搜索结束日期",dataType = "String")
    private String EndTime;
    @ApiModelProperty(value = "模糊查询",dataType = "String")
    private String FuzzyCondition;
    @ApiModelProperty(value = "状态",dataType = "boolean")
    private boolean flag;
    @ApiModelProperty(value = "页面大小",dataType = "int")
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
    @ApiModelProperty(value = "页码",dataType = "int")
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
