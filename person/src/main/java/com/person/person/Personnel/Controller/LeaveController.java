package com.person.person.Personnel.Controller;


import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.person.person.Personnel.Entity.AuditorInformation;
import com.person.person.Personnel.Entity.Leaveinformation;
import com.person.person.Personnel.Service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Leave")
public class LeaveController {
    @Autowired
    protected LeaveService leaveService;

    private ResponseResult result = new ResponseResult();

    @GetMapping("/GetLeave")
    public ResponseResult GetLeave(@RequestParam(required = false) int person_id, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.GetLeave(person_id));
    }

    @PostMapping("/UpdateLeave")
    public ResponseResult UpdateLeave(@RequestBody Leaveinformation leaveinformation) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.UpdateLeave(leaveinformation));
    }

    @PostMapping("/Addauditor")
    public ResponseResult Addauditor(@RequestBody AuditorInformation auditorInformation, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.Addauditor(auditorInformation));
    }
}
