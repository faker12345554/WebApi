package com.admin.admin.controller.dw_user;

import com.admin.token.tation.UserLoginToken;
import com.admin.admin.entity.dw_user.User;
import com.admin.admin.service.dw_user.UserService;
import com.admin.config.CacheUtils;
import com.common.common.result.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@RestController
//@Api(value = "用户基本信息操作")
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;


    private ResponseResult result = new ResponseResult();


    @ApiOperation("用户信息列表")
    @GetMapping("/GetList")
    public ResponseResult<User> listUser(@RequestParam(required = false) boolean falg, int PageSize, int PageIndex, HttpServletResponse response) {
        return userService.listUser(falg, PageSize, PageIndex);
    }


    //获取用户
    @ApiOperation("查看用户信息")
    @UserLoginToken
    @GetMapping("/GetUser")
    public ResponseResult getUser(@RequestParam(required = false) int id, HttpServletResponse response) {

        return userService.getUser(id);
    }

    //新增用户
    @ApiOperation("新增用户")
    @PostMapping("/AddUser")
    public ResponseResult saveUser(@RequestBody User user, HttpServletResponse response) {
        return userService.saveUser(user);
    }

    //修改

    @ApiOperation("修改用户信息")
    @PostMapping("/UpdateUser")
    public ResponseResult updateUser(@RequestBody User user, HttpServletResponse response) {

        return userService.updateUser(user);
    }

    //删除
    @ApiOperation("删除用户")
    @GetMapping("/DelUser")
    public ResponseResult deleteUser(@RequestParam(required = false) boolean flag, @RequestParam int UserId, HttpServletResponse response) {
        return userService.deleteUser(flag, UserId);
    }

    //登录
    @ApiOperation("登录")
    @GetMapping("/Login")
    public ResponseResult login(@RequestParam(required = false) String UserName, @RequestParam(required = false) String Password, @RequestParam(required = false) String Code,
                                HttpServletResponse response, HttpServletRequest request) {

        return userService.login(UserName, Password,Code);
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
