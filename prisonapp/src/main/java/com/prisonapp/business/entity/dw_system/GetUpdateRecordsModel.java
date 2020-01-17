package com.prisonapp.business.entity.dw_system;

import java.util.Date;

public class GetUpdateRecordsModel {
    private String timestamp;
    private String version;
    private int versionCode;
    private String msg;
    private String url;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        String strTimestamp = String.valueOf(timestamp.getTime());
        this.timestamp = strTimestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
