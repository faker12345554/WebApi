package com.admin.admin.controller.person;


import com.admin.admin.entity.person.AuditorInformation;
import com.admin.admin.entity.person.LeaveInformation;
import com.admin.admin.service.person.LeaveService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Leave")
public class LeaveController {
    @Autowired
    protected LeaveService leaveService;

    private ResponseResult result = new ResponseResult();

    @GetMapping("/getLeave")
    public ResponseResult getLeave(@RequestParam(required = false) int personId, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.getLeave(personId));
    }

    @GetMapping("/listLeave")
    public ResponseResult listLeave() {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.listLeave());
    }

    @PostMapping("/updateLeave")
    public ResponseResult updateLeave(@RequestBody LeaveInformation leaveinformation) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.updateLeave(leaveinformation));
    }

    @PostMapping("/insertAuditor")
    public ResponseResult insertAuditor(@RequestBody AuditorInformation auditorInformation, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.insertAuditor(auditorInformation));
    }
    @PostMapping("/delectAuditor")
    public ResponseResult delectAuditor(@RequestParam(required = false) int personId,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.delectAuditor(personId));
    }
}
