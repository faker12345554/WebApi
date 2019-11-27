package com.person.person.Personnel.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class Personinformation implements Serializable {

    private static final long serialVersionUID = 1L;

    private int  personid;
    private String person_name;
    private String gender;
    private int Age;
    private Date Birthdate;
    private String Card;
    private String Workunit;
    private Date Bailoutbegindate;
    private Date Bailoutenddate;
    private String Sponsor;
    private String sponsor_alarm;
    private String Contact;
    private String Address;
    private String wechat_number;
    private String qq_number;
    private boolean status;

    public int getPersonid() {
        return personid;
    }

    public Personinformation setPersonid(int personid) {
        this.personid = personid;
        return this;
    }

    public String getPerson_name() {
        return person_name;
    }

    public Personinformation setPerson_name(String person_name) {
        this.person_name = person_name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Personinformation setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getAge() {
        return Age;
    }

    public Personinformation setAge(int age) {
        Age = age;
        return this;
    }

    public Date getBirthdate() {
        return Birthdate;
    }

    public Personinformation setBirthdate(Date birthdate) {
        Birthdate = birthdate;
        return this;
    }

    public String getCard() {
        return Card;
    }

    public Personinformation setCard(String card) {
        Card = card;
        return this;
    }

    public String getWorkunit() {
        return Workunit;
    }

    public Personinformation setWorkunit(String workunit) {
        Workunit = workunit;
        return this;
    }

    public Date getBailoutbegindate() {
        return Bailoutbegindate;
    }

    public Personinformation setBailoutbegindate(Date bailoutbegindate) {
        Bailoutbegindate = bailoutbegindate;
        return this;
    }

    public Date getBailoutenddate() {
        return Bailoutenddate;
    }

    public Personinformation setBailoutenddate(Date bailoutenddate) {
        Bailoutenddate = bailoutenddate;
        return this;
    }

    public String getSponsor() {
        return Sponsor;
    }

    public Personinformation setSponsor(String sponsor) {
        Sponsor = sponsor;
        return this;
    }

    public String getSponsor_alarm() {
        return sponsor_alarm;
    }

    public Personinformation setSponsor_alarm(String sponsor_alarm) {
        this.sponsor_alarm = sponsor_alarm;
        return this;
    }

    public String getContact() {
        return Contact;
    }

    public Personinformation setContact(String contact) {
        Contact = contact;
        return this;
    }

    public String getAddress() {
        return Address;
    }

    public Personinformation setAddress(String address) {
        Address = address;
        return this;
    }

    public String getWechat_number() {
        return wechat_number;
    }

    public Personinformation setWechat_number(String wechat_number) {
        this.wechat_number = wechat_number;
        return this;
    }

    public String getQq_number() {
        return qq_number;
    }

    public Personinformation setQq_number(String qq_number) {
        this.qq_number = qq_number;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public Personinformation setStatus(boolean status) {
        this.status = status;
        return this;
    }
}
