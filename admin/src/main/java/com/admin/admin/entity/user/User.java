package com.admin.admin.entity.user;

public class User {
    private int id;
    private String account_name;
    private String password;

    private int permission_id;
    private int createid;
    private String createname;
    private String areacode;
    private String phone;
    private boolean status;

    public String getAccount_name() {
        return account_name;
    }

    public User setAccount_name(String account_name) {
        this.account_name = account_name;
        return this;
    }

    private String alias_name;



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

    public String getAlias_name() {
        return alias_name;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setAlias_name(String alias_name) {
        this.alias_name = alias_name;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public User setPermission_id(int permission_id) {
        this.permission_id = permission_id;
        return this;
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
