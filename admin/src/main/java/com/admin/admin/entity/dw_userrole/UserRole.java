package com.admin.admin.entity.dw_userrole;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserRole {

    private int roleid;
    @ApiModelProperty(value = "权限组名称",dataType = "String")
    private String rolename;

    private int menuid;
    @ApiModelProperty(value = "创建时间",dataType = "String")
    private String createtime;
    @ApiModelProperty(value = "状态",dataType = "boolean")
    private boolean status;

    private int permissionid;

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public int getMenuid() {
        return menuid;
    }

    public int getPermissionid() {
        return permissionid;
    }

    public UserRole setPermissionid(int permissionid) {
        this.permissionid = permissionid;
        return this;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }




}
