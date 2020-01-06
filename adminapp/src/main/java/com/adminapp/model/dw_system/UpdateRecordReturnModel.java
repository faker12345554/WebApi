package com.adminapp.model.dw_system;

import java.util.List;

public class UpdateRecordReturnModel {
    private int totalCount;
    private List<UpdateRecordModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<UpdateRecordModel> getList() {
        return list;
    }

    public void setList(List<UpdateRecordModel> list) {
        this.list = list;
    }
}
