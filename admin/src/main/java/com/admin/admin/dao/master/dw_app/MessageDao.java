package com.admin.admin.dao.master.dw_app;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageDao {
    int getNotificationList( String UserId);
}
