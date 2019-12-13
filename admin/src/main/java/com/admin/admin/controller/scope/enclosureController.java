package com.admin.admin.controller.scope;

import com.admin.admin.entity.scope.enclosure;
import com.admin.admin.service.scope.enclosureService;
import com.admin.model.coordina.Longitude;
import com.admin.model.coordina.RangeMap;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Api("行动范围信息")
@RestController
@RequestMapping("/enclosure")
public class enclosureController {
    @Autowired
    private enclosureService enclosureService;

    private ResponseResult result = new ResponseResult();

    @ApiParam("新增人员行动范围信息")
    @PostMapping("/saveEnclosure")
    public ResponseResult saveEnclosure(@RequestBody(required = false) RangeMap map, HttpServletResponse response) {
        if (enclosureService.selectEnclosureByPersonId(map.getPersonid())>0){
            result.setCode(400);
            result.setMessage("该成员已存在位置信息");
            return result.setData("1");
        }
        for (Longitude iten:map.getPosition()){
            enclosure enclosure=new enclosure();
            enclosure.setPersonId(map.getPersonid());
            enclosure.setAreaName(map.getAreaname());
            enclosure.setType(map.getType());
            enclosure.setStatus(map.isStatus());
            enclosure.setLongitude(iten.getLongitude());
            enclosure.setLatitude(iten.getLatitude());
            result.setData(enclosureService.saveEnclosure(enclosure));
            System.out.println(result.getData());
        }


        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;

    }
    @ApiParam("修改人员行动范围信息")
    @PostMapping("/updateEnclosure")
    public ResponseResult updateEnclosure(@RequestBody(required = false) RangeMap map, HttpServletResponse response){
        for (Longitude iten:map.getPosition()){
            enclosure enclosure=new enclosure();
            enclosure.setPersonId(map.getPersonid());
            enclosure.setAreaName(map.getAreaname());
            enclosure.setType(map.getType());
            enclosure.setStatus(map.isStatus());
            enclosure.setLongitude(iten.getLongitude());
            enclosure.setLatitude(iten.getLatitude());
            result.setData(enclosureService.saveEnclosure(enclosure));
            System.out.println(result.getData());
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    @ApiParam("删除人员行动范围信息")
    @GetMapping("/deleteEnclosure")
    public ResponseResult deleteEnclosure(@RequestParam(required = false) String personId,HttpServletResponse response){
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(enclosureService.deleteEnclosure(personId));
    }

    @ApiParam("查看人员行动范围信息")
    @GetMapping("/selectEnclosure")
    public ResponseResult selectEnclosure(@RequestParam(required = false) String personId,HttpServletResponse response){
        List<enclosure> listEncLouSure=enclosureService.selectEnclosure(personId);
        RangeMap rangeMap=new RangeMap();
        List<Longitude> listLongitudes=new ArrayList<Longitude>();

        for (enclosure item:listEncLouSure){
            rangeMap.setPersonid(item.getPersonId());
            rangeMap.setType(item.getType());
            rangeMap.setAreaname(item.getAreaName());
            rangeMap.setStatus(item.isStatus());
            Longitude longitude=new Longitude();
            longitude.setLatitude(item.getLatitude());
            longitude.setLongitude(item.getLongitude());
            listLongitudes.add(longitude);
        }
        rangeMap.setPosition(listLongitudes);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(rangeMap);
    }
}
