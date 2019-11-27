package com.admin.admin.Dao;

import com.admin.admin.Entity.UserPermissionGroup;
import com.admin.model.ParamterModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPermissionGroupDao {

    //新增
    int AddUserGroup( UserPermissionGroup userGroup);
    //修改
    int UpdateGroup(UserPermissionGroup  userGroup);
    //删除
    int DelGroup(ParamterModel Paramter);

    //获取
    UserPermissionGroup GetGroup(@Param("id") int id);

    //权限组列表
    List<UserPermissionGroup> GetList(@Param("flag") boolean flag);
}
