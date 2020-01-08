package com.adminapp.business.service.dw_message;

import com.adminapp.business.dao.dw_message.MessageDao;
import com.adminapp.business.entity.dw_message.MessageInformation;
import com.adminapp.model.dw_message.MessageListModel;
import com.adminapp.model.dw_supervise.PersonAllInformationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;

    //获取该工作人员下所监管的所有监居人员
    public List<PersonAllInformationModel> getUserControlPerson(String userId){
        return messageDao.getUserControlPerson(userId);
    }

    //获取监居人员的通知列表
    public List<MessageInformation> listNotifications(String personId){
        return messageDao.listNotifications(personId);
    }

    //获取监居人员的通知列表
    public List<MessageListModel> listAllMessages(String personId){
        return messageDao.listAllMessages(personId);
    }

    //确认消息已读
    public int updateReadMessage(int id){
        return messageDao.updateReadMessage(id);
    }
}
