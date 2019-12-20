package com.admin.admin.controller.dw_guaran;

import com.admin.admin.entity.dw_guarant.GuaranteeInformation;
import com.admin.admin.service.dw_guaran.GuaranService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Guarant")
public class GuarantController {
    @Autowired
    private GuaranService guaranService;

    private ResponseResult result = new ResponseResult();

    @ApiOperation(value = "新增担保信息")
    @PostMapping("/addGuarant")
    public ResponseResult insertGuarant(@RequestBody(required = false) GuaranteeInformation guaranteeinformation, HttpServletResponse response) {

        if(guaranService.getGuaByPersonId(guaranteeinformation.getPersonid())>=1){
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("该监居人员以被人担保!");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guaranService.insertGuarant(guaranteeinformation));

    }

    @ApiOperation(value = "修改担保信息")
    @PostMapping("/updateGuara")
    public ResponseResult updateGuara(@RequestBody(required = false) GuaranteeInformation guaranteeinformation, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guaranService.updateGuara(guaranteeinformation));
    }

    @ApiOperation(value = "删除担保信息")
    @GetMapping("/deleteGuara")
    public ResponseResult deleteGuara(@RequestParam(required = false) boolean flag,
                                      @RequestParam int GuaId, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());

        return result.setData( guaranService.deleteGuara(flag, GuaId));
    }

    @ApiOperation(value = "获取担保信息")
    @GetMapping("/getGuara")
    public ResponseResult getGuara(@RequestParam(required = false) int id, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( guaranService.getGuara(id));
    }

}
