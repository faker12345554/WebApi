package com.admin.admin.dao.user;

import com.admin.admin.entity.user.Menu;
import com.admin.admin.entity.user.UserRole;
import com.admin.model.ParamterModel;
import com.admin.model.UserRoleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleDao {

    List<Menu> ListMenu(@Param("UserId")  int UserId);

    //新增
    int SaveUserRole(  UserRole userRole);

    //修改
    int UpdateUserRole( UserRole userRole);
    //删除
    int DeleteUserRole(ParamterModel Paramter);
    //获取当前用户的权限
    List<UserRoleModel> ListUserRole(@Param("id") int id);


}
