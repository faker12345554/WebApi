package com.admin.admin.service.dw_user;

import com.admin.admin.dao.dw_user.UserDao;
import com.admin.admin.entity.dw_user.User;
import com.admin.config.CacheUtils;
import com.admin.page.PageBean;
import com.admin.token.TokenService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private TokenService tokenService;

    private ResponseResult result = new ResponseResult();

    //新增
    public ResponseResult saveUser(User t_user) {
        if (userDao.GetUserByAccountName(t_user.getAccountname()) > 1) {
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("用户名已存在!");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userDao.saveUser(t_user));
    }


    //修改
    public ResponseResult updateUser(User t_user) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userDao.updateUser(t_user));
    }

    //删除
    public ResponseResult deleteUser(Boolean flag, int UserId) {
        if (flag == true) {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("参数'flag'输入错误");
        } else if (userDao.getUser(UserId) == null) {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("参数'UserId'输入错误,该用户不存在");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userDao.deleteUser(flag, UserId));
    }

    //登录
    public ResponseResult login(String UserName, String Password,String Code) {

        String VerCode = String.valueOf(CacheUtils.get("验证码"));
        if (!VerCode.equals(Code)) {
            result.setCode(ResultCode.ILLEAGAL_STRING.getCode());
            result.setMessage(ResultCode.ILLEAGAL_STRING.getMessage());
            return result.setData("验证码不正确!");
        }

        User user = userDao.login(UserName, Password);
        CacheUtils.put("UserId", user.getId(), 0);
        String token = tokenService.getToken(user);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(token);
    }

    //获取
    public ResponseResult getUser(int id) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());

        return result.setData(userDao.getUser(id));
    }

    //用户列表
    public ResponseResult listUser(boolean flag, int PageSize, int PageIndex) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(PageIndex, PageSize);

        List<User> allItems = userDao.listUser(flag);
        PageInfo<User> info = new PageInfo<>(allItems);//全部商品
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<User> pageData = new PageBean<>(PageIndex, PageSize, countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allItems);
        //我系统封装了一个 直接丢给你试试
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(pageData);
    }
}

