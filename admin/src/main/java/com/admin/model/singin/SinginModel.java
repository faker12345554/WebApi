package com.admin.model.singin;

import com.admin.model.person.PersonModel;

import java.util.Date;

public class SinginModel extends PersonModel {
    private int id;
    private String reportstatus;
    private String reporttype;
    private String address;
    private String activityarea;
    private Date createtime;
    private float durationtime;
    public float getDurationtime() {
        return durationtime;
    }

    public SinginModel setDurationtime(float durationtime) {
        this.durationtime = durationtime;
        return this;
    }


    public int getId() {
        return id;
    }

    public SinginModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getReportstatus() {
        return reportstatus;
    }

    public SinginModel setReportstatus(String reportstatus) {
        this.reportstatus = reportstatus;
        return this;
    }

    public String getReporttype() {
        return reporttype;
    }

    public SinginModel setReporttype(String reporttype) {
        this.reporttype = reporttype;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SinginModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getActivityarea() {
        return activityarea;
    }

    public SinginModel setActivityarea(String activityarea) {
        this.activityarea = activityarea;
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public SinginModel setCreatetime(Date createtime) {
        this.createtime = createtime;
        return this;
    }


}
