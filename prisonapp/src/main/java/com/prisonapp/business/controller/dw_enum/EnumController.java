package com.prisonapp.business.controller.dw_enum;

import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.dw_enum.AreaAddressInfo;
import com.prisonapp.business.entity.dw_enum.EnumModel;
import com.prisonapp.business.service.dw_enum.EnumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value="枚举类controller",tags={"获取枚举类"})
@RestController
@RequestMapping("/app/enum")
public class EnumController {

    @Autowired
    private EnumService enumService ;


    private ResultSet result = new ResultSet();

    @ApiOperation(value = " 获取省份数据")
    @GetMapping("/getProvice")
    public ResultSet getProvice(){
         List<EnumModel> enumModel = new ArrayList<>();
       List<AreaAddressInfo> areaAddressInfos =enumService.getProvice();
       for(AreaAddressInfo item :areaAddressInfos ){
           EnumModel enumModel1 =new EnumModel();
           enumModel1.setCode(item.getCode());
           enumModel1.setName(item.getName());
           enumModel.add(enumModel1);
       }
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = enumModel;
        return result;
    }

    @ApiOperation(value = " 获取城市数据")
    @GetMapping("/getCity")
    public ResultSet getCity(){
         List<EnumModel> enumModel = new ArrayList<>();
        List<AreaAddressInfo> areaAddressInfos =enumService.getCity();
        System.out.println(areaAddressInfos.size());
        for(AreaAddressInfo item :areaAddressInfos ){
            EnumModel enumModel1 =new EnumModel();
            enumModel1.setCode(item.getCode());
            enumModel1.setName(item.getName());
            enumModel.add(enumModel1);
        }
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = enumModel;
        return result;
    }

    @ApiOperation(value = " 获取区县数据")
    @GetMapping("/getDistrict")
    public ResultSet getDistrict(){
         List<EnumModel> enumModel = new ArrayList<>();
        List<AreaAddressInfo> areaAddressInfos =enumService.getDistrict();
        for(AreaAddressInfo item :areaAddressInfos ){
            EnumModel enumModel1 =new EnumModel();
            enumModel1.setCode(item.getCode());
            enumModel1.setName(item.getName());
            enumModel.add(enumModel1);
        }
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = enumModel;
        return result;
    }
}
