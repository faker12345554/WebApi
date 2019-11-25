package com.admin.admin.Dao;

import com.admin.admin.Entity.Menu;
import com.admin.admin.Entity.UserRole;
import com.admin.model.ParamterModel;
import com.admin.model.UserRoleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserRoleDao {

    List<Menu> GetList(@Param("UserId")  int UserId);

    //新增
    int AddUserRole( @Param("userRole") UserRole userRole);

    //修改
    int UpdateUserRole(@Param("userRole") UserRole userRole);
    //删除
    int DelUserRole(@Param("Paramter") ParamterModel Paramter);
    //获取当前用户的权限
    List<UserRoleModel> GetRoleList(@Param("id") int id);


}
