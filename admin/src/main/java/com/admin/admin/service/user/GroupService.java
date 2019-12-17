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
    public int saveGroup(UserPermissionGroup UserGroup){
        return GroupDao.saveUserGroup(UserGroup);
    }
    //修改
    public int updateGroup(UserPermissionGroup UserGroup){
        return GroupDao.updateGroup(UserGroup);
    }
    //删除
    public int deleteGroup(boolean flag,int GroupId) {
        return GroupDao.deleteGroup(flag,GroupId);
    }

    //获取组信息
    public UserPermissionGroup getGroup(int id){
        return GroupDao.getGroup(id);
    }

    //组列表
    public List<UserPermissionGroup> listGroup(boolean flag){
        return GroupDao.listGroup(flag);
    }

    public int selectByName(String Name){
        return GroupDao.selectByName(Name);
    }
}
