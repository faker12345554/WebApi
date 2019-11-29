package com.admin.admin.dao;


import com.admin.admin.entity.User;
import com.admin.model.ParamterModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    //新增  方法名的话只能一个个改，类名的话可以
    int SaveUser(@Param("user") User user);
    //修改用户
    int UpdateUser(@Param("user") User user);
    //删除
    int DeleteUser(@Param("Paramter") ParamterModel Paramter);
    //登录
    User Login(@Param("UserName") String UserName,@Param("Password") String Password);

    //用户列表
    List<User> ListUser(@Param("flag") boolean flag);

    //查看
    User GetUser(@Param("id") int id);
}
