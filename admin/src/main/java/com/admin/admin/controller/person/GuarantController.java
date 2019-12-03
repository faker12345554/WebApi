package com.admin.admin.controller.person;

import com.admin.admin.entity.person.GuaranteeInformation;
import com.admin.admin.service.person.GuaranService;
import com.admin.model.ParamterModel;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Guarant")
public class GuarantController {
    @Autowired
    private GuaranService guaranService;

    private ResponseResult result=new ResponseResult();

    @PostMapping("/addGuarant")
    public ResponseResult insertGuarant(@RequestBody(required = false) GuaranteeInformation guaranteeinformation, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guaranService.insertGuarant(guaranteeinformation));
    }

    @PostMapping("/updateGuara")
    public ResponseResult updateGuara(@RequestBody(required = false) GuaranteeInformation guaranteeinformation, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guaranService.updateGuara(guaranteeinformation));
    }

    @PostMapping("/deleteGuara")
    public ResponseResult deleteGuara(@RequestBody(required = false) ParamterModel paramterModel, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guaranService.deleteGuara(paramterModel));
    }

    @GetMapping("/getGuara")
    public ResponseResult getGuara(@RequestParam(required = false) int id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guaranService.getGuara(id));
    }

}
