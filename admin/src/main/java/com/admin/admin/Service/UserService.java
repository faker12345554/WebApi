package com.admin.admin.Service;

import com.admin.admin.Dao.UserDao;
import com.admin.admin.Entity.User;
import com.admin.model.ParamterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    //新增
    public int AddUser(User t_user){
        return userDao.AddUser(t_user);
    }
    //修改
    public int UpdateUser(User t_user){
        return userDao.UpdateUser(t_user);
    }
    //删除
    public int DelUser(ParamterModel Paramter){
        return userDao.DelUser(Paramter);
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
    public List<User> GetUserList(boolean flag){
        return userDao.UserList(flag);
    }
}
