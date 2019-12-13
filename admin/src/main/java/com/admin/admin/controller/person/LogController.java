package com.admin.admin.controller.person;

import com.admin.admin.entity.person.LogInformation;
import com.admin.admin.service.person.LogService;
import com.admin.model.LogParamModel;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Log")
public class LogController {
    @Autowired
    private LogService logService;

    private ResponseResult result=new ResponseResult();

    @ApiOperation(value = "增加日志")
    @PostMapping("/addLog")
    public ResponseResult insertLog(@RequestBody LogInformation logInformation, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(logService.insertLog(logInformation));
    }

    @ApiOperation(value = "查询日志信息")
    @PostMapping("/getLog")
    public ResponseResult listLog(@RequestBody LogParamModel logParamModel, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(logService.listLog(logParamModel));
    }

}
