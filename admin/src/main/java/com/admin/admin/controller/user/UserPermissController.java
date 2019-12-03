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
    public ResponseResult SaveGroup(@RequestBody(required = false)UserPermissionGroup group, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(groupService.SaveGroup(group));
    }

    @PostMapping("/UpdateGroup")
    public ResponseResult UpdateGroup(@RequestBody(required = false) UserPermissionGroup group,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(groupService.UpdateGroup(group));
    }

    @PostMapping("/DelGroup")
    public ResponseResult DeleteGroup(@RequestBody(required = false) ParamterModel Paramter, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());

        return result.setData(groupService.DeleteGroup(Paramter));
    }

    @GetMapping("/GetGroup")
    public ResponseResult GetGroup(@RequestParam(required = false) int id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(groupService.GetGroup(id));
    }

    @GetMapping("GetList")
    public ResponseResult ListGroup(@RequestParam(required = false) boolean flag,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(groupService.ListGroup(flag));
    }

}
