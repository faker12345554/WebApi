package com.prisonapp.business.entity.message;

import java.sql.Timestamp;
/**
 * 保外人员的消息列表list
 * */
public class MessageListModel {

    public int  code;
    public String type;
    public String typeName;
    public String content;
    public java.sql.Timestamp timestamp;
    public long detailtype;
    public String detailtypename;
    public boolean isRead;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getDetailtype() {
        return detailtype;
    }

    public void setDetailtype(long detailtype) {
        this.detailtype = detailtype;
    }

    public String getDetailtypename() {
        return detailtypename;
    }

    public void setDetailtypename(String detailtypename) {
        this.detailtypename = detailtypename;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
