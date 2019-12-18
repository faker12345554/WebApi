package com.prisonapp.business.controller;

import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.prisonapp.business.entity.User;
import com.prisonapp.business.service.UserService;
import com.prisonapp.token.TokenService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    private ResponseResult result = new ResponseResult();

    @ApiOperation(value = "登录")
    @PostMapping("/login")//
    public ResponseResult login(@RequestParam(required = false) String account, @RequestParam(required = false)  String password, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        User user=userService.login(account, password);
        String token = tokenService.getToken(user);
        return result.setData(token);
    }
}
