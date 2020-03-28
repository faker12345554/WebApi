package com.admin.admin.dao.master.dw_user;


import com.admin.admin.entity.dw_user.User;
import com.admin.admin.entity.dw_user.Usermodel;
import com.admin.admin.entity.dw_userrole.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    //新增
    int saveUser(@Param("user") User user);
    //修改用户
    int updateUser(@Param("user") User user);
    //修改用户不包括密码
    int updateUserPassword(@Param("user") User user);
    //删除
    int deleteUser(@Param("flag") boolean flag, @Param("UserName") String UserName);
    //登录
    User login(@Param("UserName") String UserName,@Param("Password") String Password);

    //用户列表
    List<User> listUser(@Param("usermodel") Usermodel usermodel);

    //查看
    User getUser(@Param("UserName") String UserName,@Param("usersystem") int usersystem);

    User delGetUser(@Param("UserName") String UserName);

    //获取权限
    List<UserRole> getmenuid(@Param("permissionid") int permissionid);

    //根据用户名获取用户 查重
    int GetUserByAccountName(@Param("Account") String Account);

    //查询用户信息
    User GetUserByid(@Param("Id")int id);
}
