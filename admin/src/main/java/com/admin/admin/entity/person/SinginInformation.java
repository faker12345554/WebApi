package com.admin.admin.entity.person;

import java.util.Date;

public class SinginInformation {
    private int id;
    private  String person_id;
    private int type;
    private String face;
    private String audio;

    public String getPerson_id() {
        return person_id;
    }

    public SinginInformation setPerson_id(String person_id) {
        this.person_id = person_id;
        return this;
    }

    private int result;
    private String filepath;
    private Date createtime;

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
