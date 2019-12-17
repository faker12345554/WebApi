package com.prisonapp.business.service.user;

import com.prisonapp.business.dao.user.UserDao;
import com.prisonapp.business.entity.user.User;
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
