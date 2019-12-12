package com.admin.admin.entity.person;

public class GuaranteeInformation {
    private int id;
    private String guarantee_name;
    private int money;
    private String card;
    private String contact;
    private String workunit;
    private String person_id;

    public String getPerson_id() {
        return person_id;
    }

    public GuaranteeInformation setPerson_id(String person_id) {
        this.person_id = person_id;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuarantee_name() {
        return guarantee_name;
    }

    public void setGuarantee_name(String guarantee_name) {
        this.guarantee_name = guarantee_name;
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
