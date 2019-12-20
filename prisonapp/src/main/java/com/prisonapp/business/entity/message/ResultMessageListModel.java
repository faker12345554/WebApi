package com.prisonapp.business.entity.message;

import java.util.List;

public class ResultMessageListModel {
    public   int totalCount;
    public List<MessageListModel> resultMessageListModel;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List getResultMessageListModel() {
        return resultMessageListModel;
    }

    public void setResultMessageListModel(List resultMessageListModel) {
        resultMessageListModel = resultMessageListModel;
    }
}
