package com.adminapp.business.controller.dw_login;

import com.adminapp.business.entity.dw_login.UserInformation;
import com.adminapp.business.service.dw_login.LoginService;

import com.prisonapp.business.entity.user.TokenModel;
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

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResultSet Login(@ApiParam(name = "account", value = "登陆账号") @RequestParam(required = true) String account, @ApiParam(name = "password", value = "密码") @RequestParam(required = true) String password) {
        try {
            UserInformation user = loginService.Login(account);
            if (user != null && user.getStatus().equals("t")) {                //判断账号是否存在
                String token = "1144556677889999";
                tokenModel.setToken(token);
                tokenModel.setrExpiresTime(token);
                tokenModel.setRefreshToken(token);
                tokenModel.settExpiresTime(token);
                if (user.getPassword().equals(password)) {    //判断密码是否正确
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
