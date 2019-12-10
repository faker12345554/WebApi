package com.prisonapp.model;

import java.util.HashMap;

public class MessagePush {
    private String appkey;
    private String timestamp;
    private String type;
    private String device_tokens;
    private String alias_type;
    private String alias;
    private String file_id;
    private LoadModel payload;
    private HashMap<String,String> policy;
    private boolean production_mode;
    private String description;
    private boolean mipush;

    public String getAppkey() {
        return appkey;
    }

    public MessagePush setAppkey(String appkey) {
        this.appkey = appkey;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public MessagePush setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getType() {
        return type;
    }

    public MessagePush setType(String type) {
        this.type = type;
        return this;
    }

    public String getDevice_tokens() {
        return device_tokens;
    }

    public MessagePush setDevice_tokens(String device_tokens) {
        this.device_tokens = device_tokens;
        return this;
    }

    public String getAlias_type() {
        return alias_type;
    }

    public MessagePush setAlias_type(String alias_type) {
        this.alias_type = alias_type;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public MessagePush setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public String getFile_id() {
        return file_id;
    }

    public MessagePush setFile_id(String file_id) {
        this.file_id = file_id;
        return this;
    }

    public LoadModel getPayload() {
        return payload;
    }

    public MessagePush setPayload(LoadModel payload) {
        this.payload = payload;
        return this;
    }

    public HashMap<String, String> getPolicy() {
        return policy;
    }

    public MessagePush setPolicy(HashMap<String, String> policy) {
        this.policy = policy;
        return this;
    }

    public boolean isProduction_mode() {
        return production_mode;
    }

    public MessagePush setProduction_mode(boolean production_mode) {
        this.production_mode = production_mode;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MessagePush setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isMipush() {
        return mipush;
    }

    public MessagePush setMipush(boolean mipush) {
        this.mipush = mipush;
        return this;
    }

    public String getMi_activity() {
        return mi_activity;
    }

    public MessagePush setMi_activity(String mi_activity) {
        this.mi_activity = mi_activity;
        return this;
    }

    private String mi_activity;
}
