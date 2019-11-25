package com.person.person.Personnel.Entity;

public class Guaranteeinformation {
    private int id;
    private String guarantee_name;
    private int Money;
    private String Card;
    private String Contact;
    private String Workunit;

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
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }

    public String getCard() {
        return Card;
    }

    public void setCard(String card) {
        Card = card;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getWorkunit() {
        return Workunit;
    }

    public void setWorkunit(String workunit) {
        Workunit = workunit;
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
