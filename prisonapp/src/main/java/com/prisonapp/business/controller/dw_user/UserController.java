package com.prisonapp.business.controller.dw_user;

import com.common.common.result.ResultSet;
import com.common.common.result.TokenResult;
import com.prisonapp.business.entity.dw_user.TokenModel;
import com.prisonapp.business.entity.dw_user.UserModel;
import com.prisonapp.business.service.dw_user.UserService;
import com.prisonapp.token.TokenService;
import com.prisonapp.tool.CacheUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
    public ResultSet login(@RequestParam(required = false) String accountName,@RequestParam(required = false) String password, HttpServletResponse response){
       UserModel userModel=userService.login(accountName);

       if(userModel==null){
           result.resultCode=10;
           result.resultMsg="账号不存在";
           result.data="";
           return result;
       }
       else if(userModel.getPassword().equals(password)&&userModel.getStatus().equals("t")){
           CacheUtils.put("UserId",userModel.getId(),0);
          // String token =tokenResult.GetToken(userModel.getId()+"",userModel.getPhone());
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
}
