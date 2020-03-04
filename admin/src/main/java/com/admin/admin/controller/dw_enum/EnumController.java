package com.admin.admin.controller.dw_enum;

import com.admin.admin.entity.dw_enum.TEnum;
import com.admin.admin.service.dw_enum.EnumService;
import com.admin.model.enummodel.EnumModel;
import com.admin.model.enummodel.EnumSearchModel;
import com.admin.page.PageBean;
import com.admin.token.tation.PassToken;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@Api(value="基础数据controller",tags={"基础数据"})
@RequestMapping("/Enum")
public class EnumController {
    @Autowired
    private EnumService enumService;
    private ResponseResult result = new ResponseResult();

    /**
     * @param Code
     * @return
     */
    @UserLoginToken
    @GetMapping("/GetEnum")
    public ResponseResult GetEnum(@RequestParam String Code) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.GetEnum(Code));
    }

    @UserLoginToken
    @GetMapping("/GetPolice")
    public ResponseResult GetPolice(@RequestParam String PoliceStation) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.GetPolice(PoliceStation));
    }

    /**
     * 获取机构
     *
     * @return
     */
    @UserLoginToken
    @GetMapping("/ListMechanism")
    public ResponseResult ListMechanism() {
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
     *
     * @param Code
     * @return
     */
    @UserLoginToken
    @GetMapping("/ListSponsor")
    public ResponseResult ListSponsor(@RequestParam String Code) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.ListSponsor(Code));
    }


    @ApiOperation(value = "新增数据字典")
    @UserLoginToken
    @PostMapping("/addEnum")
    public ResponseResult addEnum(@ApiParam(name = "typeName", value = "字典名称") @RequestParam(required = true) String typeName,
                                  @ApiParam(name = "typeCode", value = "字典类型") @RequestParam(required = true) String typeCode,
                                  @ApiParam(name = "status", value = "状态") @RequestParam(required = true) boolean status,
                                  @ApiParam(name = "enumName", value = "备注") @RequestParam(required = true) String enumName) {
        List<TEnum> listEnum = enumService.listEnum(typeCode);
        int enumCode = 1;
        if (listEnum.size() != 0) {
            int enumNumber = Integer.parseInt(listEnum.get(listEnum.size() - 1).getEnumcode());
            enumCode = enumNumber + 1;
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.addEnum(typeName, typeCode, status, String.valueOf(enumCode), enumName));
    }

    @ApiOperation(value = "修改数据字典")
    @UserLoginToken
    @PostMapping("/updateEnum")
    public ResponseResult updateEnum(@ApiParam(name = "enumId", value = "数据字典id") @RequestParam(required = true) int enumId,
                                     @ApiParam(name = "typeName", value = "字典名称") @RequestParam(required = true) String typeName,
                                     @ApiParam(name = "typeCode", value = "字典类型") @RequestParam(required = true) String typeCode,
                                     @ApiParam(name = "status", value = "状态") @RequestParam(required = true) boolean status,
                                     @ApiParam(name = "enumName", value = "备注") @RequestParam(required = true) String enumName) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enumService.updateEnum(enumId, typeName, typeCode, status, enumName));
    }

    @ApiOperation(value = "删除数据字典")
    @PassToken
    @GetMapping("/deleteEnum")
    public ResponseResult deleteEnum(@ApiParam(name = "enumId", value = "字典编号") @RequestParam(required = true) String enumId) {
        String[] a=enumId.split(",");
        for (String id:a
             ) {
            int deleteLog=enumService.deleteEnum(Integer.parseInt(id));
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData("");
    }

//    @ApiOperation(value = "列出所有数据字典")
//    @PassToken
//    @GetMapping("/listEnum")
//    public ResponseResult listEnum() {
//
//        result.setCode(ResultCode.SUCCESS.getCode());
//        result.setMessage(ResultCode.SUCCESS.getMessage());
//        return result.setData(enumService.listAllEnum());
//    }

    @ApiOperation(value = "查询数据字典")
    @PassToken
    @PostMapping("findEnum")
    public ResponseResult findEnum(@RequestBody(required = true) EnumSearchModel enumSearchModel) {
        PageHelper.startPage(enumSearchModel.getPageIndex(), enumSearchModel.getPageSize());
        List<EnumModel> allItems = enumService.findEnum(enumSearchModel);
        try {
            if (allItems.size() == 0) {
                result.setCode(ResultCode.NULLDATA.getCode());
                result.setMessage(ResultCode.NULLDATA.getMessage());
                return result.setData("");
            }
            PageInfo<EnumModel> info = new PageInfo<>(allItems);//全部商品
            Collections.sort(allItems, new Comparator<EnumModel>() {
                @Override
                public int compare(EnumModel o1, EnumModel o2) {
                    int a =o1.getEnumid();
                    int b= o2.getEnumid();
                    int c = a-b;
                    return c;
                }
            });
            int countNums = (int) info.getTotal();            //总记录数
            PageBean<EnumModel> pageData = new PageBean<>(enumSearchModel.getPageIndex(), enumSearchModel.getPageSize(), countNums);
            pageData.setTotalPage(info.getPages());//总页数
            pageData.setItems(allItems);
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage(ResultCode.SUCCESS.getMessage());
            return result.setData(pageData);
        } catch (Exception ex) {
            result.setCode(ResultCode.UNKNOW_ERROR.getCode());
            result.setMessage(ResultCode.UNKNOW_ERROR.getMessage());
            return result.setData(ex.toString());
        }

    }

    @ApiOperation(value = "获取单条记录")
    @PassToken
    @GetMapping("/getOneEnum")
    public ResponseResult getOneEnum(@ApiParam(name = "enumId", value = "字典id") @RequestParam(required = true) int enumId) {
        EnumModel enumModel = enumService.getOneEnum(enumId);
        try {
            if (enumModel == null) {
                result.setCode(ResultCode.NULLDATA.getCode());
                result.setMessage(ResultCode.NULLDATA.getMessage());
                return result.setData("");
            } else {
                result.setCode(ResultCode.SUCCESS.getCode());
                result.setMessage(ResultCode.SUCCESS.getMessage());
                return result.setData(enumModel);
            }
        } catch (Exception ex) {
            result.setCode(ResultCode.UNKNOW_ERROR.getCode());
            result.setMessage(ResultCode.UNKNOW_ERROR.getMessage());
            return result.setData(ex.toString());
        }
    }
}
