package com.adminapp.business.dao.dw_message;

import com.adminapp.business.entity.dw_message.MessageInformation;
import com.adminapp.model.dw_message.MessageListModel;
import com.adminapp.model.dw_message.NotificationListModel;
import com.adminapp.model.dw_supervise.PersonAllInformationModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDao {

    List<PersonAllInformationModel> getUserControlPerson(@Param("userId")String userId);

    List<MessageInformation> listNotifications(@Param("personId")String personId);

    List<MessageListModel> listAllMessages(@Param("personId")String personId);

    int updateReadMessage(@Param("id")int id);

}
