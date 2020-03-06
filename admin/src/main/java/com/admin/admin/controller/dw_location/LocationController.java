package com.admin.admin.controller.dw_location;

import com.admin.admin.entity.dw_location.Locationmation;
import com.admin.admin.service.dw_location.LocationService;
import com.admin.model.Execl.ExeclModel;
import com.admin.model.search.SearchModel;
import com.admin.page.PageBean;
import com.admin.token.tation.UserLoginToken;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(value="定位信息controller",tags={"定位信息管理"})
@RestController
@RequestMapping("/Location")
public class LocationController {
    @Autowired
    private LocationService locationService;



    /*
    定位信息列表
     */
    @UserLoginToken
    @ApiOperation("定位信息列表")
    @GetMapping("/LocationList")
    public ResponseResult listLocationModel(@RequestParam(required = false) String Condition, @RequestParam int PageSize, @RequestParam int PageIndex,
                                            HttpServletResponse response) {
        ResponseResult result = new ResponseResult();

        PageBean pageBean = locationService.listLocationModel(Condition, PageSize, PageIndex);
        if (pageBean.getItems().size() == 0) {
            result.setCode(ResultCode.NULLDATA.getCode());
            result.setMessage(ResultCode.NULLDATA.getMessage());
            return result.setData("");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(pageBean);
    }

    @UserLoginToken
    @ApiOperation("查看今日轨迹")
    @GetMapping("/TrackToday")
    public ResponseResult ListLocation(String PersonId, String date, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        List<Locationmation> todayList=locationService.ListLocation(PersonId, date);
        if (todayList.size() == 0) {
            result.setCode(ResultCode.NULLDATA.getCode());
            result.setMessage(ResultCode.NULLDATA.getMessage());
            return result.setData("");
        }

        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(todayList);

    }


    @UserLoginToken
    @ApiOperation("历史轨迹列表")
    @PostMapping("/HistoricalTrack")
    public ResponseResult HistoricalTrack(@RequestBody SearchModel searchModel, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(searchModel.getPageIndex(), searchModel.getPageSize());
        List<Locationmation> allItems = locationService.HistoricalTrack(searchModel);
        try {
            if (allItems.size() == 0) {
                result.setCode(ResultCode.NULLDATA.getCode());
                result.setMessage(ResultCode.NULLDATA.getMessage());
                return result.setData("");
            }

            PageInfo<Locationmation> info = new PageInfo<>(allItems);//全部商品
            int countNums = (int) info.getTotal();            //总记录数
            PageBean<Locationmation> pageData = new PageBean<>(searchModel.getPageIndex(), searchModel.getPageSize(), countNums);
            pageData.setTotalPage(info.getPages());//总页数
            pageData.setItems(allItems);
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage(ResultCode.SUCCESS.getMessage());
            return result.setData(pageData);
        } catch (Exception ex) {
            result.setCode(ResultCode.UNKNOW_ERROR.getCode());
            result.setMessage(ResultCode.UNKNOW_ERROR.getMessage());
            return result.setData(ex.toString());
        }


    }

    @UserLoginToken
    @ApiOperation("查看定位信息")
    @GetMapping("/GetLocation")
    public ResponseResult GetLocation(@RequestParam int PersonId, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(locationService.GetLocation(PersonId));
    }

    @UserLoginToken
    @ApiOperation("查看当前位置")
    @GetMapping("GetLocationByPerson")
    public ResponseResult GetLocationByPerson(@RequestParam String PersonId) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(locationService.GetLocationByPerson(PersonId));
    }


    @UserLoginToken
    @ApiOperation("导出定位信息列表")
    @PostMapping("ExportLocation")
    public ResponseResult export(@RequestBody SearchModel searchModel) {
        //ResponseResult result = new ResponseResult();
        ResponseResult rtn = new ResponseResult();
        List<Locationmation> allItems = locationService.HistoricalTrack(searchModel);
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet("打印历史定位信息");
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("序号");
            row.createCell(1).setCellValue("姓名");
            row.createCell(2).setCellValue("设备编号");
            row.createCell(3).setCellValue("定位类型");
            row.createCell(4).setCellValue("定位经度");
            row.createCell(5).setCellValue("定位纬度");
            row.createCell(6).setCellValue("报警状态");
            row.createCell(7).setCellValue("剩余电量");
            row.createCell(8).setCellValue("信号强弱");
            row.createCell(9).setCellValue("接收时间");
            row.createCell(10).setCellValue("位置");

            allItems.forEach(printOrder -> {
                int lastRowNum = sheet.getLastRowNum();
                HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
                dataRow.createCell(0).setCellValue(printOrder.getId());
                dataRow.createCell(1).setCellValue(printOrder.getPersonid());
                dataRow.createCell(2).setCellValue(printOrder.getDevicecoding());
                dataRow.createCell(3).setCellValue(printOrder.getLocationtype());
                dataRow.createCell(4).setCellValue(printOrder.getLatitude());
                dataRow.createCell(5).setCellValue(printOrder.getLongitude());
                dataRow.createCell(6).setCellValue(printOrder.getAlarmstate());
                dataRow.createCell(7).setCellValue(printOrder.getSurpluselectricity());
                dataRow.createCell(8).setCellValue(printOrder.getSignalintensity());
                dataRow.createCell(9).setCellValue(printOrder.getTimestamp());
                dataRow.createCell(10).setCellValue(printOrder.getAddress());

            });
            String dateTime = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + ".xls";
            File file = new File(System.getProperty("user.dir") + "\\WebApi\\ExportExecl\\"+ dateTime);
            rtn.setData(dateTime);
            workbook.write(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        rtn.setMessage("导出成功");
        rtn.setCode(200);
        return rtn;
    }


}
