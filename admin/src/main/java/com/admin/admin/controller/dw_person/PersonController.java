package com.admin.admin.controller.dw_person;

import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_prisonsetting.TPrisonsetting;
import com.admin.admin.entity.dw_user.User;
import com.admin.admin.service.dw_person.PersoinfoService;
import com.admin.model.search.SearchModel;
import com.admin.page.PageBean;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Api(value="保外人员Controller",tags={"保外人员信息管理"})
@RestController
@RequestMapping("/Person")
public class PersonController {

    @Autowired
    private PersoinfoService persoinfoService;//

    private ResponseResult result=new ResponseResult();

    //新增
    @ApiOperation("新增人员信息")
    @PostMapping("/insertPersion")
    public ResponseResult insertPersion(@RequestBody Personinformation personinformation){
        if (persoinfoService.getPersonByCard(personinformation.getCard())>1){
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("参数'card'重复,身份证号应是唯一凭证");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( persoinfoService.insertPersion(personinformation));
    }
    //修改
    @ApiOperation("修改人员信息")
    @PostMapping("/updatePersion")
    public ResponseResult updatePersion(@RequestBody Personinformation personinformation, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( persoinfoService.updatePersion(personinformation));
    }

    //删除
    @ApiOperation("删除人员信息")
    @GetMapping("/deletePersion")
    public ResponseResult deletePersion(@RequestParam boolean flag, @RequestParam String PersonId, HttpServletResponse response){
        if (flag==true){
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("参数'flag'错误");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( persoinfoService.deletePersion(flag,PersonId));
    }

    //获取
    @ApiOperation("获取人员信息")
    @GetMapping("/getPersoin")
    public ResponseResult getPersoin(@RequestParam String id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData( persoinfoService.getPersoin(id));
    }

    @ApiOperation("变更主办人")
    @GetMapping("/updateSponsor")
    public ResponseResult updateSponsor(@RequestParam String Name,String id,String PersonId){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("变更成功");
        return  result.setData( persoinfoService.updateSponsor(Name,id,PersonId));
    }

    @ApiOperation("配置管理方式")
    @GetMapping("/insertprison")
    public ResponseResult insertprison(@RequestBody List<TPrisonsetting> List){
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage("配置成功");
            return  result.setData( persoinfoService.insertprison(List));
    }

    @ApiOperation("人员信息列表")
    @GetMapping("/ListPerson")
    public ResponseResult ListPerson(@RequestParam(required = false) String Condition,int PageSize,int PageIndex){
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage(ResultCode.SUCCESS.getMessage());
            return result.setData(persoinfoService.ListPerson(Condition,PageIndex,PageSize));
    }

    @ApiOperation("获取需要的枚举信息")
    @GetMapping("/getEnum")
    public ResponseResult getEnum(){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return  result.setData( persoinfoService.getEnum());
    }
}
