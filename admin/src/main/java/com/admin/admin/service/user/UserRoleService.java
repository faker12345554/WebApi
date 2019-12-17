package com.admin.admin.service.user;

import com.admin.admin.dao.user.UserRoleDao;
import com.admin.admin.entity.user.Menu;
import com.admin.admin.entity.user.UserRole;
import com.admin.model.ParamterModel;
import com.admin.model.userrole.UserRoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    //新增
    public int saveUserRole(UserRole userRole){return userRoleDao.saveUserRole(userRole);}

    //修改
    public int updateUserRole(UserRole userRole){return userRoleDao.updateUserRole(userRole);}
    //删除
    public int deleteUserRole(boolean flag,int UserRoleId){return userRoleDao.deleteUserRole(flag,UserRoleId);}
    //获取所以菜单
    public List<Menu> listMenu(int UserId){return userRoleDao.listMenu(UserId);}
    //获取用户拥有的权限
    public List<UserRoleModel> listUserRole(int id){return userRoleDao.listUserRole(id);}
}
