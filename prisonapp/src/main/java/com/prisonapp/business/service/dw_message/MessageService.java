package com.prisonapp.business.service.dw_message;

import com.prisonapp.business.dao.dw_message.MessageDao;
import com.prisonapp.business.entity.dw_message.MessageListModel;
import com.prisonapp.business.entity.dw_message.MessageModel;
import com.prisonapp.business.entity.dw_message.NotificationMessageModel;
import com.prisonapp.business.entity.dw_message.SearchNotificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;

    public List<NotificationMessageModel> getNotificationList(String userId ) {
        return messageDao.getNotificationList(userId);
    }
    public List<MessageListModel> getMessageList(String  type, int  count, int requestCount, String key, String userId ){
        return messageDao.getMessageList(type,count,requestCount,key,userId);
    }
    //保外人员确认消息读取
    public int readMessage(int  type,String messageTimestamp,String userId){
        return messageDao.readMessage(type,messageTimestamp,userId);
    }
    //保外人员通知搜索
    public List<SearchNotificationModel> searchNotification(String key, String userId){
        return messageDao.searchNotification(key,userId);
    }
    //本类型未读消息数
    public List<MessageModel> unreadCount(int  type,String userId){
        return messageDao.unreadCount(type,userId);
    }
    //本类型消息总数
    public List<MessageModel> messageTotalCount(int  type,String userId){
        return messageDao.messageTotalCount(type,userId);
    }

    public List<MessageListModel> getNewestMessageList(int count ,int requestCountString ,String  todayDate,String tomorrowDate,String userId){
        return messageDao.getNewestMessageList(count,requestCountString,todayDate,tomorrowDate,userId);
    }

    public List<MessageListModel> newestMessageTotalCount(String  todayDate,String tomorrowDate,String userId){
        return messageDao.newestMessageTotalCount(todayDate,tomorrowDate,userId);
    }

    public NotificationMessageModel getNotification(String userId ,int type){
        return messageDao.getNotification(userId,type);
    }


}
