package com.admin.model.location;

import com.admin.model.person.PersonModel;

public class LocationModel extends PersonModel {
    private int id;
    private String address;
    private String areaname;
    private String Starttime;

    public int getId() {
        return id;
    }

    public LocationModel setId(int id) {
        this.id = id;
        return this;
    }

    private String casetype;



    public String getAddress() {
        return address;
    }

    public LocationModel setAddress(String address) {
        this.address = address;
        return this;
    }


    public String getStarttime() {
        return Starttime;
    }

    public LocationModel setStarttime(String starttime) {
        Starttime = starttime;
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
