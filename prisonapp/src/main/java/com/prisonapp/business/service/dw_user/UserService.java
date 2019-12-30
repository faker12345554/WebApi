package com.prisonapp.business.service.dw_user;

import com.prisonapp.business.dao.dw_user.UserDao;
import com.prisonapp.business.entity.dw_user.GetUserInfoModel;
import com.prisonapp.business.entity.dw_user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserModel login(String userName ) {
        return userDao.login(userName);
    }

    public GetUserInfoModel getUserInfo(String userId){
        return userDao.getUserInfo(userId);
    }

    public List<UserModel> modifyPassword(String userId, String password){
        return userDao.modifyPassword(userId,password);
    }

    public int upModifyPassword(String userId, String newPassword){
        return userDao.upModifyPassword(userId,newPassword);
    }
}
