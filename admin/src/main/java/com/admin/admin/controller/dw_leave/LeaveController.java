package com.admin.admin.controller.dw_leave;


import com.admin.admin.entity.dw_auditor.AuditorInformation;
import com.admin.admin.entity.dw_leave.LeaveInformation;
import com.admin.admin.service.dw_leave.LeaveService;
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

    @ApiOperation(value = "获取全部请假信息")
    @GetMapping("/listLeave")
    public ResponseResult listLeave(@RequestParam String personid,HttpServletResponse response) {

        return result.setData( leaveService.getLeave(personid));
    }

//    @ApiOperation(value = "获取个人请假信息")
//    @GetMapping("/getLeave")
//    public ResponseResult getLeave(@RequestParam(required = false) String personid, HttpServletResponse response) {
//        result.setCode(ResultCode.SUCCESS.getCode());
//        result.setMessage(ResultCode.SUCCESS.getMessage());
//        return result.setData(leaveService.getLeave(personid));
//    }


//    @ApiOperation(value = "修改请假信息")
//    @PostMapping("/updateLeave")
//    public ResponseResult updateLeave(@RequestBody LeaveInformation leaveinformation) {
//        result.setCode(ResultCode.SUCCESS.getCode());
//        result.setMessage(ResultCode.SUCCESS.getMessage());
//        return result.setData(leaveService.updateLeave(leaveinformation));
//    }

    @ApiOperation(value = "增加审批信息")
    @PostMapping("/addAuditor")
    public ResponseResult insertAuditor(@RequestBody AuditorInformation auditorInformation, HttpServletResponse response) {
        LeaveInformation leaveInformations =leaveService.getLeaveInformation(auditorInformation.getLeaveorder());
        if (leaveInformations != null) {
            int updateLeaveStatus = leaveService.updateLeaveStatus(auditorInformation);
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage(ResultCode.SUCCESS.getMessage());
            return result.setData(leaveService.insertAuditor(auditorInformation));
        } else {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("该请假单不存在");
        }
    }

    @ApiOperation(value = "删除审批信息")
    @PostMapping("/deleteAuditor")
    public ResponseResult deleteAuditor(@ApiParam(name = "leaveOrder", value = "请假单号") @RequestParam String leaveOrder, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.deleteAuditor(leaveOrder));
    }

    @ApiOperation(value = "销假")
    @PostMapping("cancelLeave")
    public ResponseResult cancelAuditor(@ApiParam(name = "leaveorder", value = "请假单号") @RequestParam(required = true) String leaveorder, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.cancelAuditor(leaveorder));
    }
}
