package com.prisonapp.business.entity.dw_message;


public class MessageModel {

    private long id;
    private String modular;
    private String modularname;
    private String content;
    private String personid;
    private java.sql.Timestamp messagetime;
    private String readmessage;
    private long detailtype;
    private String detailtypename;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
    }


    public String getModularname() {
        return modularname;
    }

    public void setModularname(String modularname) {
        this.modularname = modularname;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }


    public java.sql.Timestamp getMessagetime() {
        return messagetime;
    }

    public void setMessagetime(java.sql.Timestamp messagetime) {
        this.messagetime = messagetime;
    }


    public String getReadmessage() {
        return readmessage;
    }

    public void setReadmessage(String readmessage) {
        this.readmessage = readmessage;
    }


    public long getDetailtype() {
        return detailtype;
    }

    public void setDetailtype(long detailtype) {
        this.detailtype = detailtype;
    }


    public String getDetailtypename() {
        return detailtypename;
    }

    public void setDetailtypename(String detailtypename) {
        this.detailtypename = detailtypename;
    }

}
