package com.admin.admin.entity.person;

import org.apache.tomcat.jni.Time;

import java.util.Date;
import java.util.Timer;

public class Personinformation {
    private String personid;
    private String person_name;
    private String gender;
    private int age;
    private Date birthdate;
    private String card;
    private String Workunit;
    private Date Bailoutbegindate;
    private Date Bailoutenddate;

    public String getPersonid() {
        return personid;
    }

    public Personinformation setPersonid(String personid) {
        this.personid = personid;
        return this;
    }

    public String getFounderid() {
        return founderid;
    }

    public Personinformation setFounderid(String founderid) {
        this.founderid = founderid;
        return this;
    }

    public Time getFoundertime() {
        return foundertime;
    }

    public Personinformation setFoundertime(Time foundertime) {
        this.foundertime = foundertime;
        return this;
    }

    public String getModifierid() {
        return modifierid;
    }

    public Personinformation setModifierid(String modifierid) {
        this.modifierid = modifierid;
        return this;
    }

    public Time getModifiertime() {
        return modifiertime;
    }

    public Personinformation setModifiertime(Time modifiertime) {
        this.modifiertime = modifiertime;
        return this;
    }

    public String getSuspectstatus() {
        return suspectstatus;
    }

    public Personinformation setSuspectstatus(String suspectstatus) {
        this.suspectstatus = suspectstatus;
        return this;
    }

    public String getMarriage() {
        return marriage;
    }

    public Personinformation setMarriage(String marriage) {
        this.marriage = marriage;
        return this;
    }

    private String Sponsor;
    private String sponsor_alarm;
    private String Contact;
    private String Address;
    private String wechat_number;
    private String qq_number;
    private boolean status;
    private String founderid;
    private Time foundertime;
    private String modifierid;
    private Time modifiertime;
    private String suspectstatus;
    private String marriage;


    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getWorkunit() {
        return Workunit;
    }

    public void setWorkunit(String workunit) {
        Workunit = workunit;
    }

    public Date getBailoutbegindate() {
        return Bailoutbegindate;
    }

    public void setBailoutbegindate(Date bailoutbegindate) {
        Bailoutbegindate = bailoutbegindate;
    }

    public Date getBailoutenddate() {
        return Bailoutenddate;
    }

    public void setBailoutenddate(Date bailoutenddate) {
        Bailoutenddate = bailoutenddate;
    }

    public String getSponsor() {
        return Sponsor;
    }

    public void setSponsor(String sponsor) {
        Sponsor = sponsor;
    }

    public String getSponsor_alarm() {
        return sponsor_alarm;
    }

    public void setSponsor_alarm(String sponsor_alarm) {
        this.sponsor_alarm = sponsor_alarm;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getWechat_number() {
        return wechat_number;
    }

    public void setWechat_number(String wechat_number) {
        this.wechat_number = wechat_number;
    }

    public String getQq_number() {
        return qq_number;
    }

    public void setQq_number(String qq_number) {
        this.qq_number = qq_number;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
