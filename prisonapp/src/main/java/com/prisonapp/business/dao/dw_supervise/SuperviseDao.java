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

    List<SuperviseModel> getTotalApplyLeaveList(@Param("statusCode")String  statusCode, @Param("userId")String userId);

    List<ApplyRecordModel> applyRecord(@Param("code")String  code);

    int submitApplyLeave(@Param("submitApplyLeaveModel")SubmitApplyLeaveModel submitApplyLeaveModel, @Param("startDate") Date startDate,@Param("endDate") Date endDate, @Param("code") String code, @Param("userId")String userId,@Param("personname")String personname);

    List<UserModel> getPersonname(@Param("userId")String userId);

    List<GetSuperviseTaskModel> getSuperviseTask(String userId);

    List<TPersoninformation> faceRecognize(@Param("userId")String userId);

    int insertFaceRecognize(String userId,int type,int result ,String upLoadFaceUrl);

    List<FaceRecognizeModel> getFaceRecognize(String userId,int type);

    int autoLocation(float latitude,float longitude,int locationType,String address,String userId,Date date);

    int uploadLocationError(String errorCode, String errorMsg,int userId,Date date);

    int uploadBattery(float percent,String userId,Date date);

    int batteryAlarm(String userId);
}
