package com.adminapp.business.service.dw_login;

import com.adminapp.business.dao.dw_login.LoginDao;
import com.adminapp.business.entity.dw_user.User;
import com.adminapp.model.dw_login.UserInformationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    public User login(String account) {
        return loginDao.login(account);
    }

    //获取用户信息
    public UserInformationModel getUserInformation(String userId){
        return loginDao.getUserInformation(userId);
    }

    //修改用户密码
    public int updateUserPassword(String userId,String password){
        return loginDao.updateUserPassword(userId, password);
    }

    //获取用户角色
    public String getRoleName(int permissionId){
        return loginDao.getRoleName(permissionId);
    }


    //插入登录日志
    public int insertLoginLog(String userId){
        return loginDao.insertLoginLog(userId);
    }
}
