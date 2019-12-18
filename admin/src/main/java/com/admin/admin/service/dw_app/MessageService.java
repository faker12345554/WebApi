package com.admin.admin.service.dw_app;

import com.admin.admin.dao.app.MessageDao;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;

    private ResponseResult result = new ResponseResult();

    public ResponseResult getNotificationList(String UserId) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(messageDao.getNotificationList(UserId));
    }
}
