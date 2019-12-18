package com.admin.admin.controller.dw_enclosure;

import com.admin.admin.entity.dw_enclosure.enclosure;
import com.admin.admin.service.dw_enclosure.enclosureService;
import com.common.common.result.ResponseResult;
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


    @ApiParam("新增人员行动范围信息")
    @PostMapping("/saveEnclosure")
    public ResponseResult saveEnclosure(@RequestBody(required = false) enclosure enclosure, HttpServletResponse response) {

        return enclosureService.saveEnclosure(enclosure);

    }

    @ApiOperation("修改人员行动范围信息")
    @PostMapping("/updateEnclosure")
    public ResponseResult updateEnclosure(@RequestBody(required = false) enclosure enclosure, HttpServletResponse response) {
        return enclosureService.updateEnclosure(enclosure);
    }

    @ApiOperation("删除人员行动范围信息")
    @GetMapping("/deleteEnclosure")
    public ResponseResult deleteEnclosure(@RequestParam(required = false) String personId, HttpServletResponse response) {

        return enclosureService.deleteEnclosure(personId);
    }

    @ApiOperation("查看人员行动范围信息")
    @GetMapping("/selectEnclosure")
    public ResponseResult selectEnclosure(@RequestParam(required = false) String personId, HttpServletResponse response) {

        return enclosureService.selectEnclosure(personId);
    }
}
