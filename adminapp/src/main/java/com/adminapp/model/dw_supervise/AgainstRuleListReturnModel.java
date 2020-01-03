package com.adminapp.model.dw_supervise;

import java.util.List;

public class AgainstRuleListReturnModel {
    private int totalCount;
    private List<AgainstRuleListModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<AgainstRuleListModel> getList() {
        return list;
    }

    public void setList(List<AgainstRuleListModel> list) {
        this.list = list;
    }
}
