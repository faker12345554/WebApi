package com.prisonapp.business.dao;

import com.prisonapp.business.entity.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    UserModel login(@Param("userName") String userName);
}
