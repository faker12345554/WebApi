package com.adminapp.business.controller.dw_login;

import com.adminapp.business.entity.dw_user.TokenModel;
import com.adminapp.business.entity.dw_user.User;
import com.adminapp.business.entity.dw_user.UserModel;
import com.adminapp.business.service.dw_login.LoginService;
import com.adminapp.business.service.dw_user.UserService;
import com.adminapp.config.CacheUtils;
import com.adminapp.config.token.TokenService;
import com.adminapp.config.token.tation.PassToken;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.common.common.result.ResultSet;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/admin/user")
public class LoginController {
    public ResultSet rs=new ResultSet();

    public TokenModel tokenModel=new TokenModel();
    @Autowired
    public LoginService loginService;
    @Autowired
    public TokenService tokenService;

    @Autowired
    public UserService userService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    @PassToken
    public ResultSet Login(@ApiParam(name = "account", value = "登陆账号") @RequestParam(required = true) String account, @ApiParam(name = "password", value = "密码") @RequestParam(required = true) String password) {
        try {
            CacheUtils.put("UserId",account);

            UserModel userInformation = userService.login(account);
            UserModel user=new UserModel();
            user.setId(userInformation.getId());
            user.setAccountname(userInformation.getAccountname());
            user.setPassword(userInformation.getPassword());
            String name=userInformation.getAliasname();
            CacheUtils.put("UserName",name);
            if (userInformation != null) {                //判断账号是否存在
                String token =tokenService.getToken(user);
                tokenModel.setToken(token);
                tokenModel.setrExpiresTime(token);
                tokenModel.setRefreshToken(token);
                tokenModel.settExpiresTime(token);
                if (userInformation.getPassword().equals(password)) {    //判断密码是否正确
                    rs.resultCode = 0;
                    rs.resultMsg = "";
                    rs.data = tokenModel;
                } else {
                    rs.resultCode = 11;
                    rs.resultMsg = "密码错误";
                    rs.data = null;
                }
            } else {
                rs.resultCode = 10;
                rs.resultMsg = "此账号不存在";
                rs.data = null;
            }
        }catch (Exception e){
            rs.resultCode=1;
            rs.resultMsg=e.getMessage();
            rs.data=null;
        }
        return rs;
    }
}
