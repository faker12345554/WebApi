package com.prisonapp.business.dao.dw_user;

import com.prisonapp.business.entity.dw_supervise.TPersoninformation;
import com.prisonapp.business.entity.dw_user.GetUserInfoModel;
import com.prisonapp.business.entity.dw_user.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    UserModel login(@Param("userName") String userName);

    UserModel getUserid(@Param("account") String account);

    int insertLoginRecord(@Param("userid") int personid);

    UserModel getUser(@Param("phone") String phone);

    GetUserInfoModel getUserInfo(String userId);

    UserModel officephone(@Param("sponsoralarm") String sponsoralarm);

    List<UserModel> modifyPassword(String accountName, String password);

    int upModifyPassword(String accountName, String newPassword);

    TPersoninformation RelatedId(@Param("accountName")String accountName);
}
