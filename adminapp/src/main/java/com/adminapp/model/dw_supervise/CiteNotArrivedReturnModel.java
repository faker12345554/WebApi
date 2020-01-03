package com.adminapp.model.dw_supervise;

import java.util.List;

public class CiteNotArrivedReturnModel {
    private int totalCount;
    private List<CiteNotArrivedModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<CiteNotArrivedModel> getList() {
        return list;
    }

    public void setList(List<CiteNotArrivedModel> list) {
        this.list = list;
    }
}
