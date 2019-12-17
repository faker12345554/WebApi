package com.prisonapp.business.controller.admin;

import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.admin.TokenModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/admin/user")
public class LoginController {
    public ResultSet rs=new ResultSet();

    public TokenModel tokenModel=new TokenModel();

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResultSet Login(){
        String token="1144556677889999";
        tokenModel.setToken(token);
        tokenModel.setrExpiresTime(token);
        tokenModel.setRefreshToken(token);
        tokenModel.settExpiresTime(token);
        rs.resultCode=1;
        rs.resultMsg="123";
        rs.data=tokenModel;
        return rs;
    }

}
