package com.admin.admin.dao.master.dw_app;

import com.admin.admin.entity.dw_fsgayw.FsgaYwRyb;
import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDao {
    int getNotificationList( String UserId);

    List<FsgaYwRyb> getallpolice();

    int insertuser(@Param("user") User user);

    int updateUser(@Param("user") User user);

    List<Personinformation> getperson();


}
