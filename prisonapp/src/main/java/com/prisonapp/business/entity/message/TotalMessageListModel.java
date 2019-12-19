package com.prisonapp.business.entity.message;

public class TotalMessageListModel {
    private  int totalCount;
    private MessageListModel messageListModel;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public MessageListModel getMessageListModel() {
        return messageListModel;
    }

    public void setMessageListModel(MessageListModel messageListModel) {
        this.messageListModel = messageListModel;
    }
}
