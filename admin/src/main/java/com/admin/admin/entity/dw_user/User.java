package com.admin.admin.entity.dw_user;

public class User {
    private int id;
    private String accountname;
    private String password;

    private int permissionid;
    private int createid;
    private String createname;
    private String areacode;
    private String phone;
    private boolean status;



    private String aliasname;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public int getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(int permissionid) {
        this.permissionid = permissionid;
    }

    public String getAliasname() {
        return aliasname;
    }

    public void setAliasname(String aliasname) {
        this.aliasname = aliasname;
    }

    public int getCreateid() {
        return createid;
    }

    public void setCreateid(int createid) {
        this.createid = createid;
    }

    public String getCreatename() {
        return createname;
    }

    public void setCreatename(String createname) {
        this.createname = createname;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
