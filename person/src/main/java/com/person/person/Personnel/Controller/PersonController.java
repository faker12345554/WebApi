package com.person.person.Personnel.Controller;

import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.person.person.Personnel.Entity.Guaranteeinformation;
import com.person.person.Personnel.Entity.Personinformation;
import com.person.person.Personnel.Service.PersoinfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "人员信息")
@RequestMapping("/Person")
public class PersonController {

    @Autowired
    private PersoinfoService persoinfoService;

    private ResponseResult result=new ResponseResult();

    //新增
    @PostMapping("/AddPersion")
    public ResponseResult AddPersion(@RequestBody(required = false) Personinformation personinformation,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.AddPersion(personinformation));
    }
    //修改
    @PostMapping("/UpdatePersion")
    public ResponseResult UpdatePersion(@RequestBody(required = false) Personinformation personinformation, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.UpdatePersion(personinformation));
    }
    //删除
    @GetMapping("/DelPersion")
    public ResponseResult DelPersion(@RequestParam(required = false) int id,@RequestParam(required = false) boolean flag,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.DelPersion(id, flag));
    }

    //获取
    @GetMapping("/GetPersoin")
    public ResponseResult GetPersoin(@RequestParam(required = false) int id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(persoinfoService.GetPersoin(id));
    }
}
