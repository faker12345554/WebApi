package com.prisonapp.business.entity.dw_message;

import java.sql.Timestamp;
import java.util.Date;

//保外人员的通知列表
public class NotificationMessageModel {
    public int type;
    public String typeName;
    public String content;
    public String timestamp;
    public int detailType;
    public String detailTypeName;
    public int unreadCount;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        String timeStamp =String.valueOf(timestamp.getTime());
        this.timestamp = timeStamp;
    }

    public int getDetailType() {
        return detailType;
    }

    public void setDetailType(int detailType) {
        this.detailType = detailType;
    }

    public String getDetailTypeName() {
        return detailTypeName;
    }

    public void setDetailTypeName(String detailTypeName) {
        this.detailTypeName = detailTypeName;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}
