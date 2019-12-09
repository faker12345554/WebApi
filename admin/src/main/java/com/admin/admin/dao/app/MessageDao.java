package com.admin.admin.dao.app;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageDao {
    int getNotificationList( );
}
