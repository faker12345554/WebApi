package com.admin.admin.controller.dw_log;

import com.admin.admin.service.dw_log.LogService;
import com.admin.model.log.LogParamModel;
import com.admin.page.PageBean;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api(value="操作日志controller",tags={"查看操作日志"})
@RestController
@RequestMapping("/Log")
public class LogController {
    @Autowired
    private LogService logService;
    private ResponseResult result = new ResponseResult();


//    @ApiOperation(value = "增加日志")
////    @PostMapping("/addLog")
////    public ResponseResult insertLog(@RequestBody LogInformation logInformation, HttpServletResponse response) {
////        result.setCode(ResultCode.SUCCESS.getCode());
////        result.setMessage(ResultCode.SUCCESS.getMessage());
////        return result.setData(logService.insertLog(logInformation));
////    }

    @ApiOperation(value = "查询日志信息")
    @PostMapping("/getLog")
    public ResponseResult listLog(@RequestBody LogParamModel logParamModel, HttpServletResponse response){
        PageBean pageBean=logService.listLog(logParamModel);
        if (pageBean.getItems().size()<=0){
            result.setCode(ResultCode.NULLDATA.getCode());
            result.setMessage(ResultCode.NULLDATA.getMessage());
            return result.setData("" );
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(pageBean);


    }

}
