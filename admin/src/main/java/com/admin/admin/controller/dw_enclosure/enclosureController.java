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
import java.util.List;

@Api("行动范围信息")
@RestController
@RequestMapping("/enclosure")
public class enclosureController {
    @Autowired
    private enclosureService enclosureService;

    private ResponseResult result = new ResponseResult();

    @ApiParam("新增人员行动范围信息")
    @PostMapping("/saveEnclosure")
    public ResponseResult saveEnclosure(@RequestBody enclosure enclosure, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enclosureService.saveEnclosure(enclosure));

    }

//    @ApiOperation("修改人员行动范围信息")
//    @PostMapping("/updateEnclosure")
//    public ResponseResult updateEnclosure(@RequestBody enclosure enclosure, HttpServletResponse response) {
//        result.setCode(ResultCode.SUCCESS.getCode());
//        result.setMessage(ResultCode.SUCCESS.getMessage());
//        return result.setData( enclosureService.updateEnclosure(enclosure));
//    }

    @ApiOperation("删除人员行动范围信息")
    @GetMapping("/deleteEnclosure")
    public ResponseResult deleteEnclosure(@RequestParam String personId, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enclosureService.deleteEnclosure(personId));
    }

    @ApiOperation("查看人员行动范围信息")
    @GetMapping("/selectEnclosure")
    public ResponseResult selectEnclosure(@RequestParam String personId, HttpServletResponse response) {
        List<enclosure> list = enclosureService.selectEnclosure(personId);
        if (list.size() == 0) {
            result.setCode(ResultCode.NULLDATA.getCode());
            result.setMessage(ResultCode.NULLDATA.getMessage());
            return result.setData(list);
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(list);
    }
}
