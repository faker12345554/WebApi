package com.adminapp.model.dw_supervise;

public class AgainstRuleModel {
    private String typeCode;
    private String type;
    private String violateCode;
    private String violate;
    private int count;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
