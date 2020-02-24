package com.admin.admin.controller.dw_meun;

import com.admin.admin.service.dw_Meun.meunService;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(value="菜单管理",tags={"菜单管理"})
@RestController
@RequestMapping("/Meun")
public class MeunController {
    @Autowired
    private meunService meunService;

    private ResponseResult result = new ResponseResult();

    @UserLoginToken
    @GetMapping("/GetMeun")
    public ResponseResult GetMenuList(@RequestParam int PageSize, int PageIndex, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(meunService.GetMenuList( PageSize, PageIndex));
    }

    @UserLoginToken
    @GetMapping("/delMeun")
    public ResponseResult delMeun(@RequestParam boolean flag,@RequestParam int id){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(meunService.delMeun(!flag,id));
    }
    @UserLoginToken
    @GetMapping("/GetMeunbyid")
    public ResponseResult GetMeunbyid(@RequestParam int id){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(meunService.GetMeunbyid(id));
    }
}
