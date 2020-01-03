package com.adminapp.model.dw_supervise;

public class AgainstRuleListModel {
    private String timestamp;
    private String address;
    private String typeCode;
    private String type;
    private String violateCode;
    private String violate;
    private String reason;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getViolateCode() {
        return violateCode;
    }

    public void setViolateCode(String violateCode) {
        this.violateCode = violateCode;
    }

    public String getViolate() {
        return violate;
    }

    public void setViolate(String violate) {
        this.violate = violate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
