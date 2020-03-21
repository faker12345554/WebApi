package com.admin.admin.service.dw_app;

import com.admin.admin.dao.master.dw_app.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;



    public int getNotificationList(String UserId) {

        return messageDao.getNotificationList(UserId);
    }
}
