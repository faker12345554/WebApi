package com.admin.admin.entity.dw_guarant;

public class GuaranteeInformation {
    private int id;
    private String guaranteename;
    private int money;
    private String card;
    private String contact;
    private String workunit;
    private String personid;

    public String getGuaranteename() {
        return guaranteename;
    }

    public void setGuaranteename(String guaranteename) {
        this.guaranteename = guaranteename;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWorkunit() {
        return workunit;
    }

    public void setWorkunit(String workunit) {
        this.workunit = workunit;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private String Address;
    private boolean status;
}
