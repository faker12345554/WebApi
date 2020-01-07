package com.adminapp.model.dw_supervise;

import java.util.Date;

public class SuperviseCaseInformation {
    private String caseCategory;
    private String caseType;
    private String caseName;
    private String caseNo;
    private String receivedTime;
    private String handleUnit;
    private String handlePeson;
    private String caseDescription;

    public String getCaseCategory() {
        return caseCategory;
    }

    public void setCaseCategory(String caseCategory) {
        this.caseCategory = caseCategory;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(Date receivedTime) {
        String recevedtime=String.valueOf(receivedTime.getTime());
        this.receivedTime = recevedtime;
    }

    public String getHandleUnit() {
        return handleUnit;
    }

    public void setHandleUnit(String handleUnit) {
        this.handleUnit = handleUnit;
    }

    public String getHandlePeson() {
        return handlePeson;
    }

    public void setHandlePeson(String handlePeson) {
        this.handlePeson = handlePeson;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }
}
