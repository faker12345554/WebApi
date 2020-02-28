package com.adminapp.business.dao.dw_call;

import com.adminapp.business.entity.dw_call.SendphoneInformation;
import com.adminapp.business.entity.dw_user.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CallDao {

    SendphoneInformation checkOnline(@Param("code")String code);

    int insertRecord(@Param("sendName")String sendName,@Param("callToken")String callToken,@Param("callTimestamp")String callTimestamp,@Param("callName")String callName,
                     @Param("callType")String callType,@Param("personId")String personId,@Param("accountName")String accountName);

    UserModel getUserInformation(@Param("userId")String userId);
}
