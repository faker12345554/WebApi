package com.adminapp.model.dw_supervise;

import java.util.List;

public class LeaveListReturnModel {
    private int totalCount;
    private List<LeaveListModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<LeaveListModel> getList() {
        return list;
    }

    public void setList(List<LeaveListModel> list) {
        this.list = list;
    }
}
