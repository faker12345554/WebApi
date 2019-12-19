package com.prisonapp.business.service.user;

import com.prisonapp.business.dao.user.UserDao;
import com.prisonapp.business.entity.user.UserModel;
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
