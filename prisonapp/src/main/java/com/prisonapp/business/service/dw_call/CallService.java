package com.prisonapp.business.service.dw_call;

import com.prisonapp.business.dao.dw_call.CallDao;
import com.prisonapp.business.entity.dw_call.TSendphone;
import com.prisonapp.business.entity.dw_supervise.TPersoninformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallService {
    @Autowired
    private CallDao callDao;

    public TPersoninformation getPersoninformation(String personid){
        return callDao.getPersoninformation(personid);
    }

    public int makeCall(String type){
        return callDao.makeCall(type);
    }
    public TSendphone checkOnline(String Sponsoralarm){
        return callDao.checkOnline(Sponsoralarm);
    }

    //发出通话出插入记录
    public int insertRecord(String callToken,String timeStamp,String type,String callname,String sendname,String personid,String accountname){
        return callDao.insertRecord(callToken,timeStamp,type,callname,sendname,personid,accountname);
    }

    //获取通话信息
    public TSendphone getPhoneInformation(String callToken){
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



    public TPersoninformation RelatedId(String accountName){
        return  callDao.RelatedId( accountName);
    }
}
