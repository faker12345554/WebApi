package com.adminapp.model.dw_supervise;

import java.util.List;

public class LocationRecordReturnModel {
    private int totalCount;
    private List<LocationRecordModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<LocationRecordModel> getList() {
        return list;
    }

    public void setList(List<LocationRecordModel> list) {
        this.list = list;
    }
}
