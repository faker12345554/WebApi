package com.person.person.Personnel.Controller;

import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.person.person.Personnel.Entity.Guaranteeinformation;
import com.person.person.Personnel.Service.GuaranService;
import com.person.person.model.ParamterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Guarant")
public class GuarantController {
    @Autowired
    private GuaranService guaranService;

    private ResponseResult result=new ResponseResult();

    @PostMapping("/AddGuarant")
    public ResponseResult AddGuarant(@RequestBody(required = false) Guaranteeinformation guaranteeinformation, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guaranService.AddGuarant(guaranteeinformation));
    }

    @PostMapping("/UpdateGuara")
    public ResponseResult UpdateGuara(@RequestBody(required = false) Guaranteeinformation guaranteeinformation, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guaranService.UpdateGuara(guaranteeinformation));
    }

    @PostMapping("/DelGuara")
    public ResponseResult DelGuara(@RequestBody(required = false) ParamterModel paramterModel, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guaranService.DelGuara(paramterModel));
    }

    @GetMapping("/GetGuara")
    public ResponseResult GetGuara(@RequestParam(required = false) int id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(guaranService.GetGuara(id));
    }

}
