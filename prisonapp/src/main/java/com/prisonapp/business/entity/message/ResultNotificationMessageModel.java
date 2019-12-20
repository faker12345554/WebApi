package com.prisonapp.business.entity.message;

import java.util.List;

public class ResultNotificationMessageModel {
    public  int totalCount;
    public List<NotificationMessageModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<NotificationMessageModel> getList() {
        return list;
    }

    public void setList(List<NotificationMessageModel> list) {
        this.list = list;
    }
}


