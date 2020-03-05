package com.prisonapp.business.controller.dw_call;


import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.dw_call.AcceptModel;
import com.prisonapp.business.entity.dw_call.CallModel;
import com.prisonapp.business.entity.dw_call.CancelModel;
import com.prisonapp.business.entity.dw_call.TSendphone;
import com.prisonapp.business.entity.dw_supervise.TPersoninformation;
import com.prisonapp.business.service.dw_call.CallService;
import com.prisonapp.business.service.dw_message.MessageService;
import com.prisonapp.business.util.PersonInformationUtil;
import com.prisonapp.token.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

@Api(value="通话controller",tags={"音视频通话"})
@RestController
@RequestMapping("/app/call")
public class CallController {

    @Autowired
    private CallService callService ;

    @ApiOperation(value = " 发出通话请求")
    @PostMapping("/requestCall")
    public ResultSet requestCall(String type) throws Exception {//1 为语音通话，2 为视频通话
        ResultSet result = new ResultSet();
        CallModel callModel = new CallModel();

        //获取保外人员的信息，主要是取出相应的警员
        String personid =getPersonId();
        TPersoninformation tPersoninformation =callService.getPersoninformation(getPersonId());
        callService.makeCall(type);
        TSendphone sendphone=callService.checkOnline(tPersoninformation.getSponsoralarm());    //根据警号判断是否正在通话
        if(sendphone==null) {
            String callToken = "th";
            Random random = new Random();
            for (int i = 0; i < 20; i++) {
                callToken += String.valueOf(random.nextInt(10));
            }
            Date date = new Date();
            String timeStamp = String.valueOf(date.getTime());
            //插入通话记录
            int insertRecord=callService.insertRecord(callToken,timeStamp,type,tPersoninformation.getSponsor(),tPersoninformation.getPersonname(),tPersoninformation.getPersonid(),tPersoninformation.getSponsoralarm());
            callModel.setCallToken(callToken);
            callModel.setCallTimestamp(timeStamp);
            callModel.setCallName(tPersoninformation.getSponsor());
            result.resultCode = 0;
            result.resultMsg = "";
            result.data = callModel;

            //请求通话推送
            Calendar cal=new GregorianCalendar();
            cal.setTime(date);
            cal.add(Calendar.DATE,1);
            JSONObject object=new JSONObject();
            object.put("callName",tPersoninformation.getPersonname());//发起方姓名
            object.put("callToken",callToken);
            object.put("type",type);
            object.put("callTimestamp",timeStamp);
            String descriptions="管理端请求通话推送";
            String pushType="PushRequestCall";
            callService.sendRequestCallCast(cal.getTime(),object,tPersoninformation.getSponsoralarm(),"ReleaseAdminCode",timeStamp,descriptions,pushType);
        }else {
            result.resultCode=21;
            result.resultMsg="对方正在通话";
            result.data=null;
        }
        return result;
    }


    @ApiOperation(value = " 挂断通话")
    @PostMapping("/cancelCall")
    public ResultSet cancelCall(@ApiParam(name = "callToken",value = "通话标识")@RequestParam(required = true) String callToken,
                                @ApiParam(name = "type",value = "挂断类型")@RequestParam(required = true)String type) throws Exception {
        ResultSet resultSet = new ResultSet();
        if(type.equals("1")||type.equals("2")){
            TSendphone tSendphone=callService.getPhoneInformation(callToken);
            if(tSendphone.getCanceltype()==null){
                Date date=new Date();
                String timestamp=String.valueOf(date.getTime());
                int updateCancelRecord=callService.updateCancelRecord(callToken,type,timestamp);
                CancelModel cancelModel=new CancelModel();
                cancelModel.setCancelTimestamp(timestamp);
                resultSet.resultCode=0;
                resultSet.resultMsg="";
                resultSet.data=cancelModel;

                //结束通话推送
                Calendar cal=new GregorianCalendar();
                cal.setTime(date);
                cal.add(Calendar.DATE,1);
                JSONObject object=new JSONObject();
                object.put("callToken",callToken);
                object.put("cancelTimestamp",timestamp);
                String descriptions="管理端结束通话推送";
                String pushType="PushCancelCall";
                callService.sendRequestCallCast(cal.getTime(),object,tSendphone.getAccountname(),"ReleaseAdminCode",timestamp,descriptions,pushType);
            }else{
                resultSet.resultCode=1;
                resultSet.resultMsg="该通话已挂断";
                resultSet.data=null;
            }
        }
        else{
            resultSet.resultCode=1;
            resultSet.resultMsg="无此类型";
            resultSet.data=null;
        }
        return  resultSet;
    }


