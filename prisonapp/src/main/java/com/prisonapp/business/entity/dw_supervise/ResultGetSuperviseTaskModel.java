package com.prisonapp.business.entity.dw_supervise;

import java.util.List;

public class ResultGetSuperviseTaskModel {
    private int totalCount;
    private List<GetSuperviseTaskModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<GetSuperviseTaskModel> getList() {
        return list;
    }

    public void setList(List<GetSuperviseTaskModel> list) {
        this.list = list;
    }
}
