package com.prisonapp.business.service.dw_supervise;

import com.prisonapp.business.dao.dw_supervise.SuperviseDao;
import com.prisonapp.business.entity.dw_supervise.*;
import com.prisonapp.business.entity.dw_user.UserModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SuperviseService {
    @Autowired
    private SuperviseDao superviseDao ;

    public List<GetApplyLeaveListModel> getApplyLeaveList(String statusCode, int count, int requestCount, String userId){
        return superviseDao.getApplyLeaveList(statusCode,count,requestCount,userId);
    }

    public List<GetApplyLeaveListModel> getAllApplyLeaveList( int count, int requestCount, String userId){
        return superviseDao.getAllApplyLeaveList(count,requestCount,userId);
    }
    //外出申请列表的总数
    public List<SuperviseModel> getTotalApplyLeaveList(String statusCode, String userId){
        return superviseDao.getTotalApplyLeaveList(statusCode,userId);
    }

    //外出申请列表的第三级
    public List<ApplyRecordModel> applyRecord(String code){
        return superviseDao.applyRecord(code);
    }

    public int submitApplyLeave(SubmitApplyLeaveModel submitApplyLeaveModel, long startDate,long endDate, String code, String userId,String personname){
        return superviseDao.submitApplyLeave(submitApplyLeaveModel,startDate,endDate,code,userId,personname);
    }

    public List<UserModel> getPersonname(String userId){
        return superviseDao.getPersonname(userId);
    }


    public List<GetSuperviseTaskModel> getSuperviseTask(String userId){
        return superviseDao.getSuperviseTask(userId);
    }

    public List<TPersoninformation> faceRecognize(String userId){
        return superviseDao.faceRecognize(userId);
    }

    public int  insertFaceRecognize(String userId,int type,int result ,String upLoadFaceUrl){
        return superviseDao.insertFaceRecognize(userId,type,result,upLoadFaceUrl);
    }

    public List<FaceRecognizeModel> getFaceRecognize(String userId,int  type){
        return superviseDao.getFaceRecognize(userId,type);
    }

    public  int autoLocation(float latitude,float longitude,int locationType,String address,String userId,Date date,boolean fScope){

        return  superviseDao.autoLocation(latitude, longitude,locationType,address,userId,date,fScope);
    }

    public int uploadLocationError(String errorCode, String errorMsg,int userId,Date date){
        return  superviseDao.uploadLocationError(errorCode, errorMsg,userId,date);
    }

    public int uploadBattery(float percent,String userId,Date date){
        return  superviseDao.uploadBattery(percent,userId,date);
    }

    public int batteryAlarm(String userId,String content){
        return  superviseDao.batteryAlarm( userId,content);
    }

    public TRemindersettings getLocationConfig( ){
        return  superviseDao.getLocationConfig();
    }

//    public  GetSuperviseConfigModel getBatteryConfigTimestamp(String userId){
//        return  superviseDao.getBatteryConfigTimestamp( userId);
//    }
    public  TEnclosure getPolygon(String userId){
        return  superviseDao.getPolygon( userId);
    }

//    public  int updateFscope(String userId,boolean fscope){
//        return  superviseDao.updateFscope( userId,fscope);
//    }
    public int insertFscope(String userId,String content){
        return  superviseDao.insertFscope( userId,content);
    }

}
