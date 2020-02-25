package com.admin.admin.entity.dw_userpermission;

import com.admin.model.coordina.MenuModel;

import java.util.Date;
import java.util.List;

public class UserPermissionGroup {
    private int permissionid;
    private String permissionname;
    private String createtime;
    private int createid;
    private String createname;
    private boolean status;
    private List<MenuModel> MenuList;

    public List<MenuModel> getMenuList() {
        return MenuList;
    }

    public UserPermissionGroup setMenuList(List<MenuModel> menuList) {
        MenuList = menuList;
        return this;
    }



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

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
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
