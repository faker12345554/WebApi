package com.prisonapp.business.entity.dw_message;

import java.util.Date;

/**
 * 保外人员的消息列表list
 * 同时也是获取保外人员最新消息
 * */
public class MessageListModel {

    public String  code;
    public String type;
    public String typeName;
    public String content;
    public String  timestamp;
    public String detailType;
    public String detailTypeName;
    private String contactCode;
    public boolean isRead;

    public String getCode() {
        return code;
    }

    public void setCode(int code) {
        String strCode =String.valueOf(code);
        this.code = strCode;
    }

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
        String timeStamp=String.valueOf(timestamp.getTime());
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

    public String getContactCode() {
        return contactCode;
    }

    public void setContactCode(String contactCode) {
        this.contactCode = contactCode;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
}
