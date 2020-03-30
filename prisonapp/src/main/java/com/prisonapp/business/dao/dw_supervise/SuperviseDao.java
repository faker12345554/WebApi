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

    List<SuperviseModel> getTotalApplyLeaveList( @Param("userId")String userId);

    List<ApplyRecordModel> applyRecord(@Param("code")String  code);

//    int submitApplyLeave(@Param("city")String city,@Param("cityCode")String cityCode,@Param("district")String district,@Param("districtCode")String districtCode,
//                         @Param("province")String province,@Param("provinceCode")String provinceCode,@Param("reason")String reason,@Param("reasonAudioUrl")String reasonAudioUrl ,
//                         @Param("endDate")long endDate,@Param("startDate")long startDate,  @Param("code") String code,
//                         @Param("userId")String userId,@Param("personName")String personName);

   // int submitApplyLeave(@Param("userId")String userId);


    int submitApplyLeave (@Param("city")String city,@Param("cityCode")String cityCode,@Param("district")String district,@Param("districtCode")String districtCode,
                         @Param("province")String province,@Param("provinceCode")String provinceCode,@Param("reason")String reason,@Param("reasonAudioUrl")String reasonAudioUrl ,
                         @Param("endDate")long endDate,@Param("startDate")long startDate,  @Param("code") String code,
                         @Param("userId")String userId,@Param("personName")String personName,@Param("sponsorAlarm")String sponsorAlarm ,@Param("address")String address,
                          @Param("pendingReview")String pendingReview,@Param("messageContent")String messageContent);

    TEnum getReview();

    List<TPersoninformation> getPersonname(@Param("userId")String userId);

    List<GetSuperviseTaskModel> getSuperviseTask(String userId);

    List<TPersoninformation> faceRecognize(@Param("userId")String userId);

    TEnum getQDLX(@Param("typecode")String typecode,@Param("enumcode")String enumcode);

    int insertFaceRecognize(String userId,int type,int result ,String upLoadFaceUrl,String typename);

    List<FaceRecognizeModel> getFaceRecognize(String userId,int type);

    int autoLocation(float latitude,float longitude,int locationType,String address,String userId,Date date,boolean fScope);

    UserModel getUserId(@Param("personid")String userId);

    int uploadLocationError(String errorCode, String errorMsg,int userId,Date date);

    int uploadBattery(float percent,String userId,Date date);

    int batteryAlarm(@Param("userId")String userId,@Param("content")String content);

    TPrisonsetting getLocationConfig( String personid,int settingcode);

    TRemindersettings getLocationConfigTime();

  //  GetSuperviseConfigModel getBatteryConfigTimestamp(String userId);

    TEnclosure getPolygon(String userId);

  //  int updateFscope(String userId,boolean fscope) ;

    int insertFscope(String userId,String content);

    TPersoninformation RelatedId(@Param("accountName")String accountName);

    int insertVoice (@Param("personid")String personid,@Param("type")int type,@Param("result")int result,@Param("filepath")String filepath,@Param("typename")String typename);
}
