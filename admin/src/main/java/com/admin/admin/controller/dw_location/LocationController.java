package com.admin.admin.controller.dw_location;

import com.admin.admin.entity.dw_location.Locationmation;
import com.admin.admin.service.dw_location.LocationService;
import com.admin.model.Execl.ExeclModel;
import com.admin.page.PageBean;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    private ResponseResult result = new ResponseResult();

    /*
    定位信息列表
     */
    @ApiOperation("定位信息列表")
    @GetMapping("/LocationList")
    public ResponseResult listLocationModel(@RequestParam String Condition, @RequestParam int PageSize, @RequestParam int PageIndex,
                                            HttpServletResponse response) {

        System.out.println(Condition);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(locationService.listLocationModel(Condition, PageSize, PageIndex));
    }

    @ApiOperation("查看今日轨迹")
    @GetMapping("/TrackToday")
    public ResponseResult ListLocation(String PersonId, @RequestParam(required = false) String date, @RequestParam(required = false) int PageSize, @RequestParam(required = false) int PageIndex, HttpServletResponse response) {

        if (date != null && date != "") {
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage(ResultCode.SUCCESS.getMessage());
            return result.setData(locationService.ListLocation(PersonId, date));
        }
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(PageIndex, PageSize);

        List<Locationmation> allItems = locationService.ListLocation(PersonId, date);
        PageInfo<Locationmation> info = new PageInfo<>(allItems);//全部商品
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<Locationmation> pageData = new PageBean<>(PageIndex, PageSize, countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allItems);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(pageData);


    }

//    @ApiOperation("历史轨迹列表")
//    @GetMapping("/HistoricalTrack")
//    public ResponseResult HistoricalTrack(@RequestParam String PersonId,HttpServletResponse response){
//
//    }

    @ApiOperation("查看定位信息")
    @GetMapping("/GetLocation")
    public ResponseResult GetLocation(@RequestParam int PersonId, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(locationService.GetLocation(PersonId));
    }

    @ApiOperation("查看当前位置")//
    @GetMapping("GetLocationByPerson") //HttpServletResponse response 这个可以删掉了，你用不上的
    public ResponseResult GetLocationByPerson(@RequestParam String PersonId) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(locationService.GetLocationByPerson(PersonId));
    }


    //上边那个方法就是我在网上找的 但是结果全是乱码 你调用一下 这个就是控制层了 我知道，
    @ApiOperation("导出定位信息列表")
    @PostMapping("ExportLocation")
    public ResponseResult export( @RequestBody ExeclModel execlModel) {
        ResponseResult rtn = new ResponseResult();
        List<Locationmation> allItems = locationService.HistoricalTrack(execlModel);
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
            File file = new File("E:\\WebApi\\admin\\src\\main\\resources\\Execl\\" + dateTime);
            workbook.write(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        rtn.setData("导出成功");
        rtn.setCode(200);
        return rtn;
    }


}
