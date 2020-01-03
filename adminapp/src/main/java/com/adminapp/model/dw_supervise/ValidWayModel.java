package com.adminapp.model.dw_supervise;

import java.util.List;

public class ValidWayModel {
    private String code;
    private List<ValidWayListModel> validWay;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ValidWayListModel> getValidWay() {
        return validWay;
    }

    public void setValidWay(List<ValidWayListModel> validWay) {
        this.validWay = validWay;
    }
}

