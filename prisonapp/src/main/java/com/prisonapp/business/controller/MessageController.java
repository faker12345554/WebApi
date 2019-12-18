package com.prisonapp.business.controller;


import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.MessageModel;
import com.prisonapp.business.service.MessageService;
import com.prisonapp.tool.CacheUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/message")
public class MessageController {
    @Autowired
    private String  userId= CacheUtils.get("UserId").toString();
    @Autowired
    private MessageService messageService;
    private ResultSet result = new ResultSet();
    //  private TokenUtil tokenUtil =new TokenUtil();

    @ApiOperation(value = "获取保外人员的通知列表")
    @PostMapping("/getNotificationList")
    public ResultSet getNotificationList( ) {
        List<MessageModel> messageModel =messageService.getNotificationList("11");
        result.resultCode=0;
        result.resultMsg="";
        result.data=messageModel;
        return result;
    }

//    @ApiOperation(value = "获取保外人员的消息列表")
//    @PostMapping("/getMessageList")
//    public ResponseResult getMessageList(@RequestParam(required = true)int type ,@RequestParam(required = true)int count,int requestCount,@RequestParam(required = false)String key) {
//      //  if(key.equals(""))
//        result.setCode(ResultCode.SUCCESS.getCode());
//        result.setMessage(ResultCode.SUCCESS.getMessage());
//        return result.setData(messageService.getMessageList(type,count,requestCount,key,userId));
//    }
//
//    @ApiOperation(value = "保外人员确认消息读取")
//    @PostMapping("/readMessage")
//    public ResponseResult readMessage(@RequestParam(required = true)int type ,@RequestParam(required = true)String messageTimestamp) {
//        result.setCode(ResultCode.SUCCESS.getCode());
//        result.setMessage(ResultCode.SUCCESS.getMessage());
//        return result.setData(messageService.readMessage(type,messageTimestamp,userId));
//    }
}
