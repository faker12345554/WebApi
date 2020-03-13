package com.adminapp.model.dw_call;

public class RequestCallReturnModel {
    private String callToken;
    private String callTimestamp;
    private String callName;
    private int callGender;
    private String callHeadUrl;

    public String getCallToken() {
        return callToken;
    }

    public void setCallToken(String callToken) {
        this.callToken = callToken;
    }

    public String getCallTimestamp() {
        return callTimestamp;
    }

    public void setCallTimestamp(String callTimestamp) {
        this.callTimestamp = callTimestamp;
    }

    public String getCallName() {
        return callName;
    }

    public void setCallName(String callName) {
        this.callName = callName;
    }

    public int getCallGender() {
        return callGender;
    }

    public void setCallGender(int callGender) {
        this.callGender = callGender;
    }

    public String getCallHeadUrl() {
        return callHeadUrl;
    }

    public void setCallHeadUrl(String callHeadUrl) {
        this.callHeadUrl = callHeadUrl;
    }
}
