package com.prisonapp.business.service;

import com.prisonapp.business.dao.UserDao;
import com.prisonapp.business.entity.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public UserModel login(String userName ) {
        return userDao.login(userName);
    }
}
