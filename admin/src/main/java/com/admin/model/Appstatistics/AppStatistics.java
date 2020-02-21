package com.admin.model.Appstatistics;

public class AppStatistics {
    private String Code;
    private int Totalnumber;//总人数
    private String Precisetime;

    public String getPrecisetime() {
        return Precisetime;
    }

    public AppStatistics setPrecisetime(String precisetime) {
        Precisetime = precisetime;
        return this;
    }

    public String getCode() {
        return Code;
    }

    public AppStatistics setCode(String code) {
        Code = code;
        return this;
    }

    public int getTotalnumber() {
        return Totalnumber;
    }

    public AppStatistics setTotalnumber(int totalnumber) {
        Totalnumber = totalnumber;
        return this;
    }

    public int getUsernumber() {
        return Usernumber;
    }

    public AppStatistics setUsernumber(int usernumber) {
        Usernumber = usernumber;
        return this;
    }

    public String getName() {
        return Name;
    }

    public AppStatistics setName(String name) {
        Name = name;
        return this;
    }

    private int Usernumber;//使用数量
    private String Name;
}
