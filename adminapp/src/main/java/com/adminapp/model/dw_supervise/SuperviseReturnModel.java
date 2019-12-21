package com.adminapp.model.dw_supervise;

import com.adminapp.business.entity.dw_supervise.Personinformation;

import java.util.List;

public class SuperviseReturnModel {
    public int totalCount;
    public List<Personinformation> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Personinformation> getList() {
        return list;
    }

    public void setList(List<Personinformation> list) {
        this.list = list;
    }
}
