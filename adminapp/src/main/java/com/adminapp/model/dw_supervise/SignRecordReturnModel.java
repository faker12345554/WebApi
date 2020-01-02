package com.adminapp.model.dw_supervise;

import java.util.List;

public class SignRecordReturnModel {
    private int totalCount;
    private List<SignRecordModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<SignRecordModel> getList() {
        return list;
    }

    public void setList(List<SignRecordModel> list) {
        this.list = list;
    }
}
