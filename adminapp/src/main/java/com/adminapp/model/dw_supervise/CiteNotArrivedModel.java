package com.adminapp.model.dw_supervise;

public class CiteNotArrivedModel {
    private String name;
    private String violateCode;
    private String violate;
    private int notArrivedCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getNotArrivedCount() {
        return notArrivedCount;
    }

    public void setNotArrivedCount(int notArrivedCount) {
        this.notArrivedCount = notArrivedCount;
    }
}
