package com.admin.admin.controller.user;

import com.admin.token.TokenService;
import com.admin.token.tation.UserLoginToken;
import com.admin.admin.entity.user.User;
import com.admin.admin.service.user.UserService;
import com.admin.config.CacheUtils;
import com.admin.model.ParamterModel;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@RestController
@Api(value = "用户基本信息操作")
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    private ResponseResult result=new ResponseResult();

    //用户列表
    @GetMapping("/GetList")
    public ResponseResult<User> ListUser(@RequestParam(required = false) boolean falg,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userService.ListUser(falg));
    }


    //获取用户
    @UserLoginToken
    @GetMapping("/GetUser")
    public ResponseResult GetUser(@RequestParam(required = false) int id, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userService.GetUser(id));
    }
    //新增用户
    @PostMapping("/Add")
    public ResponseResult SaveUser(@RequestBody User user, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userService.SaveUser(user));
    }

    //修改
    @PostMapping("/Update")
    public ResponseResult UpdateUser(@RequestBody User user, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userService.UpdateUser(user));
    }
    //删除
    @PostMapping("/DelUser")
    public ResponseResult DeleteUser(@RequestBody(required = false) ParamterModel Paramter, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(userService.DeleteUser(Paramter));
    }

    //登录
    @GetMapping("/Login")
    public ResponseResult Login(@RequestParam(required = false) String UserName,@RequestParam(required = false) String Password,@RequestParam(required = false) String Code,
                                HttpServletResponse response, HttpServletRequest request){
        String VerCode= String.valueOf(CacheUtils.get("验证码"));
        if (!VerCode.equals(Code)){
            result.setCode(ResultCode.ILLEAGAL_STRING.getCode());
            result.setMessage(ResultCode.ILLEAGAL_STRING.getMessage());
            return result.setData("验证码不正确!");
        }

        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        User user=userService.Login(UserName, Password);
        String token = tokenService.getToken(user);
        return result.setData(token);
    }

    @GetMapping("/random")
    public void findRandom (HttpServletResponse response,HttpServletRequest request) throws IOException {
        // 验证码字符个数
        int codeCount = 4;
        char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

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
        CacheUtils.put("验证码",randomCode,60000);
        out.print(randomCode);
    }

}
