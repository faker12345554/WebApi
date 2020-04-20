package com.admin.tool;

public enum EnumDemo {
    jiankong(1,"位置监控"),face(2,"视频签到"),sheng(3,"语音签到"),dian(4,"低电量报警");

    private int code;
    private String msg;
    private EnumDemo(int ordinal, String name) {
        this.code = ordinal;
        this.msg = name;
    }

        public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
