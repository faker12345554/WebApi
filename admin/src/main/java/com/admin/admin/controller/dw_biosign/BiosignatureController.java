package com.admin.admin.controller.dw_biosign;

import com.admin.admin.entity.dw_bios.BiosignatureInformation;
import com.admin.admin.service.dw_biosign.BiosignatureService;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value="生物特征管理controller",tags={"生物特征管理"})
@RestController
@RequestMapping("/Biosignature")
public class BiosignatureController {
    @Autowired
    private BiosignatureService biosignatureService;



    @UserLoginToken
    @ApiOperation(value = "新增生物特征信息")
    @PostMapping("/addBiosignature")
    public ResponseResult insertBiosignature(@RequestBody BiosignatureInformation biosignatureInformation) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( biosignatureService.insertBiosignature(biosignatureInformation));
    }

    @UserLoginToken
    @ApiOperation(value = "查询生物特征信息")
    @GetMapping("/getBiosignature")
    public ResponseResult getBiosignature(@RequestParam(required = true) String persion_id, @RequestParam(required = true) int type) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(biosignatureService.getBiosignature(persion_id, type));
    }
}
