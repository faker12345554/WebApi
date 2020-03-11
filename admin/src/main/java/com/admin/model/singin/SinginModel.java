package com.admin.model.singin;

import com.admin.model.person.PersonModel;

import java.util.Date;

public class SinginModel extends PersonModel {
    private int id;
    private String reportstatus;
    private String reporttype;
    private String address;
    private String activityarea;
    private String createtime;
    private float durationtime;

    public String getTypename() {
        return typename;
    }

    public SinginModel setTypename(String typename) {
        this.typename = typename;
        return this;
    }

    private String filepath;
    private String typename;

    public String getRemark() {
        return Remark;
    }

    public SinginModel setRemark(String remark) {
        Remark = remark;
        return this;
    }

    private int type;
    private String Remark;


    public int getType() {
        return type;
    }

    public SinginModel setType(int type) {
        this.type = type;
        return this;
    }


    public String getFilepath() {
        return filepath;
    }

    public SinginModel setFilepath(String filepath) {
        this.filepath = filepath;
        return this;
    }


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

    public String getCreatetime() {
        return createtime;
    }

    public SinginModel setCreatetime(String createtime) {
        this.createtime = createtime;
        return this;
    }


}
