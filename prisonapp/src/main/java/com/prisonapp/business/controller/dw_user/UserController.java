package com.prisonapp.business.controller.dw_user;

import com.common.common.result.ResultSet;
import com.prisonapp.business.controller.dw_supervise.SuperviseController;
import com.prisonapp.business.entity.dw_supervise.TPersoninformation;
import com.prisonapp.business.entity.dw_user.TokenModel;
import com.prisonapp.business.entity.dw_user.UserModel;
import com.prisonapp.business.entity.dw_user.GetUserInfoModel;
import com.prisonapp.business.service.dw_user.UserService;
import com.prisonapp.token.TokenService;
import com.prisonapp.token.TokenUtil;
import com.prisonapp.token.tation.PassToken;
import com.prisonapp.token.tation.UserLoginToken;
import com.prisonapp.tool.CacheUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;


@Api(value="登录controller",tags={"用户登录及应用"})
@PassToken
@RestController
@RequestMapping("/app/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;
    private SuperviseController superviseController =new SuperviseController();

    private TokenModel tokenModel=new TokenModel();


    @ApiOperation(value = "登录")
    @PostMapping("/login")//
    public ResultSet login(@ApiParam(name = "account",value = "账号")@RequestParam(required = false) String account,@ApiParam(name = "password",value = "密码") @RequestParam(required = false) String password, HttpServletResponse response){
       UserModel userModel=userService.login(account);
        ResultSet result = new ResultSet();

       if(userModel==null){
           result.resultCode=10;
           result.resultMsg="账号错误";
           result.data=null;
           return result;
       }
       else if(userModel.getPassword().equals(password)&&userModel.getStatus().equals("t")){
           CacheUtils.put("UserId",userModel.getId(),0);

           TPersoninformation tPersoninformation =userService.RelatedId(account);//获取personid
           //将登录操作写入日志
           int insertLog = userService.insertLoginRecord(tPersoninformation.getPersonid());
           String token =tokenService.getToken(userModel);
           Calendar calendar = Calendar.getInstance();
           calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 7);
           String  ExpiresTime =String.valueOf(calendar.getTimeInMillis());
           tokenModel.setToken(token);
           tokenModel.setrExpiresTime(ExpiresTime);
           tokenModel.setRefreshToken(token);
           tokenModel.settExpiresTime(ExpiresTime);
           result.resultCode=0;
           result.resultMsg="";
           result.data=tokenModel;
           return result;
       }else{
           result.resultCode=11;
           result.resultMsg="密码错误";
           result.data=null;
           return result;
       }

    }

    @UserLoginToken
    @ApiOperation(value = "获取保外人员信息")
    @GetMapping("/getUserInfo")
    public ResultSet getUserInfo(){
        ResultSet result = new ResultSet();
          GetUserInfoModel getUserInfoModels = userService.getUserInfo(getPersonId());
          UserModel userModel =userService.officephone(getUserInfoModels.getSponsoralarm());
          getUserInfoModels.setInChargeContract(userModel.getOfficephone());
        result.resultCode=0;
        result.resultMsg="";
        result.data=getUserInfoModels;
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = "保外人员修改密码")
    @PostMapping("/modifyPassword")
    public ResultSet modifyPassword(@ApiParam(name = "password",value = "旧密码")@RequestParam(required = false)String password,@ApiParam(name = "newPassword",value = "新密码")@RequestParam(required = false)String newPassword){
        ResultSet result = new ResultSet();
        List<UserModel> userModel =userService.modifyPassword(TokenUtil.getTokenUserId(),password);//手机号，密码
        if(userModel.size()!=0){
          int a = userService.upModifyPassword(TokenUtil.getTokenUserId(),newPassword);
          if(a!=0){
              result.resultCode=0;
              result.resultMsg="";
              result.data=new Object();
          }else{
              result.resultCode=1;
              result.resultMsg="修改异常";
              result.data=null;
          }
        }else {
            result.resultCode=11;
            result.resultMsg="密码错误";
            result.data=null;
        }

        return result;
    }

    public  String getPersonId(){

        TPersoninformation tPersoninformation = userService.RelatedId(TokenUtil.getTokenUserId());//根据user中的手机号去取出personid
        String personid = tPersoninformation.getPersonid();
        return personid;
    }
}
