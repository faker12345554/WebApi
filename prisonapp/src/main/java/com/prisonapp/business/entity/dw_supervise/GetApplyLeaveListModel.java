package com.prisonapp.business.entity.dw_supervise;

import java.sql.Timestamp;
import java.util.List;

public class GetApplyLeaveListModel {
    public String  code;
    public String  statuscode;
    public String status;
    public String applicant;
    public java.sql.Timestamp applyTimestamp;
    public java.sql.Timestamp startTimestamp;
    public java.sql.Timestamp endTimestamp;
    public int days;
    public String address;
    public String reason;
    public List<ApplyRecordModel> applyRecord;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public Timestamp getApplyTimestamp() {
        return applyTimestamp;
    }

    public void setApplyTimestamp(Timestamp applyTimestamp) {
        this.applyTimestamp = applyTimestamp;
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Timestamp startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Timestamp endTimestamp) {
        this.endTimestamp = endTimestamp;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ApplyRecordModel> getApplyRecord() {
        return applyRecord;
    }

    public void setApplyRecord(List<ApplyRecordModel> applyRecord) {
        this.applyRecord = applyRecord;
    }


}
