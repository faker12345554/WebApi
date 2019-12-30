package com.adminapp.business.service.dw_login;

import com.adminapp.business.dao.dw_login.LoginDao;
import com.adminapp.business.entity.dw_user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;

    public User login(String account) {
        return loginDao.login(account);
    }
}
