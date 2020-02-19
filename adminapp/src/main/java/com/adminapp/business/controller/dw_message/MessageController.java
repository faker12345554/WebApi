package com.adminapp.business.controller.dw_message;

import com.adminapp.business.entity.dw_message.MessageInformation;
import com.adminapp.business.entity.dw_user.UserModel;
import com.adminapp.business.service.dw_message.MessageService;
import com.adminapp.business.service.dw_user.UserService;
import com.adminapp.config.token.TokenUtil;
import com.adminapp.config.token.tation.UserLoginToken;
import com.adminapp.model.dw_message.*;
import com.adminapp.model.dw_supervise.PersonAllInformationModel;
import com.common.common.result.ResultSet;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/app/admin/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    //private ResultSet rs=new ResultSet();

    @UserLoginToken
    @ApiOperation(value = "获取管理人员的通知列表")
    @GetMapping("/getNotificationList")
    public ResultSet getNotificationList(){
        ResultSet rs=new ResultSet();
        String userId= TokenUtil.getTokenUserId();
        List<NotificationListModel> notificationListModels=new ArrayList<>();
        List<MessageInformation> messageInformationList=new ArrayList<>();  //该工作人员的所有通知消息
        List<PersonAllInformationModel> personAllInformationModels=messageService.getUserControlPerson(userId);
        for (PersonAllInformationModel item:personAllInformationModels
             ) {
            List<MessageInformation> messageInformations=messageService.listNotifications(item.getPersonid());  //一个监居人员的所有通知
            for (MessageInformation item1:messageInformations
                 ) {
                messageInformationList.add(item1);
            }
        }
        List<MessageInformation> remindMessageList=new ArrayList<>();  //到期提醒通知列表
        List<MessageInformation> alarmMessageList=new ArrayList<>();   //报警提醒通知列表
        List<MessageInformation> auditorMessageList=new ArrayList<>(); //审批提醒通知列表
        List<MessageInformation> upcomignMessageList=new ArrayList<>(); //待办事项通知列表
        List<MessageInformation> connectMessageList=new ArrayList<>();  //通话消息通知列表
        List<MessageInformation> personMessageList=new ArrayList<>();   //人员消息通知列表
        List<MessageInformation> platformMessageList=new ArrayList<>(); //平台通知列表
        int remindUnreadCount=0;   //到期提醒未读消息数
        int alarmUnreadCount=0;    //报警提醒未读消息数
        int auditorUnreadCount=0;  //审批提醒未读消息数
        int upcomignUnreadCount=0; //待办事项未读消息数
        int connectUnreadCount=0;  //通话消息未读消息数
        int personUnreadCount=0;   //人员消息未读消息数
        int platformUnreadCount=0; //平台通知未读消息数

        for (MessageInformation item3:messageInformationList
             ) {
            if(item3.getModular().equals("1")){
                remindMessageList.add(item3);
                if(!item3.getReadmessage()){
                    remindUnreadCount++;
                }
            }
            if(item3.getModular().equals("2")){
                alarmMessageList.add(item3);
                if(!item3.getReadmessage()){
                    alarmUnreadCount++;
                }
            }
            if(item3.getModular().equals("3")){
                auditorMessageList.add(item3);
                if(!item3.getReadmessage()){
                    auditorUnreadCount++;
                }
            }
            if(item3.getModular().equals("4")){
                upcomignMessageList.add(item3);
                if(!item3.getReadmessage()){
                    upcomignUnreadCount++;
                }
            }
            if(item3.getModular().equals("5")){
                connectMessageList.add(item3);
                if(!item3.getReadmessage()){
                    connectUnreadCount++;
                }
            }
            if(item3.getModular().equals("6")){
                personMessageList.add(item3);
                if(!item3.getReadmessage()){
                    personUnreadCount++;
                }
            }
            if(item3.getModular().equals("7")){
                platformMessageList.add(item3);
                if(!item3.getReadmessage()){
                    platformUnreadCount++;
                }
            }
        }
            Collections.sort(remindMessageList, new Comparator<MessageInformation>() {   //倒序排序
                @Override
                public int compare(MessageInformation o1, MessageInformation o2) {
                    long a = o2.getMessagetime().getTime();
                    long b = o1.getMessagetime().getTime();
                    return String.valueOf(a).compareTo(String.valueOf(b));
                }
            });
        Collections.sort(alarmMessageList, new Comparator<MessageInformation>() {
            @Override
            public int compare(MessageInformation o1, MessageInformation o2) {
                long a=o2.getMessagetime().getTime();
                long b=o1.getMessagetime().getTime();
                return String.valueOf(a).compareTo(String.valueOf(b));
            }
        });
        Collections.sort(auditorMessageList, new Comparator<MessageInformation>() {
            @Override
            public int compare(MessageInformation o1, MessageInformation o2) {
                long a=o2.getMessagetime().getTime();
                long b=o1.getMessagetime().getTime();
                return String.valueOf(a).compareTo(String.valueOf(b));
            }
        });
        Collections.sort(upcomignMessageList, new Comparator<MessageInformation>() {
            @Override
            public int compare(MessageInformation o1, MessageInformation o2) {
                long a=o2.getMessagetime().getTime();
                long b=o1.getMessagetime().getTime();
                return String.valueOf(a).compareTo(String.valueOf(b));
            }
        });
        Collections.sort(connectMessageList, new Comparator<MessageInformation>() {
            @Override
            public int compare(MessageInformation o1, MessageInformation o2) {
                long a=o2.getMessagetime().getTime();
                long b=o1.getMessagetime().getTime();
                return String.valueOf(a).compareTo(String.valueOf(b));
            }
        });
        Collections.sort(personMessageList, new Comparator<MessageInformation>() {
            @Override
            public int compare(MessageInformation o1, MessageInformation o2) {
                long a=o2.getMessagetime().getTime();
                long b=o1.getMessagetime().getTime();
                return String.valueOf(a).compareTo(String.valueOf(b));
            }
        });
        Collections.sort(platformMessageList, new Comparator<MessageInformation>() {
            @Override
            public int compare(MessageInformation o1, MessageInformation o2) {
                long a=o2.getMessagetime().getTime();
                long b=o1.getMessagetime().getTime();
                return String.valueOf(a).compareTo(String.valueOf(b));
            }
        });
        NotificationListModel remindNotifitionModel = new NotificationListModel();
        if(remindMessageList.size()!=0) {
            remindNotifitionModel.setType("1");
            remindNotifitionModel.setTypeName("到期提醒");
            remindNotifitionModel.setDetailType(String.valueOf(remindMessageList.get(0).getDetailtype()));
            remindNotifitionModel.setDetailTypeName(remindMessageList.get(0).getDetailtypename());
            remindNotifitionModel.setContent(remindMessageList.get(0).getContent());
            remindNotifitionModel.setTimestamp(String.valueOf(remindMessageList.get(0).getMessagetime().getTime()));
            remindNotifitionModel.setUnreadCount(remindUnreadCount);
            notificationListModels.add(remindNotifitionModel);
        }else{
            remindNotifitionModel.setType("1");
            remindNotifitionModel.setTypeName("到期提醒");
            remindNotifitionModel.setUnreadCount(remindUnreadCount);
        }

        NotificationListModel alarmNotificationModel = new NotificationListModel();
        if(alarmMessageList.size()!=0) {
            alarmNotificationModel.setType("2");
            alarmNotificationModel.setTypeName("报警提醒");
            alarmNotificationModel.setDetailType(String.valueOf(alarmMessageList.get(0).getDetailtype()));
            alarmNotificationModel.setDetailTypeName(alarmMessageList.get(0).getDetailtypename());
            alarmNotificationModel.setContent(alarmMessageList.get(0).getContent());
            alarmNotificationModel.setTimestamp(String.valueOf(alarmMessageList.get(0).getMessagetime().getTime()));
            alarmNotificationModel.setUnreadCount(alarmUnreadCount);
            notificationListModels.add(alarmNotificationModel);
        }else
        {
            alarmNotificationModel.setType("2");
            alarmNotificationModel.setTypeName("报警提醒");
            alarmNotificationModel.setUnreadCount(alarmUnreadCount);
        }

        NotificationListModel auditorNotificationModel = new NotificationListModel();
        if(auditorMessageList.size()!=0) {
            auditorNotificationModel.setType("3");
            auditorNotificationModel.setTypeName("审批提醒");
            auditorNotificationModel.setDetailType(String.valueOf(auditorMessageList.get(0).getDetailtype()));
            auditorNotificationModel.setDetailTypeName(auditorMessageList.get(0).getDetailtypename());
            auditorNotificationModel.setContent(auditorMessageList.get(0).getContent());
            auditorNotificationModel.setTimestamp(String.valueOf(auditorMessageList.get(0).getMessagetime().getTime()));
            auditorNotificationModel.setUnreadCount(auditorUnreadCount);
            notificationListModels.add(auditorNotificationModel);
        }else
        {
            auditorNotificationModel.setType("3");
            auditorNotificationModel.setTypeName("审批提醒");
            auditorNotificationModel.setUnreadCount(auditorUnreadCount);
        }

        NotificationListModel upcomingNotificationModel = new NotificationListModel();
        if(upcomignMessageList.size()!=0) {
            upcomingNotificationModel.setType("4");
            upcomingNotificationModel.setTypeName("待办事项");
            upcomingNotificationModel.setDetailType(String.valueOf(upcomignMessageList.get(0).getDetailtype()));
            upcomingNotificationModel.setDetailTypeName(upcomignMessageList.get(0).getDetailtypename());
            upcomingNotificationModel.setContent(upcomignMessageList.get(0).getContent());
            upcomingNotificationModel.setTimestamp(String.valueOf(upcomignMessageList.get(0).getMessagetime().getTime()));
            upcomingNotificationModel.setUnreadCount(upcomignUnreadCount);
            notificationListModels.add(upcomingNotificationModel);
        }else{
            upcomingNotificationModel.setType("4");
            upcomingNotificationModel.setTypeName("待办事项");
            upcomingNotificationModel.setUnreadCount(upcomignUnreadCount);
        }

        NotificationListModel connectNotificationModel = new NotificationListModel();
        if(connectMessageList.size()!=0) {

            connectNotificationModel.setType("5");
            connectNotificationModel.setTypeName("通话消息");
            connectNotificationModel.setDetailType(String.valueOf(connectMessageList.get(0).getDetailtype()));
            connectNotificationModel.setDetailTypeName(connectMessageList.get(0).getDetailtypename());
            connectNotificationModel.setContent(connectMessageList.get(0).getContent());
            connectNotificationModel.setTimestamp(String.valueOf(connectMessageList.get(0).getMessagetime().getTime()));
            connectNotificationModel.setUnreadCount(connectUnreadCount);
            notificationListModels.add(connectNotificationModel);
        }else{
            connectNotificationModel.setType("5");
            connectNotificationModel.setTypeName("通话消息");
            connectNotificationModel.setUnreadCount(connectUnreadCount);
        }

        NotificationListModel personNotificationModel = new NotificationListModel();
        if(personMessageList.size()!=0) {
            personNotificationModel.setType("6");
            personNotificationModel.setTypeName("人员消息");
            personNotificationModel.setDetailType(String.valueOf(personMessageList.get(0).getDetailtype()));
            personNotificationModel.setDetailTypeName(personMessageList.get(0).getDetailtypename());
            personNotificationModel.setContent(personMessageList.get(0).getContent());
            personNotificationModel.setTimestamp(String.valueOf(personMessageList.get(0).getMessagetime().getTime()));
            personNotificationModel.setUnreadCount(personUnreadCount);
            notificationListModels.add(personNotificationModel);
        }else
        {
            personNotificationModel.setType("6");
            personNotificationModel.setTypeName("人员消息");
            personNotificationModel.setUnreadCount(personUnreadCount);
        }

        NotificationListModel platformNotificationModel = new NotificationListModel();
        if(platformMessageList.size()!=0) {
            platformNotificationModel.setType("7");
            platformNotificationModel.setTypeName("平台通知");
            platformNotificationModel.setDetailType(String.valueOf(platformMessageList.get(0).getDetailtype()));
            platformNotificationModel.setDetailTypeName(platformMessageList.get(0).getDetailtypename());
            platformNotificationModel.setContent(platformMessageList.get(0).getContent());
            platformNotificationModel.setTimestamp(String.valueOf(platformMessageList.get(0).getMessagetime().getTime()));
            platformNotificationModel.setUnreadCount(platformUnreadCount);
            notificationListModels.add(platformNotificationModel);
        }else{
            platformNotificationModel.setType("7");
            platformNotificationModel.setTypeName("平台通知");
            platformNotificationModel.setUnreadCount(platformUnreadCount);
        }

        Collections.sort(notificationListModels, new Comparator<NotificationListModel>() {
            @Override
            public int compare(NotificationListModel o1, NotificationListModel o2) {
                return o2.getTimestamp().compareTo(o1.getTimestamp());
            }
        });

        if(remindMessageList.size()==0){
            notificationListModels.add(remindNotifitionModel);
        }
        if(alarmMessageList.size()==0){
            notificationListModels.add(alarmNotificationModel);
        }
        if(auditorMessageList.size()==0){
            notificationListModels.add(auditorNotificationModel);
        }
        if(upcomignMessageList.size()==0){
            notificationListModels.add(upcomingNotificationModel);
        }
        if(connectMessageList.size()==0){
            notificationListModels.add(connectNotificationModel);
        }
        if(personMessageList.size()==0){
            notificationListModels.add(personNotificationModel);
        }
        if(platformMessageList.size()==0){
            notificationListModels.add(platformNotificationModel);
        }
        NotificationListReturnModel notificationListReturnModel=new NotificationListReturnModel();
        notificationListReturnModel.setTotalCount(remindUnreadCount+alarmUnreadCount+auditorUnreadCount+upcomignUnreadCount+connectUnreadCount+personUnreadCount+platformUnreadCount);
        notificationListReturnModel.setList(notificationListModels);
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=notificationListReturnModel;
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "获取管理人员的消息列表")
    @GetMapping("/getMessageList")
    public ResultSet getMessageList(@ApiParam(name = "type",value = "类型编号")@RequestParam(required = true)String type,
                                    @ApiParam(name="count",value = "已获取数据数")@RequestParam(required = true)int count,
                                    @ApiParam(name = "requestCount",value = "请求获取数据数")@RequestParam(required = true)int requestCount,
                                    @ApiParam(name = "key",value = "关键字")@RequestParam(required = false)String key){
        ResultSet rs=new ResultSet();
        if(type.equals("0")||type.equals("1")||type.equals("2")||type.equals("3")||type.equals("4")||type.equals("5")||type.equals("6")||type.equals("7")) {
            String userId = TokenUtil.getTokenUserId();
            List<MessageListModel> messageInformationList = new ArrayList<>();  //该工作人员的所有通知消息
            List<PersonAllInformationModel> personAllInformationModels = messageService.getUserControlPerson(userId);
            for (PersonAllInformationModel item : personAllInformationModels
            ) {
                //List<MessageInformation> messageInformations=messageService.listNotifications(item.getPersonid());  //一个监居人员的所有通知
                List<MessageListModel> messageInformations = messageService.listAllMessages(item.getPersonid());
                for (MessageListModel item1 : messageInformations
                ) {
                    messageInformationList.add(item1);
                }
            }
            List<MessageListModel> newMessageInformations = new ArrayList<>();  //某一类型的消息通知列表
            switch (type) {
                case "1":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("1")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
                case "2":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("2")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
                case "3":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("3")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
                case "4":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("4")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
                case "5":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("5")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
                case "6":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("6")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
                case "7":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("7")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
                case "0":
                    newMessageInformations = messageInformationList;
            }

            List<MessageListModel> messageListModels = new ArrayList<>();
            if (key == null||key=="") {
                messageListModels = newMessageInformations;
            } else {
                for (MessageListModel item : newMessageInformations
                ) {
                    if (item.getDetailTypeName().contains(key) || item.getContent().contains(key)) {
                        messageListModels.add(item);
                    }
                }
            }

            List<MessageListModel> newMessageList = new ArrayList<>();
            if (messageListModels.size() > count && messageListModels.size() <= count + requestCount) {
                for (int i = count; i < messageListModels.size(); i++) {
                    MessageListModel summonsInformation = messageListModels.get(i);
                    newMessageList.add(summonsInformation);
                }
            }
            if (messageListModels.size() > count + requestCount) {
                for (int i = count; i < count + requestCount; i++) {
                    MessageListModel summonsInformation = messageListModels.get(i);
                    newMessageList.add(summonsInformation);
                }
            }

            Collections.sort(newMessageList, new Comparator<MessageListModel>() {
                @Override
                public int compare(MessageListModel o1, MessageListModel o2) {
                    return o2.getTimestamp().compareTo(o1.getTimestamp());
                }
            });

            MessageListReturnModel messageListReturnModel = new MessageListReturnModel();
            messageListReturnModel.setTotalCount(messageListModels.size());
            messageListReturnModel.setList(newMessageList);
            rs.resultCode = 0;
            rs.resultMsg = "";
            rs.data = messageListReturnModel;
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此消息类型";
            rs.data=null;
        }
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "管理人员确认消息读取")
    @PostMapping("/readMessage")
    public ResultSet readMessage(@ApiParam(name = "type",value = "类型编号")@RequestParam(required = true)String type,
                                 @ApiParam(name = "messageTimestamp",value = "最新一条消息的时间戳")@RequestParam(required = true)String messageTimestamp){
        ResultSet rs=new ResultSet();
        if(type.equals("1")||type.equals("2")||type.equals("3")||type.equals("4")||type.equals("5")||type.equals("6")||type.equals("7")) {
            if(messageTimestamp.length()==13) {
                String userId = TokenUtil.getTokenUserId();
                List<MessageListModel> messageInformationList = new ArrayList<>();  //该工作人员的所有通知消息
                List<PersonAllInformationModel> personAllInformationModels = messageService.getUserControlPerson(userId);
                for (PersonAllInformationModel item : personAllInformationModels
                ) {
                    List<MessageListModel> messageInformations = messageService.listAllMessages(item.getPersonid());
                    for (MessageListModel item1 : messageInformations
                    ) {
                        messageInformationList.add(item1);
                    }
                }

                List<MessageListModel> messageListModels = new ArrayList<>();   //筛选出小于等于最新一条消息时间戳的通知消息
                for (MessageListModel item : messageInformationList
                ) {
                    if (item.getType().equals(type) && Long.parseLong(item.getTimestamp()) <= Long.parseLong(messageTimestamp) && item.getIsRead() == false) {
                        messageListModels.add(item);
                    }
                }

                for (MessageListModel item : messageListModels
                ) {
                    int updateReadMessage = messageService.updateReadMessage(Integer.parseInt(item.getCode()));
                }
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = new Object();
            }
            else{
                rs.resultCode=1;
                rs.resultMsg="时间戳格式不正确";
                rs.data=null;
            }
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此类型编号";
            rs.data=null;
        }
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "管理人员通知搜索")
    @GetMapping("/searchNotification")
    public ResultSet searchNotification(@ApiParam(name = "key",value = "关键字")@RequestParam(required = true)String key){
        ResultSet rs=new ResultSet();
        String userId = TokenUtil.getTokenUserId();
        List<MessageListModel> messageInformationList = new ArrayList<>();  //该工作人员的所有通知消息
        List<PersonAllInformationModel> personAllInformationModels = messageService.getUserControlPerson(userId);
        for (PersonAllInformationModel item : personAllInformationModels
        ) {
            List<MessageListModel> messageInformations = messageService.listAllMessages(item.getPersonid());
            for (MessageListModel item1 : messageInformations
            ) {
                messageInformationList.add(item1);
            }
        }
        List<MessageListModel> keyMessageList=new ArrayList<>();   //含有key关键字的所有数据列表
        for (MessageListModel item:messageInformationList
             ) {
            if(item.getContent().contains(key)||item.getDetailTypeName().contains(key)){
                keyMessageList.add(item);
            }
        }
        List<MessageListModel> remindMessageList=new ArrayList<>();  //到期提醒通知列表
        List<MessageListModel> alarmMessageList=new ArrayList<>();   //报警提醒通知列表
        List<MessageListModel> auditorMessageList=new ArrayList<>(); //审批提醒通知列表
        List<MessageListModel> upcomignMessageList=new ArrayList<>(); //待办事项通知列表
        List<MessageListModel> connectMessageList=new ArrayList<>();  //通话消息通知列表
        List<MessageListModel> personMessageList=new ArrayList<>();   //人员消息通知列表
        List<MessageListModel> platformMessageList=new ArrayList<>(); //平台通知列表

        for (MessageListModel item3:keyMessageList
        ) {
            if(item3.getType().equals("1")){
                remindMessageList.add(item3);
            }
            if(item3.getType().equals("2")){
                alarmMessageList.add(item3);
            }
            if(item3.getType().equals("3")){
                auditorMessageList.add(item3);
            }
            if(item3.getType().equals("4")){
                upcomignMessageList.add(item3);
            }
            if(item3.getType().equals("5")){
                connectMessageList.add(item3);
            }
            if(item3.getType().equals("6")){
                personMessageList.add(item3);
            }
            if(item3.getType().equals("7")){
                platformMessageList.add(item3);
            }
        }

        List<SearchNotificationModel> searchNotificationModels=new ArrayList<>();
        if(remindMessageList.size()!=0){    //到期提醒数据不为空
            Collections.sort(remindMessageList, new Comparator<MessageListModel>() {
                        @Override
                        public int compare(MessageListModel o1, MessageListModel o2) {
                            return o2.getTimestamp().compareTo(o1.getTimestamp());
                        }
                    });
            SearchNotificationModel searchNotificationModel=new SearchNotificationModel();
            searchNotificationModel.setType(remindMessageList.get(0).getType());
            searchNotificationModel.setTypeName(remindMessageList.get(0).getTypeName());
            searchNotificationModel.setDetailType(remindMessageList.get(0).getDetailType());
            searchNotificationModel.setDetailTypeName(remindMessageList.get(0).getDetailTypeName());
            searchNotificationModel.setMessageCount(remindMessageList.size());
            searchNotificationModel.setContent(remindMessageList.get(0).getContent());
            searchNotificationModel.setTimestamp(remindMessageList.get(0).getTimestamp());
            searchNotificationModels.add(searchNotificationModel);
        }

        if(alarmMessageList.size()!=0){    //报警提醒数据不为空
            Collections.sort(alarmMessageList, new Comparator<MessageListModel>() {
                @Override
                public int compare(MessageListModel o1, MessageListModel o2) {
                    return o2.getTimestamp().compareTo(o1.getTimestamp());
                }
            });
            SearchNotificationModel searchNotificationModel=new SearchNotificationModel();
            searchNotificationModel.setType(alarmMessageList.get(0).getType());
            searchNotificationModel.setTypeName(alarmMessageList.get(0).getTypeName());
            searchNotificationModel.setDetailType(alarmMessageList.get(0).getDetailType());
            searchNotificationModel.setDetailTypeName(alarmMessageList.get(0).getDetailTypeName());
            searchNotificationModel.setMessageCount(alarmMessageList.size());
            searchNotificationModel.setContent(alarmMessageList.get(0).getContent());
            searchNotificationModel.setTimestamp(alarmMessageList.get(0).getTimestamp());
            searchNotificationModels.add(searchNotificationModel);
        }

        if(auditorMessageList.size()!=0){    //审批提醒数据不为空
            Collections.sort(auditorMessageList, new Comparator<MessageListModel>() {
                @Override
                public int compare(MessageListModel o1, MessageListModel o2) {
                    return o2.getTimestamp().compareTo(o1.getTimestamp());
                }
            });
            SearchNotificationModel searchNotificationModel=new SearchNotificationModel();
            searchNotificationModel.setType(auditorMessageList.get(0).getType());
            searchNotificationModel.setTypeName(auditorMessageList.get(0).getTypeName());
            searchNotificationModel.setDetailType(auditorMessageList.get(0).getDetailType());
            searchNotificationModel.setDetailTypeName(auditorMessageList.get(0).getDetailTypeName());
            searchNotificationModel.setMessageCount(auditorMessageList.size());
            searchNotificationModel.setContent(auditorMessageList.get(0).getContent());
            searchNotificationModel.setTimestamp(auditorMessageList.get(0).getTimestamp());
            searchNotificationModels.add(searchNotificationModel);
        }

        if(upcomignMessageList.size()!=0){    //待办事项数据不为空
            Collections.sort(upcomignMessageList, new Comparator<MessageListModel>() {
                @Override
                public int compare(MessageListModel o1, MessageListModel o2) {
                    return o2.getTimestamp().compareTo(o1.getTimestamp());
                }
            });
            SearchNotificationModel searchNotificationModel=new SearchNotificationModel();
            searchNotificationModel.setType(upcomignMessageList.get(0).getType());
            searchNotificationModel.setTypeName(upcomignMessageList.get(0).getTypeName());
            searchNotificationModel.setDetailType(upcomignMessageList.get(0).getDetailType());
            searchNotificationModel.setDetailTypeName(upcomignMessageList.get(0).getDetailTypeName());
            searchNotificationModel.setMessageCount(upcomignMessageList.size());
            searchNotificationModel.setContent(upcomignMessageList.get(0).getContent());
            searchNotificationModel.setTimestamp(upcomignMessageList.get(0).getTimestamp());
            searchNotificationModels.add(searchNotificationModel);
        }

        if(connectMessageList.size()!=0){    //通话消息数据不为空
            Collections.sort(connectMessageList, new Comparator<MessageListModel>() {
                @Override
                public int compare(MessageListModel o1, MessageListModel o2) {
                    return o2.getTimestamp().compareTo(o1.getTimestamp());
                }
            });
            SearchNotificationModel searchNotificationModel=new SearchNotificationModel();
            searchNotificationModel.setType(connectMessageList.get(0).getType());
            searchNotificationModel.setTypeName(connectMessageList.get(0).getTypeName());
            searchNotificationModel.setDetailType(connectMessageList.get(0).getDetailType());
            searchNotificationModel.setDetailTypeName(connectMessageList.get(0).getDetailTypeName());
            searchNotificationModel.setMessageCount(connectMessageList.size());
            searchNotificationModel.setContent(connectMessageList.get(0).getContent());
            searchNotificationModel.setTimestamp(connectMessageList.get(0).getTimestamp());
            searchNotificationModels.add(searchNotificationModel);
        }

        if(personMessageList.size()!=0){    //人员消息数据不为空
            Collections.sort(personMessageList, new Comparator<MessageListModel>() {
                @Override
                public int compare(MessageListModel o1, MessageListModel o2) {
                    return o2.getTimestamp().compareTo(o1.getTimestamp());
                }
            });
            SearchNotificationModel searchNotificationModel=new SearchNotificationModel();
            searchNotificationModel.setType(personMessageList.get(0).getType());
            searchNotificationModel.setTypeName(personMessageList.get(0).getTypeName());
            searchNotificationModel.setDetailType(personMessageList.get(0).getDetailType());
            searchNotificationModel.setDetailTypeName(personMessageList.get(0).getDetailTypeName());
            searchNotificationModel.setMessageCount(personMessageList.size());
            searchNotificationModel.setContent(personMessageList.get(0).getContent());
            searchNotificationModel.setTimestamp(personMessageList.get(0).getTimestamp());
            searchNotificationModels.add(searchNotificationModel);
        }

        if(platformMessageList.size()!=0){    //平台消息数据不为空
            Collections.sort(platformMessageList, new Comparator<MessageListModel>() {
                @Override
                public int compare(MessageListModel o1, MessageListModel o2) {
                    return o2.getTimestamp().compareTo(o1.getTimestamp());
                }
            });
            SearchNotificationModel searchNotificationModel=new SearchNotificationModel();
            searchNotificationModel.setType(platformMessageList.get(0).getType());
            searchNotificationModel.setTypeName(platformMessageList.get(0).getTypeName());
            searchNotificationModel.setDetailType(platformMessageList.get(0).getDetailType());
            searchNotificationModel.setDetailTypeName(platformMessageList.get(0).getDetailTypeName());
            searchNotificationModel.setMessageCount(platformMessageList.size());
            searchNotificationModel.setContent(platformMessageList.get(0).getContent());
            searchNotificationModel.setTimestamp(platformMessageList.get(0).getTimestamp());
            searchNotificationModels.add(searchNotificationModel);
        }

        Collections.sort(searchNotificationModels, new Comparator<SearchNotificationModel>() {
            @Override
            public int compare(SearchNotificationModel o1, SearchNotificationModel o2) {
                return o1.getType().compareTo(o2.getType());
            }
        });
        SearchNotificationReturnModel searchNotificationReturnModel=new SearchNotificationReturnModel();
        searchNotificationReturnModel.setTotalCount(remindMessageList.size()+alarmMessageList.size()+auditorMessageList.size()+upcomignMessageList.size()+connectMessageList.size()+personMessageList.size()+platformMessageList.size());
        searchNotificationReturnModel.setList(searchNotificationModels);
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=searchNotificationReturnModel;
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "获取管理人员的某一类通知")
    @GetMapping("/getNotification")
    public ResultSet getNotification(@ApiParam(name = "type",value = "通知类型")@RequestParam(required = true)String type){
        ResultSet rs=new ResultSet();
        if(type.equals("1")||type.equals("2")||type.equals("3")||type.equals("4")||type.equals("5")||type.equals("6")||type.equals("7")) {

            String userId = TokenUtil.getTokenUserId();
            List<MessageListModel> messageInformationList = new ArrayList<>();  //该工作人员的所有通知消息
            List<PersonAllInformationModel> personAllInformationModels = messageService.getUserControlPerson(userId);
            for (PersonAllInformationModel item : personAllInformationModels
            ) {
                List<MessageListModel> messageInformations = messageService.listAllMessages(item.getPersonid());
                for (MessageListModel item1 : messageInformations
                ) {
                    messageInformationList.add(item1);
                }
            }
            List<MessageListModel> newMessageInformations = new ArrayList<>();  //某一类型的消息通知列表
            switch (type) {
                case "1":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("1")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
                case "2":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("2")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
                case "3":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("3")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
                case "4":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("4")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
                case "5":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("5")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
                case "6":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("6")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
                case "7":
                    for (MessageListModel item : messageInformationList
                    ) {
                        if (item.getType().equals("7")) {
                            newMessageInformations.add(item);
                        }
                    }
                    break;
            }
            Collections.sort(newMessageInformations, new Comparator<MessageListModel>() {
                @Override
                public int compare(MessageListModel o1, MessageListModel o2) {
                    return o2.getTimestamp().compareTo(o1.getTimestamp());
                }
            });
            int unreadCount = 0;
            for (MessageListModel item : newMessageInformations
            ) {
                if (item.getIsRead() == false) {
                    unreadCount++;
                }
            }
            if (newMessageInformations.size() != 0) {
                GetNotificationModel getNotificationModel = new GetNotificationModel();
                getNotificationModel.setType(newMessageInformations.get(0).getType());
                getNotificationModel.setTypeName(newMessageInformations.get(0).getTypeName());
                getNotificationModel.setDetailType(newMessageInformations.get(0).getDetailType());
                getNotificationModel.setDetailTypeName((newMessageInformations.get(0).getDetailTypeName()));
                getNotificationModel.setContent(newMessageInformations.get(0).getContent());
                getNotificationModel.setTimestamp(newMessageInformations.get(0).getTimestamp());
                getNotificationModel.setUnreadCount(unreadCount);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = getNotificationModel;
            } else {
                GetNotificationModel getNotificationModel = new GetNotificationModel();
                String typeCode="";
                String typeName="";
                switch (type) {
                    case "1":
                        typeCode="1";
                        typeName="到期提醒";
                        break;
                    case "2":
                        typeCode="2";
                        typeName="报警提醒";
                        break;
                    case "3":
                        typeCode="3";
                        typeName="审批提醒";
                        break;
                    case "4":
                        typeCode="4";
                        typeName="待办事项";
                        break;
                    case "5":
                        typeCode="5";
                        typeName="通话消息";
                        break;
                    case "6":
                        typeCode="6";
                        typeName="人员消息";
                        break;
                    case "7":
                        typeCode="7";
                        typeName="平台通知";
                        break;
                }
                getNotificationModel.setType(typeCode);
                getNotificationModel.setTypeName(typeName);
                getNotificationModel.setUnreadCount(unreadCount);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = getNotificationModel;
            }
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此通知类型";
            rs.data=null;
        }
        return rs;
    }
}
