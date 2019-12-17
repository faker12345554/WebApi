package com.admin.admin.dao.user;

import com.admin.admin.entity.user.Menu;
import com.admin.admin.entity.user.UserRole;
import com.admin.model.ParamterModel;
import com.admin.model.userrole.UserRoleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleDao {

    List<Menu> listMenu(@Param("UserId")  int UserId);

    //新增
    int saveUserRole(  UserRole userRole);

    //修改
    int updateUserRole( UserRole userRole);
    //删除
    int deleteUserRole(@Param("flag") boolean flag, @Param("UserRoleId") int UserRoleId);
    //获取当前用户的权限
    List<UserRoleModel> listUserRole(@Param("id") int id);


}
