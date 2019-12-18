package com.prisonapp.business.controller;


import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.prisonapp.business.service.MessageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    private ResponseResult result = new ResponseResult();
    //  private TokenUtil tokenUtil =new TokenUtil();

    @ApiOperation(value = "获取保外人员的通知列表")
    @PostMapping("/getNotificationList")
    public ResponseResult getNotificationList( ) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(messageService.getNotificationList());
    }

    @ApiOperation(value = "获取保外人员的消息列表")
    @PostMapping("/getMessageList")
    public ResponseResult getMessageList(int type ,int count,int requestCount,@RequestParam(required = false)String key) {
      //  if(key.equals(""))
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(messageService.getMessageList(type,count,requestCount,key));
    }

    @ApiOperation(value = "保外人员确认消息读取")
    @PostMapping("/readMessage")
    public ResponseResult readMessage(int type ,String messageTimestamp) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(messageService.readMessage(type,messageTimestamp));
    }
}
