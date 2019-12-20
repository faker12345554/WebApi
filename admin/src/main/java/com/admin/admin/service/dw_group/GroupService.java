package com.admin.admin.service.dw_group;

import com.admin.admin.dao.dw_userpermission.UserPermissionGroupDao;
import com.admin.admin.entity.dw_userpermission.UserPermissionGroup;

import com.admin.page.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private UserPermissionGroupDao GroupDao;

    //新增
    public int saveGroup(UserPermissionGroup UserGroup) {

        return GroupDao.saveUserGroup(UserGroup);
    }

    public int selectByName(String Name) {
        return GroupDao.selectByName(Name);
    }

    //修改
    public int updateGroup(UserPermissionGroup UserGroup) {

        return GroupDao.updateGroup(UserGroup);
    }

    //删除
    public int deleteGroup(boolean flag, int GroupId) {

        return GroupDao.deleteGroup(flag, GroupId);
    }

    //获取组信息
    public UserPermissionGroup getGroup(int id) {

        return GroupDao.getGroup(id);
    }

    //组列表
    public PageBean listGroup(boolean flag, int PageSize, int PageIndex) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(PageIndex, PageSize);

        List<UserPermissionGroup> allItems = GroupDao.listGroup(flag);
        PageInfo<UserPermissionGroup> info = new PageInfo<>(allItems);//全部商品
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<UserPermissionGroup> pageData = new PageBean<>(PageIndex, PageSize, countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allItems);

        return pageData;
    }

}
