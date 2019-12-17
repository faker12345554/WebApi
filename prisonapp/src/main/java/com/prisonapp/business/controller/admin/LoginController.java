package com.prisonapp.business.controller.admin;

import com.common.common.result.ResultSet;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/admin/user")
public class LoginController {
    public ResultSet rs=new ResultSet();

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResultSet Login(){
        rs.resultCode=1;
        rs.resultMsg="123";
        rs.data=null;
        return rs;
    }

}
