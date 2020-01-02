package com.prisonapp.business.dao.dw_user;

import com.prisonapp.business.entity.dw_user.GetUserInfoModel;
import com.prisonapp.business.entity.dw_user.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    UserModel login(@Param("userName") String userName);

    UserModel getUser(@Param("id") int id);

    GetUserInfoModel getUserInfo(String userId);

    List<UserModel> modifyPassword(String userId, String password);

    int upModifyPassword(String userId, String newPassword);
}
