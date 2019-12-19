package com.admin.admin.entity.dw_address;

import java.util.Date;

public class AddressInformation {
    private int id;
    private String latitude;
    private String longitude;
    private String address;
    private String personid;
    private Date timesta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Date getTimesta() {
        return timesta;
    }

    public void setTimesta(Date timesta) {
        this.timesta = timesta;
    }
}
