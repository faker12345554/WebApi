package com.prisonapp.business.dao.dw_user;

import com.prisonapp.business.entity.dw_user.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    UserModel login(@Param("userName") String userName);
}
