package com.prisonapp.business.controller.dw_user;

import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.dw_user.TokenModel;
import com.prisonapp.business.entity.dw_user.UserModel;
import com.prisonapp.business.service.dw_user.UserService;
import com.prisonapp.token.TokenService;
import com.prisonapp.token.tation.PassToken;
import com.prisonapp.token.tation.UserLoginToken;
import com.prisonapp.tool.CacheUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@Api(value="登录controller",tags={"用户登录及应用"})
@PassToken
@RestController
@RequestMapping("/app/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;
    private ResultSet result = new ResultSet();
    private TokenModel tokenModel=new TokenModel();
    //private TokenResult tokenResult =new TokenResult();


    @ApiOperation(value = "登录")
    @PostMapping("/login")//
    public ResultSet login(@ApiParam(name = "account",value = "账号")@RequestParam(required = false) String account,@ApiParam(name = "password",value = "密码") @RequestParam(required = false) String password, HttpServletResponse response){
       UserModel userModel=userService.login(account);

       if(userModel==null){
           result.resultCode=10;
           result.resultMsg="账号不存在";
           result.data="";
           return result;
       }
       else if(userModel.getPassword().equals(password)&&userModel.getStatus().equals("t")){
           CacheUtils.put("UserId",userModel.getId(),0);
           String token =tokenService.getToken(userModel);

           tokenModel.setToken(token);
           tokenModel.setrExpiresTime(token);
           tokenModel.setRefreshToken(token);
           tokenModel.settExpiresTime(token);
           result.resultCode=0;
           result.resultMsg="";
           result.data=tokenModel;
           return result;
       }else{
           result.resultCode=11;
           result.resultMsg="密码错误";
           result.data="";
           return result;
       }

    }

//    public ResultSet getUserInfo(){
//
//    }


}
