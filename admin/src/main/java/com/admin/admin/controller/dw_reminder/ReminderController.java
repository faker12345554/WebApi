package com.admin.admin.controller.dw_reminder;

import com.admin.admin.entity.dw_reminder.Remindersettings;
import com.admin.admin.service.dw_Reminder.ReminderService;
import com.admin.admin.service.dw_Report.ReportService;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="待办提醒设置Controller",tags={"待办提醒设置"})
@RestController
@RequestMapping("/Reminder")
public class ReminderController {
    @Autowired
    private ReminderService reminderService;



    @UserLoginToken
    @PostMapping("/SaveOrUpdateReminder")
    @ApiOperation("修改或者新增待办提醒设置")
    public ResponseResult SaveOrUpdateReminder(@RequestBody List<Remindersettings> remindersettings){
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( reminderService.SaveOrUpdateReminder(remindersettings));
    }

//    @ApiOperation("删除待办提醒设置")
//    @GetMapping("deleteReminder")
//    public ResponseResult deleteReminder(){
//        result.setCode(ResultCode.SUCCESS.getCode());
//        result.setMessage(ResultCode.SUCCESS.getMessage());
//        return result.setData( reminderService.DeleteReminder());
//    }

    @UserLoginToken
    @ApiOperation("查看待办提醒设置")
    @GetMapping("getReminder")
    public ResponseResult getReminder (){
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( reminderService.getReminder());
    }
}
