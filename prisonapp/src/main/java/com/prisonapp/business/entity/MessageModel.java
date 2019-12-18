package com.prisonapp.business.entity;

public class MessageModel {

    private long id;
    private long modular;
    private String content;
    private long personid;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getModular() {
        return modular;
    }

    public void setModular(long modular) {
        this.modular = modular;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public long getPersonid() {
        return personid;
    }

    public void setPersonid(long personid) {
        this.personid = personid;
    }

}
