package com.adminapp.model.dw_supervise;

import java.util.Date;
import java.util.List;

public class LocationRecordModel {
    private float latitude;
    private float longitude;
    private int locationType;
    private String address;
    private boolean isOutBound;
    private String timestamp;
    private String areaCode;
    private List<AreaFenceModel> area;

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getLocationType() {
        return locationType;
    }

    public void setLocationType(int locationType) {
        this.locationType = locationType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getIsOutBound() {
        return isOutBound;
    }

    public void setIsOutBound(boolean isOutBound) {
        this.isOutBound = isOutBound;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        String timeStamp=String.valueOf(timestamp.getTime());
        this.timestamp = timeStamp;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public List<AreaFenceModel> getArea() {
        return area;
    }

    public void setArea(List<AreaFenceModel> area) {
        this.area = area;
    }
}
