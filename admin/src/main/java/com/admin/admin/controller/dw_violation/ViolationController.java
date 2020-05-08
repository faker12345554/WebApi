package com.admin.admin.controller.dw_violation;

import com.admin.admin.entity.dw_violation.Violationfens;
import com.admin.admin.service.dw_violation.ViolationService;
import com.admin.token.tation.UserLoginToken;
import com.common.common.authenticator.CalendarAdjust;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Api(value="违规分数设置Controller",tags={"违规分数设置"})
@RestController
@RequestMapping("/Violation")
public class ViolationController {

    @Autowired
    private ViolationService violationService;


    @UserLoginToken
    @ApiOperation("新增违规分数")
    @PostMapping("/SaveViolation")
    public ResponseResult SaveViolation(@RequestBody List<Violationfens> violationfens) throws ParseException {
        ResponseResult result = new ResponseResult();

        List<Violationfens> violationlist = violationService.ListViolation();
        List<Violationfens> violation = violationService.enabledViolationList();
        for (Violationfens item : violationfens) {
            if (violationlist.size() == 0) {
                item.setStatus(true);
                item.setEnabled(false);
                item.setCreatetime(new Date());
                violationService.SaveViolation(item);
                result.setMessage("设置成功");
            } else if (violation.size() == 0) {
                item.setStatus(false);
                item.setCreatetime(new Date());
                item.setUpdatemonth(CalendarAdjust.dateFormat.format( new Date()));
                item.setEnabled(true);
                violationService.SaveViolation(item);
                result.setMessage("修改成功,下月起生效");
            } else {
                result.setMessage("本月已修改过,不能再修改");
            }
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        return result;
    }




    @UserLoginToken
    @ApiOperation("作废违规分数")
    @GetMapping("/DeleteViolation")
    public ResponseResult DeleteViolation(@ApiParam(name = "id", value = "违规设置记录id")  int id,
                                          @ApiParam(name = "flag", value = "状态")  boolean flag){
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(violationService.deleteViolation(id,flag));
    }

//    @UserLoginToken
//    @ApiOperation("获取违规分数信息")
//    @GetMapping("/getViolation")
//    public ResponseResult getViolation(@RequestParam int id){
//        result.setCode(ResultCode.SUCCESS.getCode());
//        result.setMessage(ResultCode.SUCCESS.getMessage());
//        return  result.setData(violationService.getViolation(id));
//    }

    @UserLoginToken
    @ApiOperation("违规分数信息列表")
    @GetMapping("/ListViolation")
    public ResponseResult ListViolation(){
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(violationService.ListViolation());

    }
}
