package com.adminapp.business.service.dw_call;

import com.adminapp.business.dao.dw_call.CallDao;
import com.adminapp.business.entity.dw_call.SendphoneInformation;
import com.adminapp.business.entity.dw_user.UserModel;
import com.adminapp.model.dw_call.RequestPushModel;
import com.common.common.apppush.AndroidNotification;
import com.common.common.apppush.PushClient;
import com.common.common.apppush.android.AndroidCustomizedcast;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    //请求通话推送
    public void sendRequestCallCast(Date date, JSONObject custom, String alias, String aliasType,String timestamp,String descriptions,String pushType) throws Exception{
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
}
