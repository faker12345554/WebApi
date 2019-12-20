package com.admin.admin.service.dw_group;

import com.admin.admin.dao.dw_userpermission.UserPermissionGroupDao;
import com.admin.admin.entity.dw_userpermission.UserPermissionGroup;

import com.admin.page.PageBean;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private UserPermissionGroupDao GroupDao;
    private ResponseResult result = new ResponseResult();

    //新增
    public ResponseResult saveGroup(UserPermissionGroup UserGroup) {
        if (GroupDao.selectByName(UserGroup.getPermissionname()) > 0) {
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setMessage("该权限组已经存在！");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(GroupDao.saveUserGroup(UserGroup));
    }

    //修改
    public ResponseResult updateGroup(UserPermissionGroup UserGroup) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(GroupDao.updateGroup(UserGroup));
    }

    //删除
    public ResponseResult deleteGroup(boolean flag, int GroupId) {
        if (flag == true) {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("参数'flag'输入错误");
        } else if (GroupDao.getGroup(GroupId) == null) {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("参数'GroupId'输入错误,权限组不存在");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(GroupDao.deleteGroup(flag, GroupId));
    }

    //获取组信息
    public ResponseResult getGroup(int id) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(GroupDao.getGroup(id));
    }

    //组列表
    public ResponseResult listGroup(boolean flag, int PageSize, int PageIndex) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(PageIndex, PageSize);

        List<UserPermissionGroup> allItems = GroupDao.listGroup(flag);
        PageInfo<UserPermissionGroup> info = new PageInfo<>(allItems);//全部商品
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<UserPermissionGroup> pageData = new PageBean<>(PageIndex, PageSize, countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allItems);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(pageData);
    }

}
