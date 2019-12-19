package com.admin.admin.entity.dw_menu;

public class Menu {
    private int menuid;
    private String menuname;
    private String path;
    private String name;
    private String redirect;

    public int getMenu_id() {
        return menuid;
    }

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getTopid() {
        return topid;
    }

    public void setTopid(int topid) {
        this.topid = topid;
    }






    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getIcon() {
        return icon;
    }


    public boolean getAffix() {
        return affix;
    }

    public void setAffix(boolean affix) {
        this.affix = affix;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }



    public boolean isAffix() {
        return affix;
    }



    public boolean isStatus() {
        return status;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private String icon;
    private boolean affix;
    private boolean hidden;
    private int orderid;
    private int topid;
    private boolean status;
    private String component;
}
