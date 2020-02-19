package com.adminapp.business.dao.dw_login;

import com.adminapp.business.entity.dw_user.User;
import com.adminapp.model.dw_login.UserInformationModel;
import com.adminapp.model.dw_login.WorkUserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface LoginDao {
    User login(@Param("account") String account);

    UserInformationModel getUserInformation(@Param("userId")String userId);

    int updateUserPassword(@Param("userId")String userId,@Param("password")String password);

    String getRoleName(@Param("permissionId")int permissionId);

    int insertLoginLog(@Param("userId")int userId);
}
