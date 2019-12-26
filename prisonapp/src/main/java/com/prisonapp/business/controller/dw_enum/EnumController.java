package com.prisonapp.business.controller.dw_enum;

import com.common.common.result.ResultSet;
import com.prisonapp.token.getuserid.GetUserId;
import io.swagger.annotations.Api;

@Api(value="枚举类controller",tags={"获取枚举类"})

public class EnumController {

    private GetUserId getUserId = new GetUserId();
    private ResultSet result = new ResultSet();

//    @ApiOperation(value = " 获取省份数据")
//    @PostMapping("/getProvice")
//    public ResultSet getProvice(){
//
//    }
}
