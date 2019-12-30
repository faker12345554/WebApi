package com.adminapp.business.dao.dw_login;

import com.adminapp.business.entity.dw_user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginDao {
    User login(@Param("account") String account);
}
