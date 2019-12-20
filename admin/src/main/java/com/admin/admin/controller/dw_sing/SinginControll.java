package com.admin.admin.controller.dw_sing;

import com.admin.admin.service.dw_sing.SinginService;
import com.common.common.result.ResponseResult;
import io.swagger.annotations.ApiOperation;
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


    @ApiOperation("签到")
    @GetMapping("/getSingin")
    public ResponseResult getSingin(@RequestParam(required = false) int personId, HttpServletResponse response) {

        return singinService.getSingin(personId);
    }
}
