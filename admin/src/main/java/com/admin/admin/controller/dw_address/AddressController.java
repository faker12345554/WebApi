package com.admin.admin.controller.dw_address;

import com.admin.admin.entity.dw_address.AddressInformation;
import com.admin.admin.service.dw_address.AddressService;
import com.common.common.result.ResponseResult;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Address")
public class AddressController {
    @Autowired
    private AddressService addressService;


    @ApiOperation(value = "上报位置")
    @PostMapping("/addLocation")//
    public ResponseResult insertLocation(@RequestBody(required = false) AddressInformation addressInformation, HttpServletResponse response) {

        return addressService.insertLocation(addressInformation);
    }
}
