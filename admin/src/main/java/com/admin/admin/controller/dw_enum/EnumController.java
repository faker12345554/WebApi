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
    public ResponseResult GetEnum(@RequestParam(required = false) String Code){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.GetEnum(Code));
    }

}
