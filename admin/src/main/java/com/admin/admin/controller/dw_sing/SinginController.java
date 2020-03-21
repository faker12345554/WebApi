package com.admin.admin.controller.dw_sing;

import com.admin.admin.service.dw_sing.SinginService;
import com.admin.model.search.SearchModel;
import com.admin.model.singin.SinginModel;
import com.admin.page.PageBean;
import com.admin.token.tation.UserLoginToken;
import com.common.common.authenticator.CalendarAdjust;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Api(value = "签到信息管理Controller", tags = {"报到信息管理"})
@RestController
@RequestMapping("/Singin")
public class SinginController {
    @Autowired
    private SinginService singinService;


    @UserLoginToken
    @ApiOperation("查看签到信息")
    @GetMapping("/getSingin")
    public ResponseResult getSingin(@RequestParam int Id, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(singinService.getSingin(Id));
    }

    @UserLoginToken
    @ApiOperation("查看签到信息列表")
    @PostMapping("/ListSingin")
    public ResponseResult ListSingin(@RequestBody SearchModel searchModel) {
        ResponseResult result = new ResponseResult();
        try {
            PageHelper.startPage(searchModel.getPageIndex(), searchModel.getPageSize());
            List<SinginModel> allItems = singinService.ListSingin(searchModel);
            if (allItems.size() == 0) {
                result.setCode(ResultCode.NULLDATA.getCode());
                result.setMessage(ResultCode.NULLDATA.getMessage());
                return result.setData("");
            }


            PageInfo<SinginModel> info = new PageInfo<>(allItems);//全部商品
            int countNums = (int) info.getTotal();            //总记录数
            PageBean<SinginModel> pageData = new PageBean<>(searchModel.getPageIndex(), searchModel.getPageSize(), countNums);
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

    // @UserLoginToken
    @ApiOperation("查看音视频列表")
    @PostMapping("/ListAudio")
    public ResponseResult ListAudio(@RequestBody SearchModel searchModel) {
        ResponseResult result = new ResponseResult();
        try {
            PageHelper.startPage(searchModel.getPageIndex(), searchModel.getPageSize());
            List<SinginModel> allItems = singinService.ListAudio(searchModel);
            if (allItems.size() == 0) {
                result.setCode(ResultCode.NULLDATA.getCode());
                result.setMessage(ResultCode.NULLDATA.getMessage());
                return result.setData("");
            }

            PageInfo<SinginModel> info = new PageInfo<>(allItems);//全部商品
            for (SinginModel item : allItems) {
                item.setDurationtime(CalendarAdjust.getDatePoor(CalendarAdjust.timeStamp2Date(Long.parseLong(item.getCanceltimestamp())),
                        CalendarAdjust.timeStamp2Date(Long.parseLong(item.getCalltimestamp()))));
            }


            int countNums = (int) info.getTotal();            //总记录数
            PageBean<SinginModel> pageData = new PageBean<>(searchModel.getPageIndex(), searchModel.getPageSize(), countNums);
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
    @ApiOperation("导出音视频信息")
    @PostMapping("ExportAudio")
    public ResponseResult ExportAudio(@RequestBody SearchModel searchModel) {
        ResponseResult result = new ResponseResult();
        List<SinginModel> allItems = singinService.ListAudio(searchModel);
        String dateTime = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + "音视频信息" + ".xls";
        File file = new File(System.getProperty("user.dir") + "/../webapps/Execl/音视频信息" + dateTime);
        result.setData("音视频信息" + dateTime);
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet("导出音视频信息");
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("序号");
            row.createCell(1).setCellValue("嫌疑人状态");
            row.createCell(2).setCellValue("姓名");
            row.createCell(3).setCellValue("性别");
            row.createCell(4).setCellValue("主办人");
            row.createCell(5).setCellValue("执行机关");
            row.createCell(6).setCellValue("通话方式");
            row.createCell(7).setCellValue("动作");
            row.createCell(8).setCellValue("联系民警");
            row.createCell(9).setCellValue("通话时长");
            row.createCell(10).setCellValue("接通时间");


            allItems.forEach(printOrder -> {
                int lastRowNum = sheet.getLastRowNum();
                HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
                dataRow.createCell(0).setCellValue(printOrder.getId());
                dataRow.createCell(1).setCellValue(printOrder.getSuspectstatus());
                dataRow.createCell(2).setCellValue(printOrder.getPersonname());
                dataRow.createCell(3).setCellValue(printOrder.getGender());
                dataRow.createCell(4).setCellValue(printOrder.getSponsor());
                dataRow.createCell(5).setCellValue(printOrder.getPolicestation());
                dataRow.createCell(6).setCellValue(printOrder.getReporttype());
                dataRow.createCell(7).setCellValue(printOrder.getReportstatus());
                dataRow.createCell(8).setCellValue(printOrder.getSponsor());
                dataRow.createCell(9).setCellValue(printOrder.getDurationtime());
                dataRow.createCell(10).setCellValue(printOrder.getCreatetime());


            });


            workbook.write(file);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        result.setMessage("导出成功");
        result.setCode(200);
        return result;
    }

    @UserLoginToken
    @ApiOperation("导出签到信息")
    @PostMapping("ExportSingIn")
    public ResponseResult ExportSingIn(@RequestBody SearchModel searchModel) {
        ResponseResult result = new ResponseResult();
        List<SinginModel> allItems = singinService.ListSingin(searchModel);
        String dateTime = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + "签到信息" + ".xls";
        File file = new File(System.getProperty("user.dir") + "/../webapps/Execl/报到信息" + dateTime);
        result.setData("报到信息" + dateTime);
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet("打印历史定位信息");
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("序号");
            row.createCell(1).setCellValue("姓名");
            row.createCell(2).setCellValue("性别");
            row.createCell(3).setCellValue("年龄");
            row.createCell(4).setCellValue("人员状态");
            row.createCell(5).setCellValue("执行单位");
            row.createCell(6).setCellValue("主办人");
            row.createCell(7).setCellValue("执行民警");
            row.createCell(8).setCellValue("签到类型");
            row.createCell(9).setCellValue("签到时间");
            row.createCell(10).setCellValue("备注");


            allItems.forEach(printOrder -> {
                int lastRowNum = sheet.getLastRowNum();
                HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
                dataRow.createCell(0).setCellValue(printOrder.getId());
                dataRow.createCell(1).setCellValue(printOrder.getPersonname());
                dataRow.createCell(2).setCellValue(printOrder.getGender());
                dataRow.createCell(3).setCellValue(printOrder.getAge());
                dataRow.createCell(4).setCellValue(printOrder.getSuspectstatus());
                dataRow.createCell(5).setCellValue(printOrder.getHandleunit());
                dataRow.createCell(6).setCellValue(printOrder.getHandlepeson());
                dataRow.createCell(7).setCellValue(printOrder.getSponsor());
                dataRow.createCell(8).setCellValue(printOrder.getTypename());
                dataRow.createCell(9).setCellValue(printOrder.getCreatetime());
                dataRow.createCell(10).setCellValue(printOrder.getRemark());

            });


            workbook.write(file);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        result.setMessage("导出成功");
        result.setCode(200);
        return result;
    }


}
