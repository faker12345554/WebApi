package com.admin.model.singin;

import com.admin.model.person.PersonModel;
import com.common.common.authenticator.CalendarAdjust;

import java.util.Date;

public class SinginModel extends PersonModel {
    private int id;
    private String reportstatus;
    private String reporttype;
    private String address;
    private String activityarea;
    private String createtime;
    private String durationtime;
    private String canceltimestamp;
    private String calltimestamp;
    private int type;
    private String Remark;
    private String filepath;
    private String typename;

    public String getCanceltimestamp() {
        return canceltimestamp;
    }

    public SinginModel setCanceltimestamp(String canceltimestamp) {
        this.canceltimestamp = canceltimestamp;
        return this;
    }

    public String getCalltimestamp() {
        return calltimestamp;
    }

    public SinginModel setCalltimestamp(String calltimestamp) {
        this.calltimestamp = calltimestamp;
        return this;
    }


    public String getTypename() {
        return typename;
    }

    public SinginModel setTypename(String typename) {
        this.typename = typename;
        return this;
    }


    public String getRemark() {
        return Remark;
    }

    public SinginModel setRemark(String remark) {
        Remark = remark;
        return this;
    }


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


    public String getDurationtime() {
        return durationtime;
    }

    public SinginModel setDurationtime(String durationtime) throws Exception {
        //
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
