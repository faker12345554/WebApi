package com.prisonapp.business.service.dw_call;

import com.common.common.apppush.AndroidNotification;
import com.common.common.apppush.PushClient;
import com.common.common.apppush.android.AndroidCustomizedcast;
import com.prisonapp.business.dao.dw_call.CallDao;
import com.prisonapp.business.entity.dw_call.TSendphone;
import com.prisonapp.business.entity.dw_supervise.TPersoninformation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    //将通话类型改为改起
    public int updateHangUp(String callToken,String timestamp){
        return callDao.updateHangUp(callToken,timestamp);
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

//推送
    public void sendRequestCallCast(Date date, JSONObject custom, String alias, String aliasType, String timestamp, String descriptions, String pushType) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PushClient client = new PushClient();
        AndroidCustomizedcast customizedcast=new AndroidCustomizedcast("5dd349b4570df37b6700045e", "4hpqbdi0wpikb7bkwamq4uwnpvkjhebz");
        customizedcast.setAlias(alias,aliasType);
        customizedcast.setCustomField(custom);
        customizedcast.setProductionMode();
        customizedcast.setDisplayType(AndroidNotification.DisplayType.MESSAGE);
        customizedcast.setExtraField("timestamp",timestamp);
        customizedcast.setExtraField("pushType",pushType);
        customizedcast.setExpireTime(sdf.format(date));
        customizedcast.setDescription(descriptions);
        client.send(customizedcast);
    }


    public TPersoninformation RelatedId(String accountName){
        return  callDao.RelatedId( accountName);
    }
}
