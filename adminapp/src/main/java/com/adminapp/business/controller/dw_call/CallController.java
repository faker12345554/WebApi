package com.adminapp.business.controller.dw_call;

import com.adminapp.business.entity.dw_call.SendphoneInformation;
import com.adminapp.business.entity.dw_user.UserModel;
import com.adminapp.business.service.dw_call.CallService;
import com.adminapp.business.service.dw_supervise.SuperviseService;
import com.adminapp.config.token.TokenUtil;
import com.adminapp.config.token.tation.PassToken;
import com.adminapp.config.token.tation.UserLoginToken;
import com.adminapp.model.dw_call.AcceptCallModel;
import com.adminapp.model.dw_call.CancelReturnModel;
import com.adminapp.model.dw_call.RequestCallReturnModel;
import com.adminapp.model.dw_supervise.PersonAllInformationModel;
import com.common.common.apppush.Demo;
import com.common.common.result.ResultSet;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

@RestController
@RequestMapping("/app/admin/call")
public class CallController {
    @Autowired
    public CallService callService;
    @Autowired
    public SuperviseService superviseService;

    @ApiOperation(value = "发出通话请求")
    @UserLoginToken
    @PostMapping("/requestCall")
    public ResultSet requestCall(@ApiParam(name = "type",value = "通话类型")@RequestParam(required = true)String type,
                                 @ApiParam(name = "code",value = "保外人员code")@RequestParam(required = true)String code) throws Exception {
        ResultSet rs=new ResultSet();
        RequestCallReturnModel requestCallReturnModel=new RequestCallReturnModel();
        String userId= TokenUtil.getTokenUserId();
        PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(code);
        if(personAllInformationModel.getSponsoralarm().equals(userId)) {
            SendphoneInformation sendphoneInformation=callService.checkOnline(code);    //判断是否正在通话
            UserModel userModel=callService.getUserInformation(userId);   //获取该工作人员信息
            if(sendphoneInformation==null) {
                String callToken = "TH";
                Random random = new Random();
                for (int i = 0; i < 20; i++) {
                    callToken += String.valueOf(random.nextInt(10));
                }
                Date date = new Date();
                String timeStamp = String.valueOf(date.getTime());
                //插入通话记录
                int insertRecord=callService.insertRecord(userModel.getAliasname(),callToken,timeStamp,personAllInformationModel.getPersonname(),type,personAllInformationModel.getPersonid(),userId);
                requestCallReturnModel.setCallToken(callToken);
                requestCallReturnModel.setCallTimestamp(timeStamp);
                requestCallReturnModel.setCallName(personAllInformationModel.getPersonname());
                requestCallReturnModel.setCallHeadUrl(personAllInformationModel.getFacepath());
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = requestCallReturnModel;

                //请求通话推送
                Calendar cal=new GregorianCalendar();
                cal.setTime(date);
                cal.add(Calendar.DATE,1);
                JSONObject object=new JSONObject();
                object.put("callName",userModel.getAliasname());
                object.put("callToken",callToken);
                object.put("type",type);
                object.put("callTimestamp",timeStamp);
                String descriptions="保外端请求通话推送";
                String pushType="PushRequestCall";
                callService.sendRequestCallCast(cal.getTime(),object,code,"ReleaseBailCode",timeStamp,descriptions,pushType);
            }else {
                rs.resultCode=21;
                rs.resultMsg="对方正在通话";
                rs.data=null;
            }
        }else{
            rs.resultCode=1;
            rs.resultMsg="无此人员";
            rs.data=null;
        }
        return rs;
    }

