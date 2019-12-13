package com.prisonapp.business.dao;

import com.prisonapp.business.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    User login(@Param("userName") String userName, @Param("password") String password);
}
