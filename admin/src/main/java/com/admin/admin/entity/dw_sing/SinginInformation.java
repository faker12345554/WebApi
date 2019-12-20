package com.admin.admin.entity.dw_sing;

import java.util.Date;

public class SinginInformation {
    private int id;
    private  String personid;
    private int type;
    private String face;
    private String audio;

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    private int result;
    private String filepath;

    public String getReporttype() {
        return reporttype;
    }

    public SinginInformation setReporttype(String reporttype) {
        this.reporttype = reporttype;
        return this;
    }

    public String getReportstatus() {
        return reportstatus;
    }

    public SinginInformation setReportstatus(String reportstatus) {
        this.reportstatus = reportstatus;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SinginInformation setAddress(String address) {
        this.address = address;
        return this;
    }

    public float getDurationtime() {
        return durationtime;
    }

    public SinginInformation setDurationtime(float durationtime) {
        this.durationtime = durationtime;
        return this;
    }

    private Date createtime;
    private String reporttype;
    private String reportstatus;
    private String address;
    private float durationtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
