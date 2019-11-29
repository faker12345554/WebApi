package com.admin.admin.controller;

import com.admin.admin.entity.Menu;
import com.admin.admin.entity.UserRole;
import com.admin.admin.service.UserRoleService;
import com.admin.model.*;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
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

    @PostMapping("/AddUserRole")
    public ResponseResult SaveUserRole(@RequestBody(required = false) UserRole userRole, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.SaveUserRole(userRole));
    }

    @PostMapping("/UpdateUserRole")
    public ResponseResult UpdateUserRole(@RequestBody(required = false) UserRole userRole, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.UpdateUserRole(userRole));
}
    @PostMapping("/DelUserRole")
    public ResponseResult DeleteUserRole(@RequestBody(required = false) ParamterModel Paramter, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.DeleteUserRole(Paramter));
    }

    @GetMapping("/GetList")
    public ResponseResult<Menu> ListMenu(@RequestParam(required = false) int UserId,HttpServletResponse response){

        List<ParentMenu> Menu=new ArrayList<ParentMenu>();

        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        //菜单
        List<Menu> MenuList=userRoleService.ListMenu(UserId);
        List<Menu> mainList=new ArrayList<Menu>();
        for (Menu item : MenuList) {
            if (item.getTop_id()==0){
                mainList.add(item);
            }
        }

        for (Menu item: mainList){
            ParentMenu model=new ParentMenu();
            MenuData Title=new MenuData();
            Title.setTitle(item.getMenu_name());
            Title.setIcon(item.getIcon());
            model.setPath(item.getPath());
            model.setComponent(item.getComponent());
            model.setRedirect(item.getRedirect());
            model.setName(item.getName());
            model.setMeta(Title);
          
            List<SonMenu> SubList=new ArrayList<SonMenu>();
            for (Menu Me:MenuList){
                if (Me.getTop_id()==item.getMenu_id()){
                    MenuData data=new MenuData();
                    SonMenu Sub=new SonMenu();
                    Sub.setPath(Me.getPath());
                    Sub.setComponent(Me.getComponent());
                    Sub.setName(Me.getName());
                    data.setTitle(Me.getMenu_name());
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

    @GetMapping("/GetRoleList")
    public ResponseResult<UserRoleModel> ListUserRole(@RequestParam(required = false) int id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.ListUserRole(id));
    }
}
