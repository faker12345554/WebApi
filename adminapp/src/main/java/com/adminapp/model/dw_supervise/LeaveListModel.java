package com.adminapp.model.dw_supervise;

import java.util.Date;
import java.util.List;

public class LeaveListModel {
    private String code;
    private String statusCode;
    private String status;
    private String applicant;
    private String applyTimestamp;
    private String startTimestamp;
    private String endTimestamp;
    private int days;
    private String address;
    private String reason;
    private String reasonAudioUrl;
    List<AuditorRecordModel> applyRecord;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
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
        String applytimeStamp=String.valueOf(applyTimestamp.getTime());
        this.applyTimestamp = applytimeStamp;
    }

    public String getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(Date startTimestamp) {
        String starttimestamp=String.valueOf(startTimestamp.getTime());
        this.startTimestamp = starttimestamp;
    }

    public String getEndTimestamp() {
        return endTimestamp;
    }

    public void setEndTimestamp(Date endTimestamp) {
        String endtimestamp=String.valueOf(endTimestamp.getTime());
        this.endTimestamp = endtimestamp;
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

    public List<AuditorRecordModel> getApplyRecord() {
        return applyRecord;
    }

    public void setApplyRecord(List<AuditorRecordModel> applyRecord) {
        this.applyRecord = applyRecord;
    }

}