    @ApiOperation(value = " 同意接收通话")
    @PostMapping("/acceptCall")
    public ResultSet acceptCall(@ApiParam(name = "callToken",value = "通话标识")@RequestParam(required = true) String callToken) throws Exception {
        ResultSet resultSet = new ResultSet();
        TSendphone tSendphone = callService.getPhoneInformation(callToken);
        if (tSendphone.getCanceltype() == null || tSendphone.getCanceltype().equals("")) {
            String roomCode = "room";
            String serverUrl = "https://112.74.41.177";
            Date date = new Date();
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                roomCode += String.valueOf(random.nextInt(10));
            }
            int updateUrl = callService.updateUrlRecord(callToken, serverUrl, roomCode);
            AcceptModel acceptModel = new AcceptModel();
            acceptModel.setServerUrl(serverUrl);
            acceptModel.setRoomCode(roomCode);
            acceptModel.setType(tSendphone.getCalltype());
            resultSet.resultCode = 0;
            resultSet.resultMsg = "";
            resultSet.data = acceptModel;

            //开始通话推送
            Calendar cal=new GregorianCalendar();
            cal.setTime(date);
            cal.add(Calendar.DATE,1);
            JSONObject object=new JSONObject();
            object.put("serverUrl",serverUrl);//发起方姓名
            object.put("roomCode",roomCode);
            object.put("type",tSendphone.getCalltype());
            object.put("callToken",callToken);
            String descriptions="管理端同意通话推送";
            String timestamp=String.valueOf(date.getTime());
            String pushType="PushRequestCall";
            callService.sendRequestCallCast(cal.getTime(),object,tSendphone.getAccountname(),"ReleaseAdminCode",timestamp,descriptions,pushType);
        }else{
            resultSet.resultCode=1;
            resultSet.resultMsg="该通话已挂断";
            resultSet.data=null;
        }

        return resultSet;
    }


    @ApiOperation(value = " 拒绝接收通话")
    @PostMapping("/refuseCall")
    public ResultSet refuseCall(@ApiParam(name = "callToken",value = "通话标识")@RequestParam(required = true) String callToken) throws Exception {
        ResultSet resultSet = new ResultSet();
        TSendphone tSendphone = callService.getPhoneInformation(callToken);
        if (tSendphone.getCanceltype() == null || tSendphone.getCanceltype().equals("")) {
            Date date = new Date();
            String timestamp = String.valueOf(date.getTime());
            int updateCancelRecord = callService.updateCancelRecord(callToken, "3", timestamp);
            resultSet.resultCode = 0;
            resultSet.resultMsg = "";
            resultSet.data = new Object();

            //结束通话推送
            Calendar cal=new GregorianCalendar();
            cal.setTime(date);
            cal.add(Calendar.DATE,1);
            JSONObject object=new JSONObject();
            object.put("callToken",callToken);
            object.put("cancelTimestamp",timestamp);
            String descriptions="管理端结束通话推送";
            String pushType="PushCancelCall";
            callService.sendRequestCallCast(cal.getTime(),object,tSendphone.getAccountname(),"ReleaseAdminCode",timestamp,descriptions,pushType);
        } else {
            resultSet.resultCode = 1;
            resultSet.resultMsg = "该通话已挂断";
            resultSet.data = null;
        }
        return resultSet;

    }


    public  String getPersonId(){
        TPersoninformation tPersoninformation = callService.RelatedId(TokenUtil.getTokenUserId());//根据user中的手机号去取出personid
        String personid = tPersoninformation.getPersonid();
        return personid;
    }


}
