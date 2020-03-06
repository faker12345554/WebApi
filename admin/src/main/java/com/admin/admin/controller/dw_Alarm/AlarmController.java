package com.admin.admin.controller.dw_Alarm;

import com.admin.admin.entity.dw_alarm.Alarmsettings;
import com.admin.admin.service.dw_Alarm.AlarmService;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="报警提醒controller",tags={"报警提醒设置"})
@RestController
@RequestMapping("/Alarm")
public class AlarmController {
    @Autowired
    private AlarmService alarmService;



    @UserLoginToken
    @PostMapping("/SaveOrUpdateAlarm")
    @ApiOperation("保存或修改报警提醒设置")
    public ResponseResult SaveOrUpdateAlarm( @RequestBody List<Alarmsettings> alarmsettings){
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(alarmService.SaveOrUpdateAlarm(alarmsettings));
    }

//    @GetMapping("/deleteAlarm")
//    @ApiOperation("删除报警提醒设置")
//    public ResponseResult deleteAlarm(){
//        result.setCode(ResultCode.SUCCESS.getCode());
//        result.setMessage(ResultCode.SUCCESS.getMessage());
//        return result.setData(alarmService.DeleteAlarm());
//    }

    @ApiOperation("获取报警提醒设置")
    @GetMapping("/getAlarm")
    public ResponseResult getAlarm(){
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(alarmService.getAlarm());
    }
}
