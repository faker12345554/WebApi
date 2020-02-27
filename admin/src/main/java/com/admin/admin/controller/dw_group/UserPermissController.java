package com.admin.admin.controller.dw_group;

import com.admin.admin.entity.dw_group.Condition;
import com.admin.admin.entity.dw_userpermission.UserPermissionGroup;
import com.admin.admin.service.dw_group.GroupService;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@Api(value="权限组管理controller",tags={"权限组管理(即角色)"})
@RestController
@RequestMapping("/Permiss")
public class UserPermissController {

    @Autowired
    private GroupService groupService;

    private ResponseResult result = new ResponseResult();

    @UserLoginToken
    @ApiOperation("新增组信息")
    @PostMapping("/AddGroup")
    public ResponseResult saveGroup(@RequestBody UserPermissionGroup group, HttpServletResponse response) {

        if (groupService.selectByName(group.getPermissionname()) > 0) {
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage("该角色已经存在！");
            return result.setData("");
        }else if(group.getMenuList().size()==0){
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage("必需为该角色分配可操作菜单！");
            return result.setData("");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(groupService.saveGroup(group));
    }

    @UserLoginToken
    @ApiOperation("修改组信息")
    @PostMapping("/UpdateGroup")
    public ResponseResult updateGroup(@RequestBody UserPermissionGroup group, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( groupService.updateGroup(group));
    }

    @UserLoginToken
    @ApiOperation("删除组信息")
    @GetMapping("/DelGroup")
    public ResponseResult deleteGroup(@RequestParam boolean flag,
                                      @RequestParam int GroupId, HttpServletResponse response) {
        if (flag != true) {
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
        return result.setData(groupService.deleteGroup(!flag, GroupId));
    }

    @UserLoginToken
    @ApiOperation("获取组信息")
    @GetMapping("/GetGroup")
    public ResponseResult getGroup(@RequestParam int id, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( groupService.getGroup(id));
    }

    @UserLoginToken
    @ApiOperation("权限组列表")
    @PostMapping("GetList")
    public ResponseResult listGroup(@RequestBody Condition condition, HttpServletResponse response) {

        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( groupService.listGroup(condition));
    }

    @GetMapping("/Menutree")
    public ResponseResult GetMenuList(){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( groupService.GetMenuList());
    }

    @ApiOperation("获取组信息")
    @GetMapping("/GetpermissionName")
    public ResponseResult GetpermissionName() {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( groupService.GetpermissionName());
    }
}
