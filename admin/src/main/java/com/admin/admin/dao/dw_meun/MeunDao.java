package com.admin.admin.dao.dw_meun;

import com.admin.admin.entity.dw_menu.Menu;

import java.util.List;

public interface MeunDao {

    List<Menu> GetMenuList();

    int delMeun(boolean falg,int id);

    Menu UpdateMeun(Menu menu);
}
