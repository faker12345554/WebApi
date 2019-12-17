package com.admin.admin.controller.user;

import com.admin.admin.entity.user.Menu;
import com.admin.admin.entity.user.UserRole;
import com.admin.admin.service.user.UserRoleService;
import com.admin.model.*;
import com.admin.model.menu.MenuData;
import com.admin.model.menu.ParentMenu;
import com.admin.model.menu.SonMenu;
import com.admin.model.userrole.UserRoleModel;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/UserRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    private ResponseResult result=new ResponseResult();

    @ApiOperation("新增用户权限")
    @PostMapping("/AddUserRole")
    public ResponseResult saveUserRole(@RequestBody(required = false) UserRole userRole, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.saveUserRole(userRole));
    }

    @ApiOperation("修改用户权限")
    @PostMapping("/UpdateUserRole")
    public ResponseResult updateUserRole(@RequestBody(required = false) UserRole userRole, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.updateUserRole(userRole));
}
    @ApiOperation("删除用户权限")
    @GetMapping("/DelUserRole")
    public ResponseResult deleteUserRole(@RequestParam(required = false) boolean flag, @RequestParam int UserRoleId, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.deleteUserRole(flag,UserRoleId));
    }

    @ApiOperation("菜单")
    @GetMapping("/GetList")
    public ResponseResult<Menu> listMenu(@RequestParam(required = false) int UserId,HttpServletResponse response){

        List<ParentMenu> Menu=new ArrayList<ParentMenu>();

        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        //菜单
        List<Menu> MenuList=userRoleService.listMenu(UserId);
        List<Menu> mainList=new ArrayList<Menu>();
        for (Menu item : MenuList) {
            if (item.getTopid()==0){
                mainList.add(item);
            }
        }

        for (Menu item: mainList){
            ParentMenu model=new ParentMenu();
            MenuData Title=new MenuData();
            Title.setTitle(item.getMenuname());
            Title.setIcon(item.getIcon());
            model.setPath(item.getPath());
            model.setComponent(item.getComponent());
            model.setRedirect(item.getRedirect());
            model.setName(item.getName());
            model.setMeta(Title);
          
            List<SonMenu> SubList=new ArrayList<SonMenu>();
            for (Menu Me:MenuList){
                if (Me.getTopid()==item.getMenu_id()){
                    MenuData data=new MenuData();
                    SonMenu Sub=new SonMenu();
                    Sub.setPath(Me.getPath());
                    Sub.setComponent(Me.getComponent());
                    Sub.setName(Me.getName());
                    data.setTitle(Me.getMenuname());
                    data.setIcon(Me.getIcon());
                    Sub.setMeta(data);
                    SubList.add(Sub);
                }

            }
            model.setChildren(SubList);

            Menu.add(model);
        }

        return result.setData(Menu);
    }

    @ApiOperation("用户权限列表")
    @GetMapping("/GetRoleList")
    public ResponseResult<UserRoleModel> listUserRole(@RequestParam(required = false) int id, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.listUserRole(id));
    }
}
