package com.admin.admin.controller.dw_guaran;

import com.admin.admin.entity.dw_guarant.GuaranteeInformation;
import com.admin.admin.service.dw_guaran.GuaranService;
import com.common.common.result.ResponseResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/Guarant")
public class GuarantController {
    @Autowired
    private GuaranService guaranService;


    @ApiOperation(value = "新增担保信息")
    @PostMapping("/addGuarant")
    public ResponseResult insertGuarant(@RequestBody(required = false) GuaranteeInformation guaranteeinformation, HttpServletResponse response) {

        return guaranService.insertGuarant(guaranteeinformation);
    }

    @ApiOperation(value = "修改担保信息")
    @PostMapping("/updateGuara")
    public ResponseResult updateGuara(@RequestBody(required = false) GuaranteeInformation guaranteeinformation, HttpServletResponse response) {

        return guaranService.updateGuara(guaranteeinformation);
    }

    @ApiOperation(value = "删除担保信息")
    @GetMapping("/deleteGuara")
    public ResponseResult deleteGuara(@RequestParam(required = false) boolean flag,
                                      @RequestParam int GuaId, HttpServletResponse response) {


        return guaranService.deleteGuara(flag, GuaId);
    }

    @ApiOperation(value = "获取担保信息")
    @GetMapping("/getGuara")
    public ResponseResult getGuara(@RequestParam(required = false) int id, HttpServletResponse response) {

        return guaranService.getGuara(id);
    }

}
