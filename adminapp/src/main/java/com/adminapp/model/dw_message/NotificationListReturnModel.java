package com.adminapp.model.dw_message;

import java.util.List;

public class NotificationListReturnModel {
    private int totalCount;
    private List<NotificationListModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<NotificationListModel> getList() {
        return list;
    }

    public void setList(List<NotificationListModel> list) {
        this.list = list;
    }
}
