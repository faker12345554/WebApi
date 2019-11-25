package com.admin.admin.Service;

import com.admin.admin.Dao.UserPermissionGroupDao;
import com.admin.admin.Entity.UserPermissionGroup;
import com.admin.model.ParamterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private UserPermissionGroupDao GroupDao;

    //新增
    public int AddGroup(UserPermissionGroup UserGroup){return GroupDao.AddUserGroup(UserGroup);}
    //修改
    public int UpdateGroup(UserPermissionGroup UserGroup){return GroupDao.UpdateGroup(UserGroup);}
    //删除
    public int DelGroup(ParamterModel Paramter){return GroupDao.DelGroup(Paramter);}

    //获取组信息
    public UserPermissionGroup GetGroup(int id){return GroupDao.GetGroup(id);}

    //组列表
    public List<UserPermissionGroup> GetList(boolean flag){return GroupDao.GetList(flag);}
}
