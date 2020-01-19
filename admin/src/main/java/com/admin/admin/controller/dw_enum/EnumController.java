package com.admin.admin.controller.dw_enum;

import com.admin.admin.service.dw_enum.EnumService;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="基础数据controller",tags={"基础数据"})
@RequestMapping("/Enum")
public class EnumController {
    @Autowired
    private EnumService enumService;
    private ResponseResult result = new ResponseResult();

    /**
     *
     * @param Code
     * @return
     */
    @UserLoginToken
    @GetMapping("/GetEnum")
    public ResponseResult GetEnum(@RequestParam String Code){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.GetEnum(Code));
    }

    @UserLoginToken
    @GetMapping("/GetPolice")
    public ResponseResult GetPolice(@RequestParam String PoliceStation){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.GetPolice(PoliceStation));
    }

    /**
     * 获取机构
     * @return
     */
    @UserLoginToken
    @GetMapping("/ListMechanism")
    public ResponseResult ListMechanism(){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.ListMechanism());
    }

    //@ApiOperation("获取需要的枚举信息")
    @UserLoginToken
    @GetMapping("/getEnum")
    public ResponseResult getEnum() {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.getEnum());
    }

    /**
     * 获取主办人
     * @param Code
     * @return
     */
    @UserLoginToken
    @GetMapping("/ListSponsor")
    public ResponseResult ListSponsor(@RequestParam String Code){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.ListSponsor(Code));
    }

}
