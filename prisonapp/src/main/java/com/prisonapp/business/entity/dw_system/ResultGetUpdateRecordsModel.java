package com.prisonapp.business.entity.dw_system;

import java.util.List;

public class ResultGetUpdateRecordsModel {
    private int totalCount;
    private List<GetUpdateRecordsModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<GetUpdateRecordsModel> getList() {
        return list;
    }

    public void setList(List<GetUpdateRecordsModel> list) {
        this.list = list;
    }
}
