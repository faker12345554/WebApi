package com.admin.admin.controller.scope;

import com.admin.admin.entity.scope.enclosure;
import com.admin.admin.service.scope.enclosureService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api
@RestController
@RequestMapping("/enclosure")
public class enclosureController {
    @Autowired
    private enclosureService enclosureService;

    private ResponseResult result = new ResponseResult();

    @PostMapping("/saveEnclosure")
    public ResponseResult saveEnclosure(@RequestBody(required = false) enclosure enclosure,HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enclosureService.saveEnclosure(enclosure));

    }
    @PostMapping("/updateEnclosure")
    public ResponseResult updateEnclosure(@RequestBody(required = false) enclosure enclosure, HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enclosureService.updateEnclosure(enclosure));
    }

    @GetMapping("/deleteEnclosure")
    public ResponseResult deleteEnclosure(@RequestParam(required = false) int id,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enclosureService.deleteEnclosure(id));
    }
}
