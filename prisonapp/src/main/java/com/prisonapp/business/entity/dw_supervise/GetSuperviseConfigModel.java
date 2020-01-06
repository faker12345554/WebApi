package com.prisonapp.business.entity.dw_supervise;

import java.util.Date;
public class GetSuperviseConfigModel {
    private LocationModel location;
    private Battery battery;
    private String lastEditTime;

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

    public String getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        String str =String.valueOf(lastEditTime.getTime());
        this.lastEditTime = str;
    }
}
