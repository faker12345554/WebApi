package com.person.person.Personnel.Controller;

import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.person.person.Personnel.Entity.AddressInformation;
import com.person.person.Personnel.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    private ResponseResult result = new ResponseResult();

    /* 在这里就不起作用 还是说 那些代码我也得引用一遍  这里没引用调用admin的验证token的嘛？你是说拦截器？ 难道我整个搬过来嘛 我看看你admin模块的接口怎么验证的
    在这个模块就没用 运行xia
    **/
    @UserLoginToken
    @PostMapping("/Addlocation")
    public ResponseResult Addlocation(@RequestBody(required = false) AddressInformation addressInformation, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(addressService.Addlocation(addressInformation));
    }
}
