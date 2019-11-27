package com.admin.admin.Controller;

import com.admin.admin.Entity.Menu;
import com.admin.admin.Entity.UserPermissionGroup;
import com.admin.admin.Entity.UserRole;
import com.admin.admin.Service.UserRoleService;
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
    @PostMapping("/DelUserRole")
    public ResponseResult DelUserRole(@RequestBody(required = false) ParamterModel Paramter, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.DelUserRole(Paramter));
    }

    @GetMapping("/GetList")
    public ResponseResult<Menu> GetList(@RequestParam(required = false) int UserId,HttpServletResponse response){

        List<Parentmenu> Menu=new ArrayList<Parentmenu>();

        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        //菜单
        List<Menu> MenuList=userRoleService.GetList(UserId);
        List<Menu> mainList=new ArrayList<Menu>();
        for (Menu item : MenuList) {
            if (item.getTop_id()==0){
                mainList.add(item);
            }


        }

        for (Menu item: mainList){
            Parentmenu model=new Parentmenu();
            Menudata Title=new Menudata();
            Title.setTitle(item.getMenu_name());
            Title.setIcon(item.getIcon());
            model.setPath(item.getPath());
            model.setComponent(item.getComponent());
            model.setRedirect(item.getRedirect());
            model.setName(item.getName());
            model.setMeta(Title);
          
            List<Submenu> SubList=new ArrayList<Submenu>();
            for (Menu Me:MenuList){
                if (Me.getTop_id()==item.getMenu_id()){
                    Menudata data=new Menudata();
                    Submenu Sub=new Submenu();
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
    public ResponseResult<UserRoleModel> GetRoleList(@RequestParam(required = false) int id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userRoleService.GetRoleList(id));
    }
}
