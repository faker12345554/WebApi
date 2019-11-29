package com.person.person.Personnel.Controller;

import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.person.person.Personnel.Entity.Personinformation;
import com.person.person.Personnel.Service.PersoinfoService;
import com.person.person.model.ParamterModel;
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
    @PostMapping("/insertPersion") //另外不要这种写法，你很难调试的
    public ResponseResult insertPersion(@RequestBody Personinformation personinformation){
        // 所以说我不太清楚是因为什么原因你这变成小写的参数才可以接收，你的电脑好卡啊，打字都打不了。。。。。
        System.out.println(personinformation.getAge());
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.insertPersion(personinformation));
    }
    //修改
    @PostMapping("/updatePersion")
    public ResponseResult updatePersion(@RequestBody(required = false) Personinformation personinformation, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.updatePersion(personinformation));
    }
    //删除
    @PostMapping("/deletePersion")
    public ResponseResult deletePersion(@RequestBody(required = false) ParamterModel paramterModel,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.deletePersion(paramterModel));
    }

    //获取
    @GetMapping("/getPersoin")
    public ResponseResult getPersoin(@RequestParam(required = false) int id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData(persoinfoService.getPersoin(id));
    }
}