    @ApiOperation(value = "挂断通话")
    @UserLoginToken
    @PostMapping("/cancelCall")
    public ResultSet cancelCall(@ApiParam(name = "callToken",value = "通话标识")@RequestParam(required = true) String callToken,
                                @ApiParam(name = "type",value = "挂断类型")@RequestParam(required = true)String type) throws Exception {
        ResultSet rs=new ResultSet();
        if(type.equals("1")||type.equals("2")){
            SendphoneInformation sendphoneInformation=callService.getPhoneInformation(callToken);
            if(sendphoneInformation.getCanceltype()==null){
                Date date=new Date();
                String timestamp=String.valueOf(date.getTime());
                int updateCancelRecord=callService.updateCancelRecord(callToken,type,timestamp);
                CancelReturnModel cancelReturnModel=new CancelReturnModel();
                cancelReturnModel.setCancelTimestamp(timestamp);
                rs.resultCode=0;
                rs.resultMsg="";
                rs.data=cancelReturnModel;

                //请求通话推送
                Calendar cal=new GregorianCalendar();
                cal.setTime(date);
                cal.add(Calendar.DATE,1);
                JSONObject object=new JSONObject();
                object.put("callToken",callToken);
                object.put("cancelTimestamp",timestamp);
                String descriptions="保外端结束通话推送";
                String pushType="PushCancelCall";
                callService.sendRequestCallCast(cal.getTime(),object,sendphoneInformation.getPersonid(),"ReleaseBailCode",timestamp,descriptions,pushType);
            }else{
                rs.resultCode=1;
                rs.resultMsg="该通话已挂断";
                rs.data=null;
            }
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此类型";
            rs.data=null;
        }
        return rs;
    }

    @ApiOperation(value = "同意接收通话")
    @UserLoginToken
    @PostMapping("/acceptCall")
    public ResultSet acceptCall(@ApiParam(name = "callToken",value = "通话唯一标识")@RequestParam(required = true)String callToken) throws Exception {
        ResultSet rs=new ResultSet();
        SendphoneInformation sendphoneInformation=callService.getPhoneInformation(callToken);
        if(sendphoneInformation.getCanceltype()==null||sendphoneInformation.getCanceltype().equals("")) {
            String roomCode = "room";
            String serverUrl = "https://112.74.41.177";
            Date date=new Date();
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                roomCode += String.valueOf(random.nextInt(10));
            }
            int updateUrl = callService.updateUrlRecord(callToken, serverUrl, roomCode);
            AcceptCallModel acceptCallModel = new AcceptCallModel();
            acceptCallModel.setServerUrl(serverUrl);
            acceptCallModel.setRoomCode(roomCode);
            acceptCallModel.setType(sendphoneInformation.getCalltype());
            rs.resultCode = 0;
            rs.resultMsg = "";
            rs.data = acceptCallModel;

            //请求通话推送
            Calendar cal=new GregorianCalendar();
            cal.setTime(date);
            cal.add(Calendar.DATE,1);
            JSONObject object=new JSONObject();
            object.put("serverUrl",serverUrl);
            object.put("roomCode",roomCode);
            object.put("type",sendphoneInformation.getCalltype());
            object.put("callToken",callToken);
            String descriptions="保外端开始通话推送";
            String timestamp=String.valueOf(date.getTime());
            String pushType="PushStartCall";
            callService.sendRequestCallCast(cal.getTime(),object,sendphoneInformation.getPersonid(),"ReleaseBailCode",timestamp,descriptions,pushType);
        }else{
            rs.resultCode=1;
            rs.resultMsg="该通话已挂断";
            rs.data=null;
        }
        return rs;
    }

    @ApiOperation(value = "拒绝接收通话")
    @UserLoginToken
    @PostMapping("/refuseCall")
    public ResultSet refuseCall(@ApiParam(name = "callToken",value = "通话唯一标识")@RequestParam(required = true)String callToken) throws Exception {
        ResultSet rs=new ResultSet();
        SendphoneInformation sendphoneInformation=callService.getPhoneInformation(callToken);
        if(sendphoneInformation.getCanceltype()==null||sendphoneInformation.getCanceltype().equals("")) {
            Date date=new Date();
            String timestamp=String.valueOf(date.getTime());
            int updateCancelRecord=callService.updateCancelRecord(callToken,"3",timestamp);
            rs.resultCode=0;
            rs.resultMsg="";
            rs.data=new Object();

            //请求通话推送
            Calendar cal=new GregorianCalendar();
            cal.setTime(date);
            cal.add(Calendar.DATE,1);
            JSONObject object=new JSONObject();
            object.put("callToken",callToken);
            object.put("cancelTimestamp",timestamp);
            String descriptions="保外端结束通话推送";
            String pushType="PushCancelCall";
            callService.sendRequestCallCast(cal.getTime(),object,sendphoneInformation.getPersonid(),"ReleaseBailCode",timestamp,descriptions,pushType);
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="该通话已挂断";
            rs.data=null;
        }
        return rs;
    }
}
