package com.adminapp.model.dw_supervise;

public class SuperviseBailInformation {
    private String execStartDate;
    private String execEndDate;
    private String execUnit;
    private String inChargePerson;
    private String bailCaseType;
    private String execType;
    private String keepAddress;
    private String appointAddress;

    public String getExecStartDate() {
        return execStartDate;
    }

    public void setExecStartDate(String execStartDate) {
        this.execStartDate = execStartDate;
    }

    public String getExecEndDate() {
        return execEndDate;
    }

    public void setExecEndDate(String execEndDate) {
        this.execEndDate = execEndDate;
    }

    public String getExecUnit() {
        return execUnit;
    }

    public void setExecUnit(String execUnit) {
        this.execUnit = execUnit;
    }

    public String getInChargePerson() {
        return inChargePerson;
    }

    public void setInChargePerson(String inChargePerson) {
        this.inChargePerson = inChargePerson;
    }

    public String getBailCaseType() {
        return bailCaseType;
    }

    public void setBailCaseType(String bailCaseType) {
        this.bailCaseType = bailCaseType;
    }

    public String getExecType() {
        return execType;
    }

    public void setExecType(String execType) {
        this.execType = execType;
    }

    public String getKeepAddress() {
        return keepAddress;
    }

    public void setKeepAddress(String keepAddress) {
        this.keepAddress = keepAddress;
    }

    public String getAppointAddress() {
        return appointAddress;
    }

    public void setAppointAddress(String appointAddress) {
        this.appointAddress = appointAddress;
    }
}
