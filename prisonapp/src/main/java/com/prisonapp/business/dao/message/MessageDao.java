package com.prisonapp.business.dao.message;

import com.prisonapp.business.entity.message.MessageListModel;
import com.prisonapp.business.entity.message.MessageModel;
import com.prisonapp.business.entity.message.NotificationMessageModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDao {
    List<NotificationMessageModel> getNotificationList(@Param("userId") String userId );

    List<MessageListModel> getMessageList(@Param("type")String  type, @Param("count") int  count, @Param("requestCount") int requestCount, @Param("key") String key, @Param("userId") String userId);

    int readMessage(@Param("type")String  type,@Param("messageTimestamp")String  messageTimestamp,@Param("userId")String userId );

    List<MessageModel> searchNotification(@Param("key")String key ,@Param("userId")String userId);

    List<MessageModel> unreadCount(@Param("type")String  type,@Param("userId")String userId);

    List<MessageModel> messageTotalCount(@Param("type")String  type,@Param("userId")String userId);

}
