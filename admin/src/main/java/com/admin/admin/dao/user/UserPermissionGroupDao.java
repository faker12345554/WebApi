package com.admin.admin.dao.user;

import com.admin.admin.entity.user.UserPermissionGroup;
import com.admin.model.ParamterModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPermissionGroupDao {

    //新增
    int SaveUserGroup( UserPermissionGroup userGroup);
    //修改
    int UpdateGroup(UserPermissionGroup  userGroup);
    //删除
    int DeleteGroup(ParamterModel Paramter);

    //获取
    UserPermissionGroup GetGroup(@Param("id") int id);

    //权限组列表
    List<UserPermissionGroup> ListGroup(@Param("flag") boolean flag);
}
