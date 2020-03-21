package com.admin.admin.entity.dw_guarant;

import java.util.Date;

public class GuaranteeInformation {
    private int id;
    private String personid;
    private String guaranteename;
    private int money;
    private String card;
    private String contact;

    public int getId() {
        return id;
    }

    public GuaranteeInformation setId(int id) {
        this.id = id;
        return this;
    }

    public String getPersonid() {
        return personid;
    }

    public GuaranteeInformation setPersonid(String personid) {
        this.personid = personid;
        return this;
    }

    public String getGuaranteename() {
        return guaranteename;
    }

    public GuaranteeInformation setGuaranteename(String guaranteename) {
        this.guaranteename = guaranteename;
        return this;
    }

    public int getMoney() {
        return money;
    }

    public GuaranteeInformation setMoney(int money) {
        this.money = money;
        return this;
    }

    public String getCard() {
        return card;
    }

    public GuaranteeInformation setCard(String card) {
        this.card = card;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public GuaranteeInformation setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getWorkunit() {
        return workunit;
    }

    public GuaranteeInformation setWorkunit(String workunit) {
        this.workunit = workunit;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public GuaranteeInformation setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public GuaranteeInformation setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public GuaranteeInformation setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getRelatetionship() {
        return relatetionship;
    }

    public GuaranteeInformation setRelatetionship(String relatetionship) {
        this.relatetionship = relatetionship;
        return this;
    }

    public String getJob() {
        return job;
    }

    public GuaranteeInformation setJob(String job) {
        this.job = job;
        return this;
    }

    public String getReceiptunit() {
        return receiptunit;
    }

    public GuaranteeInformation setReceiptunit(String receiptunit) {
        this.receiptunit = receiptunit;
        return this;
    }

    public String getPaydate() {
        return paydate;
    }

    public GuaranteeInformation setPaydate(String paydate) {
        this.paydate = paydate;
        return this;
    }

    public String getKeepbank() {
        return keepbank;
    }

    public GuaranteeInformation setKeepbank(String keepbank) {
        this.keepbank = keepbank;
        return this;
    }

    private String workunit;
    private String address;
    private String status;
    private String gender;
    private String relatetionship;
    private String job;
    private String receiptunit;
    private String paydate;
    private String keepbank;


}
