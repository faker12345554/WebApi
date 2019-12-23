package com.admin.model.location;

import com.admin.model.person.PersonModel;

public class LocationModel extends PersonModel {
    private String address;
    private String policestation;
    private String areaname;
    private String Starttime;
    private String casetype;



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
