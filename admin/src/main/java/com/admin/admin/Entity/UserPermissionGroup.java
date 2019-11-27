package com.admin.admin.Entity;

import java.util.Date;

public class UserPermissionGroup {
    private int permission_id;
    private String permission_name;
    private Date create_time;
    private int create_id;
    private String create_name;
    private boolean status;

    public int getPermission_id() {
        return permission_id;
    }

    public UserPermissionGroup setPermission_id(int permission_id) {
        this.permission_id = permission_id;
        return this;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public UserPermissionGroup setPermission_name(String permission_name) {
        this.permission_name = permission_name;
        return this;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public UserPermissionGroup setCreate_time(Date create_time) {
        this.create_time = create_time;
        return this;
    }

    public int getCreate_id() {
        return create_id;
    }

    public UserPermissionGroup setCreate_id(int create_id) {
        this.create_id = create_id;
        return this;
    }

    public String getCreate_name() {
        return create_name;
    }

    public UserPermissionGroup setCreate_name(String create_name) {
        this.create_name = create_name;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public UserPermissionGroup setStatus(boolean status) {
        this.status = status;
        return this;
    }


}
