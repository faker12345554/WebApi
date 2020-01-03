package com.adminapp.model.dw_supervise;

public class PrisonSettingModel {
    private String code;
    private String name;
    private boolean enable;

    public String getCode() {
        return code;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
