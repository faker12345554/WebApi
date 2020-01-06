package com.adminapp.model.dw_message;

import java.util.List;

public class SearchNotificationReturnModel {
    private int totalCount;
    private List<SearchNotificationModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<SearchNotificationModel> getList() {
        return list;
    }

    public void setList(List<SearchNotificationModel> list) {
        this.list = list;
    }
}
