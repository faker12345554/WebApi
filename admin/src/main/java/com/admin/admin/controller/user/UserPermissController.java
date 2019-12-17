package com.admin.admin.controller.user;

import com.admin.admin.entity.user.UserPermissionGroup;
import com.admin.admin.service.user.GroupService;
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

    private ResponseResult result=new ResponseResult();

    @ApiOperation("新增组信息")
    @PostMapping("/AddGroup")
    public ResponseResult saveGroup(@RequestBody(required = false)UserPermissionGroup group, HttpServletResponse response){
        if (groupService.selectByName(group.getPermissionname())>0){
            return result.setMessage("该权限组已经存在！");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(groupService.saveGroup(group));
    }

    @ApiOperation("修改组信息")
    @PostMapping("/UpdateGroup")
    public ResponseResult updateGroup(@RequestBody(required = false) UserPermissionGroup group,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(groupService.updateGroup(group));
    }

    @ApiOperation("删除组信息")
    @GetMapping("/DelGroup")
    public ResponseResult deleteGroup(@RequestParam(required = false) boolean flag, @RequestParam int GroupId, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());

        return result.setData(groupService.deleteGroup(flag,GroupId));
    }

    @ApiOperation("获取组信息")
    @GetMapping("/GetGroup")
    public ResponseResult getGroup(@RequestParam(required = false) int id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(groupService.getGroup(id));
    }

    @ApiOperation("权限组列表")
    @GetMapping("GetList")
    public ResponseResult listGroup(@RequestParam(required = false) boolean flag,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(groupService.listGroup(flag));
    }

}
