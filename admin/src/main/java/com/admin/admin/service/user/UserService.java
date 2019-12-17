package com.admin.admin.service.user;

import com.admin.admin.dao.user.UserDao;
import com.admin.admin.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao; // 改啥名字
    //新增
    public int saveUser(User t_user){
        return userDao.saveUser(t_user);
    }
    //修改
    public int updateUser(User t_user){
        return userDao.updateUser(t_user);
    }
    //删除
    public int deleteUser(Boolean flag,int UserId){
        return userDao.deleteUser(flag,UserId);
    }
    //登录
    public User login(String UserName,String Password){
        return userDao.login(UserName, Password);
    }
    //获取
    public User getUser(int id){
        return userDao.getUser(id);
    }
    //用户列表
    public List<User> listUser(boolean flag){
        return userDao.listUser(flag);
    }
}
