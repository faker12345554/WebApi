package com.admin.model.menu;


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

    public boolean isHidden() {
        return hidden;
    }

    public MenuData setHidden(boolean hidden) {
        this.hidden = hidden;
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

    private boolean hidden;
    public MenuData(){

    }

    public MenuData(String icon){
       this.icon=icon;

    }
    public MenuData(String title,boolean affix,boolean hidden){
        this.title=title;
        this.affix=affix;
        this.hidden=hidden;

    }

}
