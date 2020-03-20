package com.adminapp.business.controller.dw_login;

import com.adminapp.business.entity.dw_user.TokenModel;
import com.adminapp.business.entity.dw_user.User;
import com.adminapp.business.entity.dw_user.UserModel;
import com.adminapp.business.service.dw_login.LoginService;
import com.adminapp.business.service.dw_supervise.SuperviseService;
import com.adminapp.business.service.dw_user.UserService;
import com.adminapp.config.CacheUtils;
import com.adminapp.config.token.TokenService;
import com.adminapp.config.token.TokenUtil;
import com.adminapp.config.token.tation.PassToken;
import com.adminapp.config.token.tation.UserLoginToken;
import com.adminapp.model.dw_login.UserInformationModel;
import com.adminapp.model.dw_login.WorkUserModel;
import com.adminapp.model.dw_supervise.PersonAllInformationModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.common.common.result.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

@RestController
@Api(value="工作人员登录Controller",tags={"工作人员账号管理"})
@RequestMapping("/app/admin/user")
public class LoginController {

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
    public ResultSet Login(@ApiParam(name = "account", value = "登陆账号") @RequestParam(required = true) String account,
                           @ApiParam(name = "password", value = "密码") @RequestParam(required = true) String password,
                           HttpServletResponse response, HttpServletRequest request) {
        ResultSet rs=new ResultSet();
        try {
            UserModel userInformation = userService.login(account);
            if (userInformation != null) {                //判断账号是否存在
                CacheUtils.put("UserId",String.valueOf(userInformation.getId()));
                CacheUtils.put("UserName",userInformation.getAliasname());
                UserModel user=new UserModel();
                user.setId(userInformation.getId());
                user.setAccountname(userInformation.getAccountname());
                user.setPassword(userInformation.getPassword());
                String name=userInformation.getAliasname();
                String token =tokenService.getToken(user);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);
                String  ExpiresTime =String.valueOf(calendar.getTimeInMillis());
                tokenModel.setToken(token);
                tokenModel.setrExpiresTime(ExpiresTime);
                tokenModel.setRefreshToken(token);
                tokenModel.settExpiresTime(ExpiresTime);
                if (userInformation.getPassword().equals(password)) {    //判断密码是否正确
                    //int insertLoginLog=loginService.insertLoginLog(userInformation.getId());   //插入登录日志
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

    @UserLoginToken
    @ApiOperation(value = "修改密码")
    @PostMapping("/modifyPassword")
    public ResultSet modifyPassword(@ApiParam(name="password",value = "原密码MD5")@RequestParam(required = true)String password,
                                    @ApiParam(name="newPassword",value = "新密码MD5")@RequestParam(required = true)String newPassword){
        ResultSet rs=new ResultSet();
        String userId= TokenUtil.getTokenUserId();
        if(password.equals(newPassword)==false) {
            UserInformationModel userInformationModel = loginService.getUserInformation(userId);
            if (userInformationModel.getPassword().equals(password)) {
                int udpateUserPassword=loginService.updateUserPassword(userId,newPassword);
                if(udpateUserPassword!=0){
                    rs.resultCode=0;
                    rs.resultMsg="";
                    rs.data=new Object();
                }
                else{
                    rs.resultCode=1;
                    rs.resultMsg="修改失败";
                    rs.data=null;
                }
            } else {
                rs.resultCode = 11;
                rs.resultMsg = "原密码错误";
                rs.data = null;
            }
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="原密码与新密码一致";
            rs.data=null;
        }
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "获取工作人员信息")
    @GetMapping("/getUserInfo")
    public ResultSet getUserInfo(){
        ResultSet rs=new ResultSet();
        String userId=TokenUtil.getTokenUserId();
        UserInformationModel userInformationModel=loginService.getUserInformation(userId);
        String roleName=loginService.getRoleName(userInformationModel.getPermissionid());
        WorkUserModel workUserModel=new WorkUserModel();
        workUserModel.setCode(userId);
        workUserModel.setAccount(userId);
        workUserModel.setPoliceNum(userId);
        workUserModel.setName(userInformationModel.getAliasname());
        workUserModel.setRole(roleName);
        workUserModel.setArea(userInformationModel.getAreaname());
        workUserModel.setUnits(userInformationModel.getPolice());
        workUserModel.setDepartment(userInformationModel.getDepartment());
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=workUserModel;
        return rs;
    }

}
