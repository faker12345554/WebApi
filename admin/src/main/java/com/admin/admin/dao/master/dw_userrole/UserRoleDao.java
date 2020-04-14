package com.admin.admin.dao.master.dw_userrole;

import com.admin.admin.entity.dw_menu.Menu;
import com.admin.admin.entity.dw_userrole.UserRole;
import com.admin.model.userrole.UserRoleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleDao {

    List<Menu> listMenu();

    //新增
    int saveUserRole(  UserRole userRole);

    //修改
    int updateUserRole( UserRole userRole);
    //删除
    int deleteUserRole(@Param("flag") boolean flag, @Param("UserRoleId") int UserRoleId);
    //获取当前用户的权限
    List<UserRoleModel> listUserRole(@Param("id") int id);


}
