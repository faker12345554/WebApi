package com.admin.model.person;

public class PersonModel {
    private String personid;
    private String suspectstatus;
    private String personname;
    private String gender;
    private int age;
    private String sponsor;
    private String policestation;
    private String guid;
    private String caseno;
    private String handleunit;
    private String handlepeson;

    public String getCaseno() {
        return caseno;
    }

    public PersonModel setCaseno(String caseno) {
        this.caseno = caseno;
        return this;
    }

    public String getHandleunit() {
        return handleunit;
    }

    public PersonModel setHandleunit(String handleunit) {
        this.handleunit = handleunit;
        return this;
    }

    public String getHandlepeson() {
        return handlepeson;
    }

    public PersonModel setHandlepeson(String handlepeson) {
        this.handlepeson = handlepeson;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public PersonModel setGuid(String guid) {
        this.guid = guid;
        return this;
    }



    public String getPersonid() {
        return personid;
    }

    public PersonModel setPersonid(String personid) {
        this.personid = personid;
        return this;
    }



    public String getSponsor() {
        return sponsor;
    }

    public PersonModel setSponsor(String sponsor) {
        this.sponsor = sponsor;
        return this;
    }

    public String getPolicestation() {
        return policestation;
    }

    public PersonModel setPolicestation(String policestation) {
        this.policestation = policestation;
        return this;
    }



    public String getSuspectstatus() {
        return suspectstatus;
    }

    public PersonModel setSuspectstatus(String suspectstatus) {
        this.suspectstatus = suspectstatus;
        return this;
    }

    public String getPersonname() {
        return personname;
    }

    public PersonModel setPersonname(String personname) {
        this.personname = personname;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public PersonModel setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getAge() {
        return age;
    }

    public PersonModel setAge(int age) {
        this.age = age;
        return this;
    }



}
