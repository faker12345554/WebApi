package com.prisonapp.business.entity.supervise;

import java.util.List;

public class ResultGetApplyLeaveListModel {
    public int totalCount;
    public List<GetApplyLeaveListModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<GetApplyLeaveListModel> getList() {
        return list;
    }

    public void setList(List<GetApplyLeaveListModel> list) {
        this.list = list;
    }
}
