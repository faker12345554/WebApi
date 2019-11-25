package com.admin.admin.Dao;


import com.admin.admin.Entity.User;
import com.admin.model.ParamterModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    //新增
    int AddUser(@Param("user") User user);
    //修改用户
    int UpdateUser(@Param("user") User user);
    //删除
    int DelUser(@Param("Paramter") ParamterModel Paramter);
    //登录
    User Login(@Param("UserName") String UserName,@Param("Password") String Password);

    //用户列表
    List<User> UserList(@Param("flag") boolean flag);

    //查看
    User GetUser(@Param("id") int id);
}
