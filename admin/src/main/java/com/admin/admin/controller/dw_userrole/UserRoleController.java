package com.admin.admin.controller.dw_userrole;

import com.admin.admin.entity.dw_menu.Menu;
import com.admin.admin.entity.dw_userrole.UserRole;
import com.admin.admin.service.dw_userrole.UserRoleService;
import com.admin.model.userrole.UserRoleModel;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@Api(value="用户权限管理Controller",tags={"用户权限管理"})
@RestController
@RequestMapping("/UserRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;



    @UserLoginToken
    @ApiOperation("新增用户权限")
    @PostMapping("/AddUserRole")
    public ResponseResult saveUserRole(@RequestBody UserRole userRole, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.saveUserRole(userRole));
    }
    @UserLoginToken
    @ApiOperation("修改用户权限")
    @PostMapping("/UpdateUserRole")
    public ResponseResult updateUserRole(@RequestBody UserRole userRole, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( userRoleService.updateUserRole(userRole));
    }
    @UserLoginToken
    @ApiOperation("删除用户权限")
    @GetMapping("/DelUserRole")
    public ResponseResult deleteUserRole(@ApiParam(name = "flag", value = "状态")boolean flag,
                                         @ApiParam(name = "UserRoleId", value = "权限组id") int UserRoleId) {
        ResponseResult result = new ResponseResult();
        if (flag != true) {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("参数'flag'输入错误");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( userRoleService.deleteUserRole(!flag, UserRoleId));
    }

    //@UserLoginToken@RequestParam int UserId, HttpServletResponse response
    @ApiOperation("菜单")
    @GetMapping("/GetList")
    public ResponseResult<Menu> listMenu() {
        ResponseResult result = new ResponseResult();
        if (userRoleService.listMenu().isEmpty()) {
            result.setCode(ResultCode.NULLDATA.getCode());
            result.setMessage(ResultCode.NULLDATA.getMessage());
            return result.setData("该用户没有任何权限,请联系管理员");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( userRoleService.listMenu());
    }
    @UserLoginToken
    @ApiOperation("用户权限列表")
    @GetMapping("/GetRoleList")
    public ResponseResult<UserRoleModel> listUserRole( @ApiParam(name = "id", value = "用户id") int id,
                                                       @ApiParam(name = "PageSize", value = "页面大小") int PageSize,
                                                       @ApiParam(name = "PageIndex", value = "页码")int PageIndex) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.listUserRole(id, PageSize, PageIndex));
    }
}
