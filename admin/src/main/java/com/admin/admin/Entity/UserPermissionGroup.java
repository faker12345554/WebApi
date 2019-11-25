package com.admin.admin.Entity;

import java.util.Date;

public class UserPermissionGroup {
    private int permission_id;
    private String permission_name;
    private Date create_time;
    private int create_id;

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public String getPermission_name() {
        return permission_name;
    }

    public void setPermission_name(String permission_name) {
        this.permission_name = permission_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getCreate_id() {
        return create_id;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }

    public String getCreate_persion() {
        return create_persion;
    }

    public void setCreate_persion(String create_persion) {
        this.create_persion = create_persion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private String create_persion;
    private boolean status;
}
