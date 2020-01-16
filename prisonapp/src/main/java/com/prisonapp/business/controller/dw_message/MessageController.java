package com.prisonapp.business.controller.dw_message;


import com.common.common.result.ResultSet;
import com.prisonapp.business.controller.dw_supervise.SuperviseController;
import com.prisonapp.business.entity.dw_message.*;
import com.prisonapp.business.service.dw_message.MessageService;
import com.prisonapp.token.TokenUtil;
import com.prisonapp.token.tation.UserLoginToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Api(value="消息controller",tags={"消息及通知"})
@RestController
@RequestMapping("/app/message")
public class  MessageController {

    @Autowired
    private MessageService messageService;
    private ResultSet result = new ResultSet();
    private SuperviseController superviseController=new SuperviseController();
    private ResultSearchNotificationModel resultSearchNotificationModel = new ResultSearchNotificationModel();
    private ResultMessageListModel resultMessageListModel = new ResultMessageListModel();
    private ResultNotificationMessageModel resultNotificationMessage = new ResultNotificationMessageModel();



    //@PassToken
    @UserLoginToken
    @ApiOperation(value = "获取保外人员的通知列表")
    @GetMapping("/getNotificationList")
    public ResultSet getNotificationList() {
        int sum = 0;
        List<NotificationMessageModel> notificationMessageModels = messageService.getNotificationList(superviseController.getPersonId());
        for (NotificationMessageModel item : notificationMessageModels) {
            int unreadCount = messageService.unreadCount(item.getType(), superviseController.getPersonId()).size();
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
        List<MessageListModel> messageListModel;
        int totalCount =0;
        if(type.equals("0")){
            messageListModel = messageService.getAllMessageList( count, requestCount, key, superviseController.getPersonId());
            totalCount = (messageService.messageAllTotalCount( superviseController.getPersonId())).size();
        }else{
         messageListModel = messageService.getMessageList(Integer.parseInt(type), count, requestCount, key, superviseController.getPersonId());
            totalCount = (messageService.messageTotalCount(Integer.parseInt(type), superviseController.getPersonId())).size();
        }


        resultMessageListModel.totalCount = totalCount;
        resultMessageListModel.list = messageListModel;
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = resultMessageListModel;

        return result;
    }

    @UserLoginToken
    @ApiOperation(value = "保外人员确认消息读取")
    @GetMapping("/readMessage")
    public ResultSet readMessage(@ApiParam(name = "type",value = "类型编号") @RequestParam(required = true) int type,@ApiParam(name = "messageTimestamp",value = "最新一条已获取的消息的时间戳") @RequestParam(required = true) String messageTimestamp) {
        long longMessageTimestamp = Long.parseLong(messageTimestamp);
        if (System.currentTimeMillis() - longMessageTimestamp >= 0)//当前时间戳减掉传入的时间戳，如果大于零，则传入的时间戳小于当前时间戳
        {
            int res = messageService.readMessage(type, messageTimestamp, superviseController.getPersonId());
            if (res != 0) {
                result.resultCode = 0;
                result.resultMsg = "";
                result.data = new Object();
            } else {
                result.resultCode = 1;
                result.resultMsg = "无最新消息";
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
    public ResultSet searchNotification(@ApiParam(name = "key",value = "搜索关键字") @RequestParam(required = false) String key) {
        List<SearchNotificationModel> searchNotificationModels = messageService.searchNotification(key, superviseController.getPersonId());
        int sum = 0;
        for (SearchNotificationModel item : searchNotificationModels) {
            int messageCount = messageService.messageTotalCount(Integer.parseInt(item.getType()), superviseController.getPersonId()).size();
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
        List<MessageListModel> NewestMessageListModels = messageService.getNewestMessageList(count,requestCount,todayDate, tomorrowDate, superviseController.getPersonId());
        int newestMessageTotalCount =(messageService.newestMessageTotalCount(todayDate, tomorrowDate, superviseController.getPersonId())).size();
        resultMessageListModel.totalCount=newestMessageTotalCount;
        resultMessageListModel.list=NewestMessageListModels;
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = resultMessageListModel;
        return result;
    }


    @UserLoginToken
    @ApiOperation(value = " 获取保外人员的某一类通知")
    @GetMapping("/getNotification")
    public ResultSet getNotification(@ApiParam(name = "type",value = "通知类型") @RequestParam(required = true) String type){
        int iType = Integer.parseInt(type);
        NotificationMessageModel notificationMessageModels =messageService.getNotification(superviseController.getPersonId(),iType);
        if(notificationMessageModels!=null){
            int a =messageService.unreadCount(iType,superviseController.getPersonId()).size();
            notificationMessageModels.setUnreadCount(a);
            result.resultCode = 0;
            result.resultMsg = "";
            result.data = notificationMessageModels;
        }else{
            NotificationMessageModel notificationMessageModelList =new NotificationMessageModel();
            notificationMessageModelList.setType(iType);
            notificationMessageModelList.setTypeName(Notification.getName(iType));
            result.resultCode = 0;
            result.resultMsg = "";
            result.data = notificationMessageModelList;
        }

        return result;
    }


}
