package com.admin.admin.controller.person;


import com.admin.admin.entity.person.AuditorInformation;
import com.admin.admin.entity.person.LeaveInformation;
import com.admin.admin.service.person.LeaveService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Leave")
public class LeaveController {
    @Autowired
    protected LeaveService leaveService;

    private ResponseResult result = new ResponseResult();

    @ApiOperation(value = "获取个人请假信息")
    @GetMapping("/getLeave")
    public ResponseResult getLeave(@RequestParam(required = false) int id, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.getLeave(id));
    }

    @ApiOperation(value = "获取全部请假信息")
    @GetMapping("/listLeave")
    public ResponseResult listLeave() {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.listLeave());
    }

    @ApiOperation(value = "修改请假信息")
    @PostMapping("/updateLeave")
    public ResponseResult updateLeave(@RequestBody LeaveInformation leaveinformation) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.updateLeave(leaveinformation));
    }

    @ApiOperation(value = "增加审批信息")
    @PostMapping("/addAuditor")
    public ResponseResult insertAuditor(@RequestBody AuditorInformation auditorInformation, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.insertAuditor(auditorInformation));
    }

    @ApiOperation(value = "删除审批信息")
    @PostMapping("/deleteAuditor")
    public ResponseResult deleteAuditor(@ApiParam(name = "leaveOrder",value = "请假单号")@RequestParam(required = false) String leaveOrder, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.deleteAuditor(leaveOrder));
    }
}
