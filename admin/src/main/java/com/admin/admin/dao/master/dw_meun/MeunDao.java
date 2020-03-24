package com.admin.admin.dao.master.dw_meun;

import com.admin.admin.entity.dw_menu.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MeunDao {

    List<Menu> GetMenuList();

    int delMeun(@Param("falg") boolean falg, @Param("id") int id);

    Menu GetMeunbyid(int id);
}
