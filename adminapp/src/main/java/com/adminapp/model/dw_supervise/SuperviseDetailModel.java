package com.adminapp.model.dw_supervise;

public class SuperviseDetailModel {
    private SuperviseBaseInformation base;
    private SuperviseCaseInformation caseInfo;
    private SuperviseBailInformation bailInfo;
    private SuperviseBailPersonInformation bailPerson;
    private SuperviseBailMoneyInformation bailMoney;
    private String lastUpdateTime;

    public SuperviseBaseInformation getBase() {
        return base;
    }

    public void setBase(SuperviseBaseInformation base) {
        this.base = base;
    }

    public SuperviseCaseInformation getCaseInfo() {
        return caseInfo;
    }

    public void setCaseInfo(SuperviseCaseInformation caseInfo) {
        this.caseInfo = caseInfo;
    }

    public SuperviseBailInformation getBailInfo() {
        return bailInfo;
    }

    public void setBailInfo(SuperviseBailInformation bailInfo) {
        this.bailInfo = bailInfo;
    }

    public SuperviseBailPersonInformation getBailPerson() {
        return bailPerson;
    }

    public void setBailPerson(SuperviseBailPersonInformation bailPerson) {
        this.bailPerson = bailPerson;
    }

    public SuperviseBailMoneyInformation getBailMoney() {
        return bailMoney;
    }

    public void setBailMoney(SuperviseBailMoneyInformation bailMoney) {
        this.bailMoney = bailMoney;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
