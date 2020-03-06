package com.prisonapp.business.dao.dw_call;

import com.prisonapp.business.entity.dw_call.TSendphone;
import com.prisonapp.business.entity.dw_supervise.TPersoninformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CallDao {

    TPersoninformation getPersoninformation(@Param("personid") String  personid);

     int makeCall(@Param("type") String  type);

    TSendphone checkOnline(@Param("Sponsoralarm")String Sponsoralarm);

    int insertRecord(@Param("callToken") String callToken,@Param("timeStamp")String timeStamp,@Param("type")String type,
                     @Param("callname")String callname,@Param("sendname")String sendname,@Param("personid")String personid,@Param("accountname") String accountname);

    TSendphone getPhoneInformation(@Param("callToken")String callToken);

    int updateCancelRecord(@Param("callToken")String callToken,@Param("type")String type,@Param("timestamp")String timestamp);

    int updateUrlRecord(@Param("callToke")String callToken,@Param("serverUrl")String serverUrl,@Param("roomCode")String roomCode);



    TPersoninformation RelatedId(@Param("accountName")String accountName);
}
