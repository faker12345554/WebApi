package com.prisonapp.business.entity.dw_supervise;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class GetApplyLeaveListModel {
    public String  code;
    public String  statusCode;
    public String status;
    public String applicant;
    public String applyTimestamp;
    public String startTimestamp;
    public String endTimestamp;
    public int days;
    public String address;
    public String reason;
    public String reasonAudioUrl;
    public List<ApplyRecordModel> applyRecord;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatuscode() {
        return statusCode;
    }

    public void setStatuscode(String statuscode) {
        this.statusCode = statuscode;
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

    public String getApplyTimestamp() {
        return applyTimestamp;
    }

    public void setApplyTimestamp(Date applyTimestamp) {
        String a =String.valueOf(applyTimestamp.getTime());
        this.applyTimestamp = a;
    }

    public String getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp) {
        String a =String.valueOf(startTimestamp.getTime());
        this.startTimestamp = a;
    }

    public String getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Date endTimestamp) {
        String a =String.valueOf(endTimestamp.getTime());
        this.endTimestamp = a;
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

    public String getReasonAudioUrl() {
        return reasonAudioUrl;
    }

    public void setReasonAudioUrl(String reasonAudioUrl) {
        this.reasonAudioUrl = reasonAudioUrl;
    }

    public List<ApplyRecordModel> getApplyRecord() {
        return applyRecord;
    }

    public void setApplyRecord(List<ApplyRecordModel> applyRecord) {
        this.applyRecord = applyRecord;
    }


}
