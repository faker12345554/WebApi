package com.prisonapp.business.entity.dw_message;

import java.sql.Timestamp;
import java.util.Date;

//保外人员的通知列表、获取保外人员的某一类通知
public class NotificationMessageModel {
    public String type;
    public String typeName;
    public String content;
    public String timestamp;
    public String detailType;
    public String detailTypeName;
    public int unreadCount;

    public String getType() {
        return type;
    }

    public void setType(int type) {
        String strType =String.valueOf(type);
        this.type = strType;
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

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(int detailType) {
        String strDetailType =String.valueOf(detailType);
        this.detailType = strDetailType;
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
