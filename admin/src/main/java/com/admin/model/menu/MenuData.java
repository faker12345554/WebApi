package com.admin.model.menu;

import sun.tools.jconsole.inspector.XObject;

public class MenuData {
    private String title;

    public String getTitle() {
        return title;
    }

    public boolean isAffix() {
        return affix;
    }

    public String getIcon() {
        return icon;
    }

    public MenuData setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public MenuData setAffix(boolean affix) {
        this.affix = affix;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    private boolean affix;

    private String icon;
    public MenuData(){

    }

    public MenuData(String icon){
       this.icon=icon;

    }
    public MenuData(String title,boolean affix){
        this.title=title;
        this.affix=affix;

    }

}
