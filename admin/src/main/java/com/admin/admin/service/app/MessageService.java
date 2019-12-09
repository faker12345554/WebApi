package com.admin.admin.service.app;

import com.admin.admin.dao.app.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;

    public int getNotificationList() {
        return messageDao.getNotificationList();
    }
}
