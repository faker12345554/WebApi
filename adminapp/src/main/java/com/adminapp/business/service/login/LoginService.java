package com.adminapp.business.service.login;

import com.adminapp.business.dao.login.LoginDao;
import com.adminapp.business.entity.login.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    public UserInformation Login(String account) {
        return loginDao.Login(account);
    }
}
