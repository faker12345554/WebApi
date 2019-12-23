package com.admin.admin.controller.dw_reminder;

import com.admin.admin.entity.dw_reminder.Remindersettings;
import com.admin.admin.service.dw_Reminder.ReminderService;
import com.admin.admin.service.dw_Report.ReportService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("待办提醒设置")
@RestController
@RequestMapping("/Reminder")
public class ReminderController {
    @Autowired
    private ReminderService reminderService;

    private ResponseResult result = new ResponseResult();

    @PostMapping("/SaveOrUpdateReminder")
    @ApiOperation("修改或者新增")
    public ResponseResult SaveOrUpdateReminder(@RequestBody Remindersettings remindersettings){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( reminderService.SaveOrUpdateReminder(remindersettings));
    }

    @ApiOperation("删除")
    @GetMapping("deleteReminder")
    public ResponseResult deleteReminder(@RequestParam  int id){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( reminderService.DeleteReminder(id));
    }

    @ApiOperation("查看")
    @GetMapping("getReminder")
    public ResponseResult getReminder (@RequestBody int id){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( reminderService.DeleteReminder(id));
    }
}
