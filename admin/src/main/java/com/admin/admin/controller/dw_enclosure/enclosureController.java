package com.admin.admin.controller.dw_enclosure;

import com.admin.admin.entity.dw_enclosure.enclosure;
import com.admin.admin.service.dw_enclosure.enclosureService;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api("行动范围信息")
@RestController
@RequestMapping("/enclosure")
public class enclosureController {
    @Autowired
    private enclosureService enclosureService;

    private ResponseResult result = new ResponseResult();
    @ApiParam("新增人员行动范围信息")
    @PostMapping("/saveEnclosure")
    public ResponseResult saveEnclosure(@RequestBody(required = false) enclosure enclosure, HttpServletResponse response) {
        if (enclosureService.selectEnclosureByPersonId(enclosure.getPersonId()) > 0) {
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("已存在位置信息");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( enclosureService.saveEnclosure(enclosure));

    }

    @ApiOperation("修改人员行动范围信息")
    @PostMapping("/updateEnclosure")
    public ResponseResult updateEnclosure(@RequestBody(required = false) enclosure enclosure, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( enclosureService.updateEnclosure(enclosure));
    }

    @ApiOperation("删除人员行动范围信息")
    @GetMapping("/deleteEnclosure")
    public ResponseResult deleteEnclosure(@RequestParam(required = false) String personId, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enclosureService.deleteEnclosure(personId));
    }

    @ApiOperation("查看人员行动范围信息")
    @GetMapping("/selectEnclosure")
    public ResponseResult selectEnclosure(@RequestParam(required = false) String personId, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData( enclosureService.selectEnclosure(personId));
    }
}
