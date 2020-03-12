package com.admin.model.Appstatistics;

public class monthnumber {
    private int Bailnumber;
    private String month;
    private int supervisionnumber;

    public int getBailnumber() {
        return Bailnumber;
    }

    public monthnumber setBailnumber(int bailnumber) {
        Bailnumber = bailnumber;
        return this;
    }

    public String getMonth() {
        return month;
    }

    public monthnumber setMonth(String month) {
        this.month = month;
        return this;
    }

    public int getSupervisionnumber() {
        return supervisionnumber;
    }

    public monthnumber setSupervisionnumber(int supervisionnumber) {
        this.supervisionnumber = supervisionnumber;
        return this;
    }


}
