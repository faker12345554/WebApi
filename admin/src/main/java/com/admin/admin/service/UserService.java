package com.admin.admin.service;

import com.admin.admin.dao.UserDao;
import com.admin.admin.entity.User;
import com.admin.model.ParamterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao; // 改啥名字
    //新增
    public int SaveUser(User t_user){
        return userDao.SaveUser(t_user);
    }
    //修改
    public int UpdateUser(User t_user){
        return userDao.UpdateUser(t_user);
    }
    //删除
    public int DeleteUser(ParamterModel Paramter){
        return userDao.DeleteUser(Paramter);
    }
    //登录
    public User Login(String UserName,String Password){
        return userDao.Login(UserName, Password);
    }
    //获取
    public User GetUser(int id){
        return userDao.GetUser(id);
    }
    //用户列表
    public List<User> ListUser(boolean flag){
        return userDao.ListUser(flag);
    }
}
