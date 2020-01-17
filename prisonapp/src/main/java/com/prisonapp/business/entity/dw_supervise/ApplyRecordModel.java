package com.prisonapp.business.entity.dw_supervise;

import java.sql.Timestamp;
import java.util.Date;

public class ApplyRecordModel {
    public String  statusCode;
    public String  status;
    public String timestamp;
    public String person;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        String strStatusCode =String.valueOf(statusCode);
        this.statusCode = strStatusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        String strTimestamp = String.valueOf(timestamp.getTime());
        this.timestamp = strTimestamp;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
