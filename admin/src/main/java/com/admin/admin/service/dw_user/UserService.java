package com.admin.admin.service.dw_user;

import com.admin.admin.dao.dw_user.UserDao;
import com.admin.admin.entity.dw_user.User;
import com.admin.admin.entity.dw_user.Usermodel;
import com.admin.admin.entity.dw_userrole.UserRole;
import com.admin.model.userrole.UserRoleModel;
import com.admin.page.PageBean;
import com.admin.tool.CacheUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

    public int updateUserPassword(User t_user) {

        return userDao.updateUserPassword(t_user);
    }

    //删除
    public int deleteUser(Boolean flag, String UserName) {

        return userDao.deleteUser(flag, UserName);
    }

    //登录
    public User login(String UserName, String Password) {


        return userDao.login(UserName, Password);
    }

    //获取
    public User getUser(String UserName,int usersystem) {


        return userDao.getUser(UserName, usersystem);
    }
    public User delGetUser(String UserName ) {


        return userDao.delGetUser(UserName);
    }
    //查看权限
    public List<UserRole> getmenuid(int  permissionid){
        return userDao.getmenuid(permissionid);
    }

    //用户列表
    public PageBean listUser(Usermodel usermodel) throws ParseException {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        User user = userDao.GetUserByid(Integer.parseInt(CacheUtils.get("UserId").toString()));
        usermodel.setUserid(user.getAccountname());
        usermodel.setId(Integer.parseInt(CacheUtils.get("UserId").toString()));
        PageHelper.startPage(usermodel.getPageIndex(),usermodel.getPageSize());
        List<User> allItems = userDao.listUser(usermodel);
        Collections.sort(allItems, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int a =o1.getId();
                int b= o2.getId();
                int c = a-b;
                return c;
            }
        });
        PageInfo<User> info = new PageInfo<>(allItems);//全部商品
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<User> pageData = new PageBean<>(usermodel.getPageIndex(),usermodel.getPageSize() , countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allItems);
        return pageData;


    }
}

