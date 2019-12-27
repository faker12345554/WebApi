package com.admin.admin.controller.dw_Alarm;

import com.admin.admin.entity.dw_alarm.Alarmsettings;
import com.admin.admin.service.dw_Alarm.AlarmService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value="报警提醒controller",tags={"报警提醒设置"})
@RestController
@RequestMapping("/Alarm")
public class AlarmController {
    @Autowired
    private AlarmService alarmService;

    private ResponseResult result = new ResponseResult();

    @PostMapping("/SaveOrUpdateAlarm")
    @ApiOperation("保存或修改报警提醒设置")
    public ResponseResult SaveOrUpdateAlarm( @RequestBody  Alarmsettings alarmsettings){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(alarmService.SaveOrUpdateAlarm(alarmsettings));
    }

    @GetMapping("/deleteAlarm")
    @ApiOperation("删除报警提醒设置")
    public ResponseResult deleteAlarm(@RequestParam int id){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(alarmService.DeleteAlarm(id));
    }

    @ApiOperation("获取报警提醒设置")
    @GetMapping("/getAlarm")
    public ResponseResult getAlarm(){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(alarmService.getAlarm());
    }
}
