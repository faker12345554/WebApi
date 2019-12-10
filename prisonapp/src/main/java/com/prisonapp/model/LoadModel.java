package com.prisonapp.model;

import java.util.HashMap;

public class LoadModel {
    private String display_type;
    private HashMap<String,String> body;
    private HashMap<String,String> extra;

    public HashMap<String, String> getExtra() {
        return extra;
    }

    public LoadModel setExtra(HashMap<String, String> extra) {
        this.extra = extra;
        return this;
    }

    public String getDisplay_type() {
        return display_type;
    }

    public LoadModel setDisplay_type(String display_type) {
        this.display_type = display_type;
        return this;
    }

    public HashMap<String, String> getBody() {
        return body;
    }

    public LoadModel setBody(HashMap<String, String> body) {
        this.body = body;
        return this;
    }


}
