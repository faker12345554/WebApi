package com.admin.admin.controller.person;

import com.admin.admin.service.person.SinginService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Singin")
public class SinginControll {
    @Autowired
    private SinginService singinService;
    private ResponseResult result = new ResponseResult();

    @GetMapping("/getSingin")
    public ResponseResult getSingin(@RequestParam(required = false) int person_id, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(singinService.getSingin(person_id));
    }
}
