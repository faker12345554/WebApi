package com.admin.admin.controller.person;

import com.admin.admin.entity.person.Personinformation;
import com.admin.admin.service.person.PersoinfoService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "人员信息")
@RequestMapping("/Person")
public class PersonController {

    @Autowired
    private PersoinfoService persoinfoService;//

    private ResponseResult result=new ResponseResult();

    //新增
    @ApiOperation("新增人员信息")
    @PostMapping("/insertPersion")
    public ResponseResult insertPersion(@RequestBody Personinformation personinformation){
        // 所以说我不太清楚是因为什么原因你这变成小写的参数才可以接收，你的电脑好卡啊，打字都打不了。。。。。
        System.out.println(personinformation.getAge());
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.insertPersion(personinformation));
    }
    //修改
    @ApiOperation("修改人员信息")
    @PostMapping("/updatePersion")
    public ResponseResult updatePersion(@RequestBody(required = false) Personinformation personinformation, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.updatePersion(personinformation));
    }

    //删除
    @ApiOperation("删除人员信息")
    @GetMapping("/deletePersion")
    public ResponseResult deletePersion(@RequestParam(required = false) boolean flag, @RequestParam String PersonId, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.deletePersion(flag,PersonId));
    }

    //获取
    @ApiOperation("获取人员信息")
    @GetMapping("/getPersoin")
    public ResponseResult getPersoin(@RequestParam(required = false) String id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(persoinfoService.getPersoin(id));
    }
}
