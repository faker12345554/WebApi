package com.admin.admin.dao.master.dw_userpermission;

import com.admin.admin.entity.dw_group.Condition;
import com.admin.admin.entity.dw_group.PermissionName;
import com.admin.admin.entity.dw_userpermission.UserPermissionGroup;
import com.admin.admin.entity.dw_userrole.UserRole;
import com.admin.model.coordina.MenuModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserPermissionGroupDao {

    //新增
    int saveUserGroup( UserPermissionGroup UserGroup);
    //修改
    int updateGroup(UserPermissionGroup  userGroup);
    //删除
    int deleteGroup(@Param("flag") boolean flag,@Param("PermissId")int PermissId);

    //获取
    UserPermissionGroup getGroup(@Param("id") int id);

    List<MenuModel> getSomeoneMenuList(@Param("id") int id);

    //权限组列表
    List<UserPermissionGroup> listGroup(@Param("condition") Condition condition);

    int selectByName(String Name);

    /**
     * 保存分配权限
     * @param userRole
     * @return
     */
    int saveUserRole(UserRole userRole);
    /*
    获取菜单
     */
    List<MenuModel> GetMenuList();

    /**
     * 物理删除
     * @param permissionid
     * @return
     */
    int deleteUserRole( int permissionid);

    /**
     * 状态删除
     * @param permissionid
     * @return
     */
    int DelStatus(@Param("flag") boolean flag,@Param("permissionid") int permissionid);
    /**
     * 修改
     * @param userRole
     * @return
     */
  //  int updateUserRole(UserRole userRole);
    List<PermissionName> GetpermissionName();
}
