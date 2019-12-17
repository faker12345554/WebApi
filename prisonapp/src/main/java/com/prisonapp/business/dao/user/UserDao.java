package com.prisonapp.business.dao.user;

import com.prisonapp.business.entity.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    User login(@Param("userName") String userName, @Param("password") String password);
}
