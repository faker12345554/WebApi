package com.admin.admin.controller.dw_group;

import com.admin.admin.entity.dw_userpermission.UserPermissionGroup;
import com.admin.admin.service.dw_group.GroupService;
import com.common.common.result.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Permiss")
public class UserPermissController {

    @Autowired
    private GroupService groupService;

    @ApiOperation("新增组信息")
    @PostMapping("/AddGroup")
    public ResponseResult saveGroup(@RequestBody(required = false) UserPermissionGroup group, HttpServletResponse response) {

        return groupService.saveGroup(group);
    }

    @ApiOperation("修改组信息")
    @PostMapping("/UpdateGroup")
    public ResponseResult updateGroup(@RequestBody(required = false) UserPermissionGroup group, HttpServletResponse response) {

        return groupService.updateGroup(group);
    }

    @ApiOperation("删除组信息")
    @GetMapping("/DelGroup")
    public ResponseResult deleteGroup(@RequestParam(required = false) boolean flag,
                                      @RequestParam int GroupId, HttpServletResponse response) {
        return groupService.deleteGroup(flag, GroupId);
    }

    @ApiOperation("获取组信息")
    @GetMapping("/GetGroup")
    public ResponseResult getGroup(@RequestParam(required = false) int id, HttpServletResponse response) {

        return groupService.getGroup(id);
    }

    @ApiOperation("权限组列表")
    @GetMapping("GetList")
    public ResponseResult listGroup(@RequestParam(required = false) boolean flag, int PageSize, int PageIndex, HttpServletResponse response) {

        return groupService.listGroup(flag, PageSize, PageIndex);
    }

}
