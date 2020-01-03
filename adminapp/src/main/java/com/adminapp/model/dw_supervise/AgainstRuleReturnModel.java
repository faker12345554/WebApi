package com.adminapp.model.dw_supervise;

import java.util.List;

public class AgainstRuleReturnModel {
    private int totalCount;
    private List<AgainstRuleModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<AgainstRuleModel> getList() {
        return list;
    }

    public void setList(List<AgainstRuleModel> list) {
        this.list = list;
    }
}
