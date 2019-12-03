package com.admin.admin.service.user;

import com.admin.admin.dao.user.UserPermissionGroupDao;
import com.admin.admin.entity.user.UserPermissionGroup;
import com.admin.model.ParamterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private UserPermissionGroupDao GroupDao;

    //新增
    public int SaveGroup(UserPermissionGroup UserGroup){
        return GroupDao.SaveUserGroup(UserGroup);
    }
    //修改
    public int UpdateGroup(UserPermissionGroup UserGroup){
        return GroupDao.UpdateGroup(UserGroup);
    }
    //删除
    public int DeleteGroup(ParamterModel Paramter) {
        return GroupDao.DeleteGroup(Paramter);
    }

    //获取组信息
    public UserPermissionGroup GetGroup(int id){
        return GroupDao.GetGroup(id);
    }

    //组列表
    public List<UserPermissionGroup> ListGroup(boolean flag){
        return GroupDao.ListGroup(flag);
    }
}
