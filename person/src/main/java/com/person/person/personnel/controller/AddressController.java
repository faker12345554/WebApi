package com.person.person.personnel.controller;

import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.person.person.personnel.entity.AddressInformation;
import com.person.person.personnel.service.AddressService;
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
    private ResponseResult result = new ResponseResult();

    @PostMapping("/addLocation")//
    public ResponseResult insertLocation(@RequestBody(required = false) AddressInformation addressInformation, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(addressService.insertLocation(addressInformation));
    }
}
