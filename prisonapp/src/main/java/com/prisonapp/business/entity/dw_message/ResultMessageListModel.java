package com.prisonapp.business.entity.dw_message;

import java.util.List;
/**
 * 同时也是获取保外人员最新消息的model
 * */
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
