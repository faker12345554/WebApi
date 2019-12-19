package com.admin.admin.controller.dw_person;

import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.service.dw_person.PersoinfoService;
import com.common.common.result.ResponseResult;
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



    //新增
    @ApiOperation("新增人员信息")
    @PostMapping("/insertPersion")
    public ResponseResult insertPersion(@RequestBody Personinformation personinformation){

        return persoinfoService.insertPersion(personinformation);
    }
    //修改
    @ApiOperation("修改人员信息")
    @PostMapping("/updatePersion")
    public ResponseResult updatePersion(@RequestBody(required = false) Personinformation personinformation, HttpServletResponse response){
        return persoinfoService.updatePersion(personinformation);
    }

    //删除
    @ApiOperation("删除人员信息")
    @GetMapping("/deletePersion")
    public ResponseResult deletePersion(@RequestParam(required = false) boolean flag, @RequestParam String PersonId, HttpServletResponse response){

        return persoinfoService.deletePersion(flag,PersonId);
    }

    //获取
    @ApiOperation("获取人员信息")
    @GetMapping("/getPersoin")
    public ResponseResult getPersoin(@RequestParam(required = false) String id,HttpServletResponse response){

        return  persoinfoService.getPersoin(id);
    }
}
