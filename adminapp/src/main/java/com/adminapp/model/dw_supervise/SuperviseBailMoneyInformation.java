package com.adminapp.model.dw_supervise;

import java.util.Date;

public class SuperviseBailMoneyInformation {
    private String amount;
    private String payDate;
    private String keepBank;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        String paydate=String.valueOf(payDate.getTime());
        this.payDate = paydate;
    }

    public String getKeepBank() {
        return keepBank;
    }

    public void setKeepBank(String keepBank) {
        this.keepBank = keepBank;
    }
}
