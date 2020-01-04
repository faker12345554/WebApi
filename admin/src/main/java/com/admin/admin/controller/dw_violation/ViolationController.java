package com.admin.admin.controller.dw_violation;

import com.admin.admin.entity.dw_report.Reportsettings;
import com.admin.admin.entity.dw_violation.Violationfens;
import com.admin.admin.service.dw_violation.ViolationService;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value="违规分数设置Controller",tags={"违规分数设置"})
@RestController
@RequestMapping("/Violation")
public class ViolationController {

    @Autowired
    private ViolationService violationService;

    private ResponseResult result = new ResponseResult();

    @UserLoginToken
    @ApiOperation("新增或者修改违规分数")
    @PostMapping("/SaveOrUpdate")
    public ResponseResult SaveOrUpdateViolation(@RequestBody Violationfens violationfens){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(violationService.SaveOrUpdateViolation(violationfens));
    }

    @UserLoginToken
    @ApiOperation("作废违规分数")
    @GetMapping("/DeleteViolation")
    public ResponseResult DeleteViolation(@RequestParam  int id){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(violationService.deleteViolation(id));
    }

    @UserLoginToken
    @ApiOperation("获取违规分数信息")
    @GetMapping("/getViolation")
    public ResponseResult getViolation(@RequestParam int id){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(violationService.getViolation(id));
    }

    @UserLoginToken
    @ApiOperation("违规分数信息列表")
    @GetMapping("/ListViolation")
    public ResponseResult ListViolation(@RequestParam int PageSize,@RequestParam int PageIndex){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(violationService.ListViolation(PageSize,PageIndex));

    }
}
