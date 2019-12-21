package com.prisonapp.business.controller.dw_message;


import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.dw_message.*;
import com.prisonapp.business.service.dw_message.MessageService;
import com.prisonapp.token.TokenUtil;
import com.prisonapp.token.tation.UserLoginToken;
import com.prisonapp.tool.CacheUtils;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    private SearchNotificationModel searchNotificationModel = new SearchNotificationModel();
    private ResultSet result = new ResultSet();
    private ResultSearchNotificationModel resultSearchNotificationModel =new ResultSearchNotificationModel();
    private ResultMessageListModel resultMessageListModel = new ResultMessageListModel();
    private ResultNotificationMessageModel resultNotificationMessage = new ResultNotificationMessageModel();

    @UserLoginToken
    @ApiOperation(value = "获取保外人员的通知列表")
    @PostMapping("/getNotificationList")
    public ResultSet getNotificationList( ) {

        int sum=0;
        List<NotificationMessageModel> notificationMessageModels =messageService.getNotificationList(CacheUtils.get("UserId").toString());
        for(NotificationMessageModel item: notificationMessageModels){
            int unreadCount =messageService.unreadCount(item.getType(),getUserId()).size();
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


    @UserLoginToken
    @ApiOperation(value = "获取保外人员的消息列表")
    @PostMapping("/getMessageList")
    public ResultSet getMessageList(@RequestParam(required = true)String type ,@RequestParam(required = true)int count,int requestCount,@RequestParam(required = false)String key) {

        List<MessageListModel> messageListModel =messageService.getMessageList( type, count, requestCount, key,getUserId());
        int totalCount= (messageService.messageTotalCount(type,getUserId())).size();
        resultMessageListModel.totalCount=totalCount;
        resultMessageListModel.resultMessageListModel=messageListModel  ;
        result.resultCode=0;
        result.resultMsg="";
        result.data=resultMessageListModel;

        return result;
    }

    @UserLoginToken
    @ApiOperation(value = "保外人员确认消息读取")
    @PostMapping("/readMessage")
    public ResultSet readMessage(@RequestParam(required = true)String type ,@RequestParam(required = true)String messageTimestamp) {
        long longMessageTimestamp = Long.parseLong(messageTimestamp);
        if(System.currentTimeMillis()-longMessageTimestamp>=0)//当前时间戳减掉传入的时间戳，如果大于零，则传入的时间戳小于当前时间戳
        {
            int res =messageService.readMessage(type,messageTimestamp,getUserId());
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

    @UserLoginToken
    @ApiOperation(value = " 保外人员通知搜索")
    @PostMapping("/searchNotification")
    public ResultSet searchNotification(@RequestParam(required = true)String key){
        List<SearchNotificationModel> searchNotificationModels =messageService.searchNotification(key,getUserId());
        int sum=0;
        for(SearchNotificationModel item: searchNotificationModels){
            int messageCount =messageService.messageTotalCount(item.getType(),getUserId()).size();
            item.setMessageCount(messageCount);
            sum+=messageCount;
        }
        resultSearchNotificationModel.setTotalCount(sum);
        resultSearchNotificationModel.setList(searchNotificationModels);
        result.resultCode=0;
        result.resultMsg="";
        result.data=resultSearchNotificationModel;
        return result;
    }
    public String  getUserId(){
        String userId="";
        if(CacheUtils.get("UserId").toString()!=null||CacheUtils.get("UserId").toString()!="")
        {
            userId=CacheUtils.get("UserId").toString();
        }else
        {
            userId = TokenUtil.getTokenUserId();
        }
        return userId;
    }

}
