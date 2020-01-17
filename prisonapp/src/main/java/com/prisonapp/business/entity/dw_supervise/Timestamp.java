package com.prisonapp.business.entity.dw_supervise;

public class Timestamp {
    private String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        String strTimestamp = String.valueOf(timestamp);
        this.timestamp = strTimestamp;
    }
}
