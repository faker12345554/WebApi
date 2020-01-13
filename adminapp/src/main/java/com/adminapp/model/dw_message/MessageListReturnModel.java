package com.adminapp.model.dw_message;

import java.util.List;

public class MessageListReturnModel {
    private int totalCount;
    private List<MessageListModel> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<MessageListModel> getList() {
        return list;
    }

    public void setList(List<MessageListModel> list) {
        this.list = list;
    }
}
