package com.adminapp.business.dao.dw_user;

import com.adminapp.business.entity.dw_user.User;
import com.adminapp.business.entity.dw_user.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    UserModel login(@Param("userName") String userName);

    UserModel getUser(@Param("id") int id);
}
