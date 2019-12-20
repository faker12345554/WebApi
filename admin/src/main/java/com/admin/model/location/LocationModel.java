package com.admin.model.location;

public class LocationModel {
    private String suspectstatus;
    private String personname;
    private String gender;
    private int age;
    private String address;
    private String policestation;
    private String sponsor_alarm;
    private String areaname;
    private String Starttime;
    private String casetype;

    public String getSuspectstatus() {
        return suspectstatus;
    }

    public LocationModel setSuspectstatus(String suspectstatus) {
        this.suspectstatus = suspectstatus;
        return this;
    }

    public String getPersonname() {
        return personname;
    }

    public LocationModel setPersonname(String personname) {
        this.personname = personname;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public LocationModel setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getAge() {
        return age;
    }

    public LocationModel setAge(int age) {
        this.age = age;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public LocationModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPolicestation() {
        return policestation;
    }

    public LocationModel setPolicestation(String policestation) {
        this.policestation = policestation;
        return this;
    }

    public String getStarttime() {
        return Starttime;
    }

    public LocationModel setStarttime(String starttime) {
        Starttime = starttime;
        return this;
    }

    public String getSponsor_alarm() {
        return sponsor_alarm;
    }

    public LocationModel setSponsor_alarm(String sponsor_alarm) {
        this.sponsor_alarm = sponsor_alarm;
        return this;
    }

    public String getAreaname() {
        return areaname;
    }

    public LocationModel setAreaname(String areaname) {
        this.areaname = areaname;
        return this;
    }


    public String getCasetype() {
        return casetype;
    }

    public LocationModel setCasetype(String casetype) {
        this.casetype = casetype;
        return this;
    }


}
