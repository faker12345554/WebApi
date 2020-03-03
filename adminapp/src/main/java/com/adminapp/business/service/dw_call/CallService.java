package com.adminapp.business.service.dw_call;

import com.adminapp.business.dao.dw_call.CallDao;
import com.adminapp.business.entity.dw_call.SendphoneInformation;
import com.adminapp.business.entity.dw_user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService {
    @Autowired
    private CallDao callDao;

    //检查对方是否在通话
    public SendphoneInformation checkOnline(String code){
        return callDao.checkOnline(code);
    }

    //插入通话记录
    public int insertRecord(String sendName,String callToken,String callTimestamp,String callName,String callType,String personId,String accountName){
        return callDao.insertRecord(sendName, callToken, callTimestamp, callName, callType, personId, accountName);
    }

    //查找工作人员信息
    public UserModel getUserInformation(String userId){
        return callDao.getUserInformation(userId);
    }

    //查找通话信息
    public SendphoneInformation getPhoneInformation(String callToken){
        return callDao.getPhoneInformation(callToken);
    }

    //更新通话挂断时间
    public int updateCancelRecord(String callToken,String type,String timestamp){
        return callDao.updateCancelRecord(callToken, type, timestamp);
    }

    //更新通话地址和房间号
    public int updateUrlRecord(String callToken,String serverUrl,String roomCode){
        return callDao.updateUrlRecord(callToken, serverUrl, roomCode);
    }
}
