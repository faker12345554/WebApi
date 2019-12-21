package com.admin.admin.service.dw_user;

import com.admin.admin.dao.dw_user.UserDao;
import com.admin.admin.entity.dw_user.User;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;




    public int GetUserByAccountName(String AccountName){
        return  userDao.GetUserByAccountName(AccountName);
    }
    //新增
    public int saveUser(User t_user) {

        return userDao.saveUser(t_user);
    }


    //修改
    public int updateUser(User t_user) {

        return userDao.updateUser(t_user);
    }

    //删除
    public int deleteUser(Boolean flag, int UserId) {

        return userDao.deleteUser(flag, UserId);
    }

    //登录
    public User login(String UserName, String Password) {


        return userDao.login(UserName, Password);
    }

    //获取
    public User getUser(int id) {


        return userDao.getUser(id);
    }

    //用户列表
    public List<User> listUser(boolean flag) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】

        //我系统封装了一个 直接丢给你试试


        return userDao.listUser(flag);
    }
}

