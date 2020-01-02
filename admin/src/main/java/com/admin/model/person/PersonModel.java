package com.admin.model.person;

public class PersonModel {
    private String personid;

    public String getPersonid() {
        return personid;
    }

    public PersonModel setPersonid(String personid) {
        this.personid = personid;
        return this;
    }

    private String suspectstatus;
    private String personname;
    private String gender;
    private int age;
    private String sponsor;

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

    private String policestation;

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
