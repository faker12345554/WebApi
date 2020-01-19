package com.prisonapp.business.service.dw_user;

import com.prisonapp.business.dao.dw_user.UserDao;
import com.prisonapp.business.entity.dw_supervise.TPersoninformation;
import com.prisonapp.business.entity.dw_user.GetUserInfoModel;
import com.prisonapp.business.entity.dw_user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserModel login(String userName) {
        return userDao.login(userName);
    }

    public GetUserInfoModel getUserInfo(String userId) {
        return userDao.getUserInfo(userId);
    }
    public UserModel officephone(String sponsoralarm){
        return userDao.officephone(sponsoralarm);
    }

    public List<UserModel> modifyPassword(String accountName, String password) {
        return userDao.modifyPassword(accountName, password);
    }

    public int upModifyPassword(String accountName, String newPassword) {
        return userDao.upModifyPassword(accountName, newPassword);
    }

    public TPersoninformation RelatedId(String accountName) {
        return userDao.RelatedId(accountName);
    }
}
