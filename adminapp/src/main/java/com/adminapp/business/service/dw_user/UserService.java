package com.adminapp.business.service.dw_user;

import com.adminapp.business.dao.dw_user.UserDao;
import com.adminapp.business.entity.dw_user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao; // 改啥名字
    public UserModel login(String userName ) {
        return userDao.login(userName);
    }
}

