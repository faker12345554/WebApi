package com.adminapp.business.service.dw_user;

import com.adminapp.business.dao.dw_user.UserDao;
import com.adminapp.business.entity.dw_user.User;
import com.adminapp.page.PageBean;
import com.github.pagehelper.PageHelper;
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
    public PageBean<User> listUser(boolean flag, int PageSize, int PageIndex){
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】 分页写这 有问题嘛？有啊 比如我有100条数据 分10页 我要返回这个总数给前端 但是现在它只要
        //只有当前页的数量 好 我看看
        PageHelper.startPage(PageIndex, PageSize);

        List<User> allItems = userDao.listUser(flag);        //全部商品
        int countNums = allItems.size();            //总记录数
        System.out.println(allItems.size());
        PageBean<User> pageData = new PageBean<>(PageIndex, PageSize, countNums);
        pageData.setItems(allItems);
        //我系统封装了一个 直接丢给你试试
        return pageData;
    }
}

