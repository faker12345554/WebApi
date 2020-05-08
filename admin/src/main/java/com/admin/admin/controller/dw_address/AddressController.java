package com.admin.admin.controller.dw_address;

import com.admin.admin.service.dw_address.AddressService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getaddress")
    @ApiOperation("查询公安局的数据")
    public ResponseResult getAddress(@ApiParam(name = "code",value = "派出所编码")String code,
                                     @ApiParam(name = "level",value = "派出所编码级别")int level){
        ResponseResult result = new ResponseResult();
        if (level==1) {

        }else if(level==2) {
            code=code.substring(0,4);
        }else if(level==3) {
            code=code.substring(0,6);
        }else if(level==4) {
            code=code.substring(0,8);
        }

        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(addressService.getAddress(code, level));
    }

}
