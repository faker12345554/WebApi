package com.admin.admin.controller.dw_userrole;

import com.admin.admin.entity.dw_menu.Menu;
import com.admin.admin.entity.dw_userrole.UserRole;
import com.admin.admin.service.dw_userrole.UserRoleService;
import com.admin.model.userrole.UserRoleModel;
import com.common.common.result.ResponseResult;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/UserRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;


    @ApiOperation("新增用户权限")
    @PostMapping("/AddUserRole")
    public ResponseResult saveUserRole(@RequestBody(required = false) UserRole userRole, HttpServletResponse response) {
        return userRoleService.saveUserRole(userRole);
    }

    @ApiOperation("修改用户权限")
    @PostMapping("/UpdateUserRole")
    public ResponseResult updateUserRole(@RequestBody(required = false) UserRole userRole, HttpServletResponse response) {
        return userRoleService.updateUserRole(userRole);
    }

    @ApiOperation("删除用户权限")
    @GetMapping("/DelUserRole")
    public ResponseResult deleteUserRole(@RequestParam(required = false) boolean flag, @RequestParam int UserRoleId, HttpServletResponse response) {

        return userRoleService.deleteUserRole(flag, UserRoleId);
    }

    @ApiOperation("菜单")
    @GetMapping("/GetList")
    public ResponseResult<Menu> listMenu(@RequestParam(required = false) int UserId, HttpServletResponse response) {

        return userRoleService.listMenu(UserId);
    }

    @ApiOperation("用户权限列表")
    @GetMapping("/GetRoleList")
    public ResponseResult<UserRoleModel> listUserRole(@RequestParam(required = false) int id, int PageSize, int PageIndex, HttpServletResponse response) {

        return userRoleService.listUserRole(id, PageSize, PageIndex);
    }
}
