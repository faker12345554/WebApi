package com.admin.admin.controller.dw_user;

import com.admin.admin.entity.dw_user.Usermodel;
import com.admin.page.PageUtil;
import com.admin.token.TokenService;
import com.admin.token.tation.UserLoginToken;
import com.admin.admin.entity.dw_user.User;
import com.admin.admin.service.dw_user.UserService;
import com.admin.tool.CacheUtils;
import com.common.common.md5.MD5Util;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

@RestController
@Api(value="用户信息管理Controller",tags={"用户信息管理"})
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    private ResponseResult result = new ResponseResult();


    @UserLoginToken
    @ApiOperation("用户信息列表")
   // @PostMapping("/GetList")
    @PostMapping("/GetList")
    public ResponseResult<User> listUser(@RequestBody Usermodel usermodel) {
        try {
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage(ResultCode.SUCCESS.getMessage());
            //return result.setData(userService.listUser(usermodel.getUserName(),usermodel.getPhone(),usermodel.getStatus(),usermodel.getPageIndex(),usermodel.getPageSize()));
            return result.setData(userService.listUser(usermodel));
        }catch (Exception ex){
            result.setCode(500);
            result.setMessage(ex.toString());
            return result.setData(" ");
        }
    }


    //获取用户
    @ApiOperation("查看用户信息")
    @UserLoginToken
    @GetMapping("/GetUser")
    public ResponseResult getUser(@RequestParam(required = false) String UserName, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( userService.getUser(UserName));
    }

    //新增用户
    @UserLoginToken
    @ApiOperation("新增用户")
    @PostMapping("/AddUser")
    public ResponseResult saveUser(@RequestBody User user, HttpServletResponse response) {
        if (userService.GetUserByAccountName(user.getAccountname()) > 1) {
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("用户名已存在!");
        }
        user.setPassword(MD5Util.string2MD5(user.getPassword()));
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( userService.saveUser(user));
    }

    //修改

    @UserLoginToken
    @ApiOperation("修改用户信息")
    @PostMapping("/UpdateUser")
    public ResponseResult updateUser(@RequestBody User user, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userService.updateUser(user));
    }

    //删除
    @UserLoginToken
    @ApiOperation("删除用户")
    @GetMapping("/DelUser")
    public ResponseResult deleteUser(@RequestParam boolean flag, @RequestParam String  UserName, HttpServletResponse response) {
        if (flag != true) {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("参数'flag'输入错误");
        } else if (userService.getUser(UserName) == null) {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("参数'UserId'输入错误,该用户不存在");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( userService.deleteUser(!flag, UserName));
    }

    //登录
    @ApiOperation("登录")
    @GetMapping("/Login")
    public ResponseResult login(@RequestParam String UserName, @RequestParam String Password,
                                HttpServletResponse response, HttpServletRequest request) {
//        String VerCode = String.valueOf(CacheUtils.get("验证码"));
//        if (!VerCode.equals(Code)) {
//            result.setCode(ResultCode.ILLEAGAL_STRING.getCode());
//            result.setMessage(ResultCode.ILLEAGAL_STRING.getMessage());
//            return result.setData("验证码不正确!");
//        }
        String MD5Password =MD5Util.string2MD5(Password);
        User user = userService.login(UserName, MD5Password);
        if (user==null){
            result.setCode(2);
            result.setMessage("密码或账号错误");
            return result;
        }else if(user.getUsersystem()!=1){
            result.setCode(3);
            result.setMessage("账号不存在");
            return result;
        }
        CacheUtils.put("UserId", user.getId(), 0);
        CacheUtils.put("UserName",user.getAliasname());
        String token = tokenService.getToken(user);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( token);
    }

    @ApiOperation("获取验证码")
    @GetMapping("/random")
    public void findRandom(HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 验证码字符个数
        int codeCount = 4;
        char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        // 创建一个随机数生成器类
        Random random = new Random();
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        PrintWriter out = response.getWriter();
        CacheUtils.put("验证码", randomCode, 60000);
        out.print(randomCode);
    }

}
