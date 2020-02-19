package com.admin.admin.controller.dw_enum;

import com.admin.admin.entity.dw_enum.TEnum;
import com.admin.admin.service.dw_enum.EnumService;
import com.admin.token.tation.PassToken;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value="基础数据controller",tags={"基础数据"})
@RequestMapping("/Enum")
public class EnumController {
    @Autowired
    private EnumService enumService;
    private ResponseResult result = new ResponseResult();

    /**
     *
     * @param Code
     * @return
     */
    @UserLoginToken
    @GetMapping("/GetEnum")
    public ResponseResult GetEnum(@RequestParam String Code){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.GetEnum(Code));
    }

    @UserLoginToken
    @GetMapping("/GetPolice")
    public ResponseResult GetPolice(@RequestParam String PoliceStation){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.GetPolice(PoliceStation));
    }

    /**
     * 获取机构
     * @return
     */
    @UserLoginToken
    @GetMapping("/ListMechanism")
    public ResponseResult ListMechanism(){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.ListMechanism());
    }

    //@ApiOperation("获取需要的枚举信息")
    @UserLoginToken
    @GetMapping("/getEnum")
    public ResponseResult getEnum() {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.getEnum());
    }

    /**
     * 获取主办人
     * @param Code
     * @return
     */
    @UserLoginToken
    @GetMapping("/ListSponsor")
    public ResponseResult ListSponsor(@RequestParam String Code){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.ListSponsor(Code));
    }


    @ApiOperation(value = "新增数据字典")
    @UserLoginToken
    @PostMapping("/addEnum")
    public ResponseResult addEnum(@ApiParam(name = "typeName",value = "字典名称")@RequestParam(required = true)String typeName,
                                  @ApiParam(name = "typeCode",value = "字典类型")@RequestParam(required = true)String typeCode,
                                  @ApiParam(name = "status",value = "状态")@RequestParam(required = true)boolean status,
                                  @ApiParam(name = "enumName",value = "备注")@RequestParam(required = true)String enumName){
        List<TEnum> listEnum=enumService.listEnum(typeCode);
        int enumCode=1;
        if(listEnum.size()!=0){
            int enumNumber=Integer.parseInt( listEnum.get(listEnum.size()-1).getEnumcode());
            enumCode=enumNumber+1;
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.addEnum(typeName,typeCode,status,String.valueOf(enumCode),enumName));
    }

    @ApiOperation(value = "修改数据字典")
    @PassToken
    @PostMapping("/updateEnum")
    public ResponseResult updateEnum(@ApiParam(name = "enumId",value = "数据字典id")@RequestParam(required = true)int enumId,
                                     @ApiParam(name = "typeName",value = "字典名称")@RequestParam(required = true)String typeName,
                                     @ApiParam(name = "typeCode",value = "字典类型")@RequestParam(required = true)String typeCode,
                                     @ApiParam(name = "status",value = "状态")@RequestParam(required = true)boolean status,
                                     @ApiParam(name = "enumName",value = "备注")@RequestParam(required = true)String enumName){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.updateEnum(enumId,typeName,typeCode,status,enumName));
    }
}
