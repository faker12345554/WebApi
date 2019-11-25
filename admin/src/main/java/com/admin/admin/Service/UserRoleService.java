package com.admin.admin.Service;

import com.admin.admin.Dao.UserRoleDao;
import com.admin.admin.Entity.Menu;
import com.admin.admin.Entity.UserRole;
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
    public int AddUserRole(UserRole userRole){return userRoleDao.AddUserRole(userRole);}

    //修改
    public int UpdateUserRole(UserRole userRole){return userRoleDao.UpdateUserRole(userRole);}
    //删除
    public int DelUserRole(ParamterModel Paramter){return userRoleDao.DelUserRole(Paramter);}
    //获取所以菜单
    public List<Menu> GetList(int UserId){return userRoleDao.GetList(UserId);}
    //获取用户拥有的权限
    public List<UserRoleModel> GetRoleList(int id){return userRoleDao.GetRoleList(id);}
}
