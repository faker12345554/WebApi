package com.adminapp.business.dao.login;

import com.adminapp.business.entity.login.UserInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {
    UserInformation Login(String account);
}
