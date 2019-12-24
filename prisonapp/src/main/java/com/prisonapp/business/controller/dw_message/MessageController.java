package com.prisonapp.business.controller.dw_message;


import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.dw_message.*;
import com.prisonapp.business.entity.dw_supervise.FaceRecognizeModel;
import com.prisonapp.business.service.dw_message.MessageService;
import com.prisonapp.token.TokenUtil;
import com.prisonapp.token.geiuserid.GetUserId;
import com.prisonapp.token.tation.UserLoginToken;
import com.prisonapp.tool.CacheUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.nashorn.internal.parser.Token;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.invoke.util.VerifyType;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("/app/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    private GetUserId getUserId = new GetUserId();
    private ResultSet result = new ResultSet();
    private SearchNotificationModel searchNotificationModel = new SearchNotificationModel();
    private ResultSearchNotificationModel resultSearchNotificationModel = new ResultSearchNotificationModel();
    private ResultMessageListModel resultMessageListModel = new ResultMessageListModel();
    private ResultNotificationMessageModel resultNotificationMessage = new ResultNotificationMessageModel();

    @UserLoginToken
    @ApiOperation(value = "获取保外人员的通知列表")
    @GetMapping("/getNotificationList")
    public ResultSet getNotificationList() {

        int sum = 0;
        List<NotificationMessageModel> notificationMessageModels = messageService.getNotificationList(getUserId.getUserId());
        for (NotificationMessageModel item : notificationMessageModels) {
            int unreadCount = messageService.unreadCount(item.getType(), getUserId.getUserId()).size();
            item.setUnreadCount(unreadCount);
            sum += unreadCount;
        }
        resultNotificationMessage.totalCount = sum;
        resultNotificationMessage.list = notificationMessageModels;
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = resultNotificationMessage;

        return result;
    }


    @UserLoginToken
    @ApiOperation(value = "获取保外人员的消息列表")
    @GetMapping("/getMessageList")
    public ResultSet getMessageList(@ApiParam(name = "type",value = "类型编号") @RequestParam(required = true) String type,@ApiParam(name = "count",value = "当前已经获取的数据条数") @RequestParam(required = true) int count,@ApiParam(name = "requestCount",value = "请求获取数据的条数") @RequestParam(required = true) int requestCount,@ApiParam(name = "key",value = "搜索关键字") @RequestParam(required = false) String key) {
        if ("".equals(key) || key!=null) {

        }else{
            key = "";
        }
        List<MessageListModel> messageListModel = messageService.getMessageList(type, count, requestCount, key, getUserId.getUserId());
        int totalCount = (messageService.messageTotalCount(type, getUserId.getUserId())).size();
        resultMessageListModel.totalCount = totalCount;
        resultMessageListModel.list = messageListModel;
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = resultMessageListModel;

        return result;
    }

    @UserLoginToken
    @ApiOperation(value = "保外人员确认消息读取")
    @PostMapping("/readMessage")
    public ResultSet readMessage(@ApiParam(name = "type",value = "类型编号") @RequestParam(required = true) String type,@ApiParam(name = "messageTimestamp",value = "最新一条已获取的消息的时间戳") @RequestParam(required = true) String messageTimestamp) {
        long longMessageTimestamp = Long.parseLong(messageTimestamp);
        if (System.currentTimeMillis() - longMessageTimestamp >= 0)//当前时间戳减掉传入的时间戳，如果大于零，则传入的时间戳小于当前时间戳
        {
            int res = messageService.readMessage(type, messageTimestamp, getUserId.getUserId());
            if (res != 0) {
                result.resultCode = 0;
                result.resultMsg = "";
                result.data = "";
            } else {
                result.resultCode = 1;
                result.resultMsg = "失败";
                result.data = null;
            }

        } else {
            result.resultCode = 1;
            result.resultMsg = "无最新消息";
            result.data = null;
        }
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = " 保外人员通知搜索")
    @GetMapping("/searchNotification")
    public ResultSet searchNotification(@ApiParam(name = "key",value = "搜索关键字") @RequestParam(required = true) String key) {
        List<SearchNotificationModel> searchNotificationModels = messageService.searchNotification(key, getUserId.getUserId());
        int sum = 0;
        for (SearchNotificationModel item : searchNotificationModels) {
            int messageCount = messageService.messageTotalCount(item.getType(), getUserId.getUserId()).size();
            item.setMessageCount(messageCount);
            sum += messageCount;
        }
        resultSearchNotificationModel.setTotalCount(sum);
        resultSearchNotificationModel.setList(searchNotificationModels);
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = resultSearchNotificationModel;
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = " 获取保外人员最新消息")
    @GetMapping("/getNewestMessageList")
    public ResultSet getNewestMessageList(@ApiParam(name = "count",value = "当前已经获取的数据条数") @RequestParam(required = true) int count,@ApiParam(name = "requestCount",value = "请求获取数据的条数") @RequestParam(required = true) int requestCount) {
        //获取今天的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = sdf.format(new Date());
        //获取明天的日期
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(calendar.DATE, 1);
        String tomorrowDate = sdf.format(calendar.getTime());
        List<MessageListModel> NewestMessageListModels = messageService.getNewestMessageList(count,requestCount,todayDate, tomorrowDate, getUserId.getUserId());
        int newestMessageTotalCount =(messageService.newestMessageTotalCount(todayDate, tomorrowDate, getUserId.getUserId())).size();
        resultMessageListModel.totalCount=newestMessageTotalCount;
        resultMessageListModel.list=NewestMessageListModels;
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = resultMessageListModel;
        return result;
    }





}
