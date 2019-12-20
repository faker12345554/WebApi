package com.prisonapp.business.controller.message;


import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.message.*;
import com.prisonapp.business.service.message.MessageService;
import com.prisonapp.tool.CacheUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/message")
public class MessageController {

    @Autowired
    private MessageService messageService;
    private ResultSet result = new ResultSet();
    private ResultMessageListModel resultMessageListModel = new ResultMessageListModel();
    private ResultNotificationMessageModel resultNotificationMessage = new ResultNotificationMessageModel();

    @ApiOperation(value = "获取保外人员的通知列表")
    @PostMapping("/getNotificationList")
    public ResultSet getNotificationList( ) {
        int sum=0;
        List<NotificationMessageModel> notificationMessageModels =messageService.getNotificationList(CacheUtils.get("UserId").toString());
        for(NotificationMessageModel item: notificationMessageModels){
            int unreadCount =messageService.unreadCount(item.getType(),CacheUtils.get("UserId").toString()).size();
            item.setUnreadCount(unreadCount);
            sum+=unreadCount;
        }
        resultNotificationMessage.totalCount=sum;
        resultNotificationMessage.list=notificationMessageModels;
        result.resultCode=0;
        result.resultMsg="";
        result.data=resultNotificationMessage;

        return result;
    }

    @ApiOperation(value = "获取保外人员的消息列表")
    @PostMapping("/getMessageList")
    public ResultSet getMessageList(@RequestParam(required = true)String type ,@RequestParam(required = true)int count,int requestCount,@RequestParam(required = false)String key) {
        List<MessageListModel> messageListModel =messageService.getMessageList( type, count, requestCount, key,CacheUtils.get("UserId").toString());
        int totalCount= (messageService.messageTotalCount(type,CacheUtils.get("UserId").toString())).size();
        resultMessageListModel.totalCount=totalCount;
        resultMessageListModel.resultMessageListModel=messageListModel;
        result.resultCode=0;
        result.resultMsg="";
        result.data=resultMessageListModel;

        return result;
    }

    @ApiOperation(value = "保外人员确认消息读取")
    @PostMapping("/readMessage")
    public ResultSet readMessage(@RequestParam(required = true)String type ,@RequestParam(required = true)String messageTimestamp) {
        long longMessageTimestamp = Long.parseLong(messageTimestamp);
        if(System.currentTimeMillis()-longMessageTimestamp>=0)//当前时间戳减掉传入的时间戳，如果大于零，则传入的时间戳小于当前时间戳
        {
            int res =messageService.readMessage(type,messageTimestamp,CacheUtils.get("UserId").toString());
            if(res!=0)
            {
                result.resultCode=0;
                result.resultMsg="";
                result.data="";
            }else
            {
                result.resultCode=1;
                result.resultMsg="失败";
                result.data=null;
            }

        }
        else{
            result.resultCode=1;
            result.resultMsg="无最新消息";
            result.data=null;
        }
        return result;
    }

    @ApiOperation(value = " 保外人员通知搜索")
    @PostMapping("/searchNotification")
    public ResultSet searchNotification(@RequestParam(required = true)String key){
        List<MessageModel> messageModel =messageService.searchNotification(key,CacheUtils.get("UserId").toString());
        result.resultCode=0;
        result.resultMsg="";
        result.data=messageModel;
        return result;
    }


}
