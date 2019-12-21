package com.prisonapp.business.service.dw_user;

import com.prisonapp.business.dao.dw_user.UserDao;
import com.prisonapp.business.entity.dw_user.UserModel;
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
