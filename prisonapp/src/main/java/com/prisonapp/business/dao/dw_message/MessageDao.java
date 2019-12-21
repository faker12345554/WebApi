package com.prisonapp.business.dao.dw_message;

import com.prisonapp.business.entity.dw_message.MessageListModel;
import com.prisonapp.business.entity.dw_message.MessageModel;
import com.prisonapp.business.entity.dw_message.NotificationMessageModel;
import com.prisonapp.business.entity.dw_message.SearchNotificationModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDao {
    List<NotificationMessageModel> getNotificationList(@Param("userId") String userId );

    List<MessageListModel> getMessageList(@Param("type")String  type, @Param("count") int  count, @Param("requestCount") int requestCount, @Param("key") String key, @Param("userId") String userId);

    int readMessage(@Param("type")String  type,@Param("messageTimestamp")String  messageTimestamp,@Param("userId")String userId );

    List<SearchNotificationModel> searchNotification(@Param("key")String key , @Param("userId")String userId);

    List<MessageModel> unreadCount(@Param("type")String  type,@Param("userId")String userId);

    List<MessageModel> messageTotalCount(@Param("type")String  type,@Param("userId")String userId);


}
