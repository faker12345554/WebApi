package com.prisonapp.business.entity.supervise;

import java.util.Date;

public class SubmitApplyLeaveModel {

    private int days;
    private String provinceCode;
    private String province;
    private String cityCode;
    private String city;
    private String districtCode ;
    private String district;
    private String reason ;
    private String reasonAudioUrl;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReasonAudioUrl() {
        return reasonAudioUrl;
    }

    public void setReasonAudioUrl(String reasonAudioUrl) {
        this.reasonAudioUrl = reasonAudioUrl;
    }
}
