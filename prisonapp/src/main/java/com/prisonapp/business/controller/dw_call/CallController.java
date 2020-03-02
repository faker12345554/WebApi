package com.prisonapp.business.controller.dw_call;


import com.common.common.result.ResultSet;
import com.prisonapp.business.service.dw_call.CallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="通话controller",tags={"音视频通话"})
@RestController
@RequestMapping("/app/call")
public class CallController {

    @Autowired
    private CallService callService ;

    @ApiOperation(value = " 发出通话请求")
    @PostMapping("/requestCall")
    public ResultSet requestCall(String type){
        ResultSet result = new ResultSet();

        result.resultCode = 0;
        result.resultMsg = "";
        result.data = "";
        return result;
    }

}
