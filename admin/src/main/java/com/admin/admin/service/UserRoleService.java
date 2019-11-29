package com.admin.admin.service;

import com.admin.admin.dao.UserRoleDao;
import com.admin.admin.entity.Menu;
import com.admin.admin.entity.UserRole;
import com.admin.model.ParamterModel;
import com.admin.model.UserRoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    //新增
    public int SaveUserRole(UserRole userRole){return userRoleDao.SaveUserRole(userRole);}

    //修改
    public int UpdateUserRole(UserRole userRole){return userRoleDao.UpdateUserRole(userRole);}
    //删除
    public int DeleteUserRole(ParamterModel Paramter){return userRoleDao.DeleteUserRole(Paramter);}
    //获取所以菜单
    public List<Menu> ListMenu(int UserId){return userRoleDao.ListMenu(UserId);}
    //获取用户拥有的权限
    public List<UserRoleModel> ListUserRole(int id){return userRoleDao.ListUserRole(id);}
}
