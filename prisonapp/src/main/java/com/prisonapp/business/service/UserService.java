package com.prisonapp.business.service;

import com.prisonapp.business.dao.UserDao;
import com.prisonapp.business.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User login(String userName, String password) {
        return userDao.login(userName,password);
    }
}
