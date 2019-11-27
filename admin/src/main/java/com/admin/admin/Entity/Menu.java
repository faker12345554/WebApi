package com.admin.admin.Entity;

public class Menu {
    private int menu_id;
    private String menu_name;
    private String path;
    private String name;
    private String redirect;

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
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

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean getAffix() {
        return affix;
    }

    public void setAffix(boolean affix) {
        this.affix = affix;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public boolean isAffix() {
        return affix;
    }

    public int getTop_id() {
        return top_id;
    }

    public Menu setTop_id(int top_id) {
        this.top_id = top_id;
        return this;
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
    private int order_id;
    private int top_id;
    private boolean status;
    private String component;
}
