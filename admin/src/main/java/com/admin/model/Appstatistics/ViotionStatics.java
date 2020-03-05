package com.admin.model.Appstatistics;

public class ViotionStatics {

    private int NormalNumber;//正常
    private int MinorNumber;//轻微
    private int CriticalNumber;//严重
    private String AreaCode;
    private String AreaName;

    public int getNormalNumber() {
        return NormalNumber;
    }

    public ViotionStatics setNormalNumber(int normalNumber) {
        NormalNumber = normalNumber;
        return this;
    }

    public int getMinorNumber() {
        return MinorNumber;
    }

    public ViotionStatics setMinorNumber(int minorNumber) {
        MinorNumber = minorNumber;
        return this;
    }

    public int getCriticalNumber() {
        return CriticalNumber;
    }

    public ViotionStatics setCriticalNumber(int criticalNumber) {
        CriticalNumber = criticalNumber;
        return this;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public ViotionStatics setAreaCode(String areaCode) {
        AreaCode = areaCode;
        return this;
    }

    public String getAreaName() {
        return AreaName;
    }

    public ViotionStatics setAreaName(String areaName) {
        AreaName = areaName;
        return this;
    }
}
