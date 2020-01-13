package com.admin.admin.controller.dw_address;

import com.admin.admin.entity.dw_address.AddressInformation;
import com.admin.admin.service.dw_address.AddressService;
import com.admin.admin.service.dw_task.Tasking;
import com.common.common.result.ResponseResult;

import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api("")
@RestController
@RequestMapping("/Address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private Tasking tasking;

    private ResponseResult result = new ResponseResult();

    @ApiOperation(value = "上报位置")
    @PostMapping("/addLocation")//
    public ResponseResult insertLocation(@RequestBody AddressInformation addressInformation, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( addressService.insertLocation(addressInformation));
    }

//    @GetMapping("/test")
//    public void GeneratedRecord() throws Exception{
//        tasking.GeneratedRecord();
//    }
}
