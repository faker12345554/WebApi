package com.admin.model.coordina;

import java.util.List;

public class RangeMap {
    private String personid;
    private String Type;

    public String getType() {
        return Type;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public RangeMap setType(String type) {
        Type = type;
        return this;
    }

    public String getAreaname() {
        return areaname;
    }

    public RangeMap setAreaname(String areaname) {
        this.areaname = areaname;
        return this;
    }

    public List<Longitude> getPosition() {
        return position;
    }

    public RangeMap setPosition(List<Longitude> position) {
        this.position = position;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public RangeMap setStatus(boolean status) {
        this.status = status;
        return this;
    }

    private String areaname;
    private List<Longitude> position;
    private boolean status;

}
