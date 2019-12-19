package com.prisonapp.business.service.message;

import com.prisonapp.business.dao.message.MessageDao;
import com.prisonapp.business.entity.message.MessageListModel;
import com.prisonapp.business.entity.message.MessageModel;
import com.prisonapp.business.entity.message.NotificationMessageModel;
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

    public int readMessage(String  type,String messageTimestamp,String userId){
        return messageDao.readMessage(type,messageTimestamp,userId);
    }

    public List<MessageModel> searchNotification(String key,String userId){
        return messageDao.searchNotification(key,userId);
    }
}
