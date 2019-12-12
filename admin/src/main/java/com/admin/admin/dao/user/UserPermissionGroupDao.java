package com.admin.admin.dao.user;

import com.admin.admin.entity.user.UserPermissionGroup;
import com.admin.model.ParamterModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPermissionGroupDao {

    //新增
    int saveUserGroup( UserPermissionGroup userGroup);
    //修改
    int updateGroup(UserPermissionGroup  userGroup);
    //删除
    int deleteGroup(ParamterModel Paramter);

    //获取
    UserPermissionGroup getGroup(@Param("id") int id);

    //权限组列表
    List<UserPermissionGroup> listGroup(@Param("flag") boolean flag);

    int selectByName(String Name);
}
