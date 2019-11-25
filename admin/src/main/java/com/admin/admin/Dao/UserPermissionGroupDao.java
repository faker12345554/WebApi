package com.admin.admin.Dao;

import com.admin.admin.Entity.UserPermissionGroup;
import com.admin.model.ParamterModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPermissionGroupDao {

    //新增
    int AddUserGroup(@Param("userGroup") UserPermissionGroup userGroup);
    //修改
    int UpdateGroup(@Param("userGroup")UserPermissionGroup  userGroup);
    //删除
    int DelGroup(@Param("Paramter") ParamterModel Paramter);

    //获取
    UserPermissionGroup GetGroup(@Param("id") int id);

    //权限组列表
    List<UserPermissionGroup> GetList(@Param("flag") boolean flag);
}
