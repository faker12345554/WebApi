package com.admin.admin.controller.user;

import com.admin.admin.entity.user.UserPermissionGroup;
import com.admin.admin.service.user.GroupService;
import com.admin.model.ParamterModel;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Permiss")
public class UserPermissController {

    @Autowired
    private GroupService groupService;

    private ResponseResult result=new ResponseResult();

    @PostMapping("/AddGroup")
    public ResponseResult saveGroup(@RequestBody(required = false)UserPermissionGroup group, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(groupService.saveGroup(group));
    }

    @PostMapping("/UpdateGroup")
    public ResponseResult updateGroup(@RequestBody(required = false) UserPermissionGroup group,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(groupService.updateGroup(group));
    }

    @PostMapping("/DelGroup")
    public ResponseResult deleteGroup(@RequestBody(required = false) ParamterModel Paramter, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());

        return result.setData(groupService.deleteGroup(Paramter));
    }

    @GetMapping("/GetGroup")
    public ResponseResult getGroup(@RequestParam(required = false) int id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(groupService.getGroup(id));
    }

    @GetMapping("GetList")
    public ResponseResult listGroup(@RequestParam(required = false) boolean flag,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(groupService.listGroup(flag));
    }

}
