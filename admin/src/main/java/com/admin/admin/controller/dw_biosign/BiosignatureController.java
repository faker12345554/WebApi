package com.admin.admin.controller.dw_biosign;

import com.admin.admin.entity.dw_bios.BiosignatureInformation;
import com.admin.admin.service.dw_biosign.BiosignatureService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Biosignature")
public class BiosignatureController {
    @Autowired
    private BiosignatureService biosignatureService;

    private ResponseResult result = new ResponseResult();

    @ApiOperation(value = "新增生物特征信息")
    @PostMapping("/addBiosignature")
    public ResponseResult insertBiosignature(@RequestBody(required = false) BiosignatureInformation biosignatureInformation) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( biosignatureService.insertBiosignature(biosignatureInformation));
    }

    @ApiOperation(value = "查询生物特征信息")
    @GetMapping("/getBiosignature")
    public ResponseResult getBiosignature(@RequestParam(required = true) String persion_id, @RequestParam(required = true) int type) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(biosignatureService.getBiosignature(persion_id, type));
    }
}
