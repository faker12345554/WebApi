package com.prisonapp.business.dao.user;

import com.prisonapp.business.entity.user.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    UserModel login(@Param("userName") String userName);
}
