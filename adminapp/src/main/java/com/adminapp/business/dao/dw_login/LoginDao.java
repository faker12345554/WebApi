package com.adminapp.business.dao.dw_login;

import com.adminapp.business.entity.dw_login.UserInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {
    UserInformation Login(String account);
}
