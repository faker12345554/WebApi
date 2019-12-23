package com.adminapp.model.dw_supervise;

import com.adminapp.business.entity.dw_supervise.SummonsInformation;

import java.util.List;

public class CiteRecordReturnModel {
    public int totalCount;
    public List<CiteRecordsModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<CiteRecordsModel> getList() {
        return list;
    }

    public void setList(List<CiteRecordsModel> list) {
        this.list = list;
    }
}
