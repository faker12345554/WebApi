package com.admin.admin.controller.dw_group;

import com.admin.admin.entity.dw_userpermission.UserPermissionGroup;
import com.admin.admin.service.dw_group.GroupService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Permiss")
public class UserPermissController {

    @Autowired
    private GroupService groupService;

    private ResponseResult result = new ResponseResult();

    @ApiOperation("新增组信息")
    @PostMapping("/AddGroup")
    public ResponseResult saveGroup(@RequestBody(required = false) UserPermissionGroup group, HttpServletResponse response) {

        if (groupService.selectByName(group.getPermissionname()) > 0) {
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("该权限组已经存在！");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(groupService.saveGroup(group));
    }

    @ApiOperation("修改组信息")
    @PostMapping("/UpdateGroup")
    public ResponseResult updateGroup(@RequestBody(required = false) UserPermissionGroup group, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( groupService.updateGroup(group));
    }

    @ApiOperation("删除组信息")
    @GetMapping("/DelGroup")
    public ResponseResult deleteGroup(@RequestParam(required = false) boolean flag,
                                      @RequestParam int GroupId, HttpServletResponse response) {
        if (flag == true) {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("参数'flag'输入错误");
        } else if (groupService.getGroup(GroupId) == null) {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("参数'GroupId'输入错误,权限组不存在");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(groupService.deleteGroup(flag, GroupId));
    }

    @ApiOperation("获取组信息")
    @GetMapping("/GetGroup")
    public ResponseResult getGroup(@RequestParam(required = false) int id, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( groupService.getGroup(id));
    }

    @ApiOperation("权限组列表")
    @GetMapping("GetList")
    public ResponseResult listGroup(@RequestParam(required = false) boolean flag, int PageSize, int PageIndex, HttpServletResponse response) {

        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( groupService.listGroup(flag, PageSize, PageIndex));
    }

}
