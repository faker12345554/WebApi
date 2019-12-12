package com.admin.admin.controller.person;

import com.admin.admin.entity.person.BiosignatureInformation;
import com.admin.admin.service.person.BiosignatureService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Biosignature")
public class BiosignatureController {
    @Autowired
    private BiosignatureService biosignatureService;

    private ResponseResult result=new ResponseResult();

    @ApiOperation(value = "新增生物特征信息")
    @PostMapping("/addBiosignature")
    public ResponseResult insertBiosignature(@RequestBody(required = false)BiosignatureInformation biosignatureInformation){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(biosignatureService.insertBiosignature(biosignatureInformation));
    }
}
