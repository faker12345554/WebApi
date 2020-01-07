package com.prisonapp.business.dao.dw_supervise;

import com.prisonapp.business.entity.dw_supervise.*;
import com.prisonapp.business.entity.dw_user.UserModel;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SuperviseDao {
    List<GetApplyLeaveListModel> getApplyLeaveList(@Param("statusCode")String  statusCode,@Param("count")int count,@Param("requestCount")int requestCount, @Param("userId")String userId);

    List<GetApplyLeaveListModel> getAllApplyLeaveList(@Param("count")int count,@Param("requestCount")int requestCount, @Param("userId")String userId);

    List<SuperviseModel> getTotalApplyLeaveList(@Param("statusCode")String  statusCode, @Param("userId")String userId);

    List<ApplyRecordModel> applyRecord(@Param("code")String  code);

    int submitApplyLeave(@Param("submitApplyLeaveModel")SubmitApplyLeaveModel submitApplyLeaveModel, @Param("startDate") long startDate,@Param("endDate") long endDate, @Param("code") String code, @Param("userId")String userId,@Param("personname")String personname);

    List<UserModel> getPersonname(@Param("userId")String userId);

    List<GetSuperviseTaskModel> getSuperviseTask(String userId);

    List<TPersoninformation> faceRecognize(@Param("userId")String userId);

    int insertFaceRecognize(String userId,int type,int result ,String upLoadFaceUrl);

    List<FaceRecognizeModel> getFaceRecognize(String userId,int type);

    int autoLocation(float latitude,float longitude,int locationType,String address,String userId,Date date,boolean fScope);

    int uploadLocationError(String errorCode, String errorMsg,int userId,Date date);

    int uploadBattery(float percent,String userId,Date date);

    int batteryAlarm(String userId,String content);

    TRemindersettings getLocationConfig();

  //  GetSuperviseConfigModel getBatteryConfigTimestamp(String userId);

    TEnclosure getPolygon(String userId);

  //  int updateFscope(String userId,boolean fscope) ;

    int insertFscope(String userId,String content);
}
