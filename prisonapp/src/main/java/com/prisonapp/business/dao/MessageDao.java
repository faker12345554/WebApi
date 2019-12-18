package com.prisonapp.business.dao;

import com.prisonapp.business.entity.MessageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDao {
    List<MessageModel> getNotificationList( );
    List<MessageModel> getMessageList(@Param("type")int  type, @Param("count") int  count, @Param("requestCount") int requestCount, @Param("key") String key);
    int readMessage(@Param("type")int  type,@Param("messageTimestamp")String  messageTimestamp);
}
