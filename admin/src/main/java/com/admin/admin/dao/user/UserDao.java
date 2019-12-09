package com.admin.admin.dao.user;


import com.admin.admin.entity.user.User;
import com.admin.model.ParamterModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    //新增  方法名的话只能一个个改，类名的话可以
    int saveUser(@Param("user") User user);
    //修改用户
    int updateUser(@Param("user") User user);
    //删除
    int deleteUser(@Param("Paramter") ParamterModel Paramter);
    //登录
    User login(@Param("UserName") String UserName,@Param("Password") String Password);

    //用户列表
    List<User> listUser(@Param("flag") boolean flag);

    //查看
    User getUser(@Param("id") int id);
}
