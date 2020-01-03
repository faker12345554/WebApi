package com.adminapp.model.dw_supervise;

import java.util.List;

public class ValidWayReturnModel {
    private int totalCount;
    private List<PrisonSettingModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<PrisonSettingModel> getList() {
        return list;
    }

    public void setList(List<PrisonSettingModel> list) {
        this.list = list;
    }
}
