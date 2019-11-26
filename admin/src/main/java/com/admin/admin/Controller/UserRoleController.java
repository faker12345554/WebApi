package com.admin.admin.Controller;

import com.admin.admin.Entity.Menu;
import com.admin.admin.Entity.UserPermissionGroup;
import com.admin.admin.Entity.UserRole;
import com.admin.admin.Service.UserRoleService;
import com.admin.model.ParamterModel;
import com.admin.model.UserRoleModel;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/UserRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    private ResponseResult result=new ResponseResult();

    @PostMapping("/AddUserRole")
    public ResponseResult AddUserRole(@RequestBody(required = false) UserRole userRole, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return   result.setData(userRoleService.AddUserRole(userRole));
    }

    @PostMapping("/UpdateUserRole")
    public ResponseResult UpdateUserRole(@RequestBody(required = false) UserRole userRole, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.UpdateUserRole(userRole));
}
    @GetMapping("/DelUserRole")
    public ResponseResult DelUserRole(@RequestParam(required = false) ParamterModel Paramter, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.DelUserRole(Paramter));
    }

    @GetMapping("/GetList")
    public ResponseResult<Menu> GetList(@RequestParam(required = false) int UserId,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        List<Menu> MenuList=userRoleService.GetList(UserId);
        return result.setData(userRoleService.GetList(UserId));
    }

    @GetMapping("/GetRoleList")
    public ResponseResult<UserRoleModel> GetRoleList(@RequestParam(required = false) int id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.GetRoleList(id));
    }
}
