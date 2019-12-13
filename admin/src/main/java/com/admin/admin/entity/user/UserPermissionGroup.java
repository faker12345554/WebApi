package com.admin.admin.entity.user;

import java.util.Date;

public class UserPermissionGroup {
    private int permissionid;
    private String permissionname;
    private Date createtime;
    private int createid;
    private String createname;
    private boolean status;

    public int getPermissionid() {
        return permissionid;
    }

    public void setPermissionid(int permissionid) {
        this.permissionid = permissionid;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
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

    public boolean isStatus() {
        return status;
    }

    public UserPermissionGroup setStatus(boolean status) {
        this.status = status;
        return this;
    }


}
