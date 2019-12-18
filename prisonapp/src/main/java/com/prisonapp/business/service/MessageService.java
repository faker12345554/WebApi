package com.prisonapp.business.service;

import com.prisonapp.business.dao.MessageDao;
import com.prisonapp.business.entity.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;

    public List<MessageModel> getNotificationList( ) {
        return messageDao.getNotificationList();
    }
    public List<MessageModel> getMessageList(int  type, int  count, int requestCount, String key ){
        return messageDao.getMessageList(type,count,requestCount,key);
    }

    public int readMessage(int  type,String messageTimestamp){
        return messageDao.readMessage(type,messageTimestamp);
    }

}
