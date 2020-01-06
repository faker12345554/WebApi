package com.prisonapp.business.entity.dw_message;

import java.util.Date;

/**
 * 保外人员的消息列表list
 * 同时也是获取保外人员最新消息
 * */
public class MessageListModel {

    public int  code;
    public int type;
    public String typeName;
    public String content;
    public String  timestamp;
    public int detailtype;
    public String detailtypename;
    public boolean isRead;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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
        String timeStamp=String.valueOf(timestamp.getTime());
        this.timestamp = timeStamp;
    }

    public int getDetailtype() {
        return detailtype;
    }

    public void setDetailtype(int detailtype) {
        this.detailtype = detailtype;
    }

    public String getDetailtypename() {
        return detailtypename;
    }

    public void setDetailtypename(String detailtypename) {
        this.detailtypename = detailtypename;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
}
