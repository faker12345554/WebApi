package com.admin.admin.controller.dw_sing;

import com.admin.admin.entity.dw_location.Locationmation;
import com.admin.admin.entity.dw_sing.SinginInformation;
import com.admin.admin.service.dw_sing.SinginService;
import com.admin.model.Execl.ExeclModel;
import com.admin.model.search.SearchModel;
import com.admin.model.singin.SinginModel;
import com.admin.page.PageBean;
import com.admin.token.tation.UserLoginToken;
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


@Api(value="签到信息管理Controller",tags={"报到信息管理"})
@RestController
@RequestMapping("/Singin")
public class SinginControll {
    @Autowired
    private SinginService singinService;
    private ResponseResult result = new ResponseResult();

    @UserLoginToken
    @ApiOperation("查看签到信息")
    @GetMapping("/getSingin")
    public ResponseResult getSingin(@RequestParam int Id, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(singinService.getSingin(Id));
    }
   // @UserLoginToken
    @ApiOperation("签到信息列表")
    @PostMapping("/ListSingin")
    public ResponseResult ListSingin(@RequestBody SearchModel searchModel){
        try {
            PageHelper.startPage(searchModel.getPageIndex(), searchModel.getPageSize());
            List<SinginModel> allItems = singinService.ListSingin(searchModel);
            if (allItems.size()==0){
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
        }catch (Exception ex){
            result.setCode(ResultCode.UNKNOW_ERROR.getCode());
            result.setMessage(ResultCode.UNKNOW_ERROR.getMessage());
            return result.setData(ex.toString());
        }


    }

    @UserLoginToken
    @ApiOperation("音视频列表")
    @PostMapping("/ListAudio")
    public ResponseResult ListAudio(@RequestBody SearchModel searchModel){
        try {
            PageHelper.startPage(searchModel.getPageIndex(), searchModel.getPageSize());
            List<SinginModel> allItems = singinService.ListAudio(searchModel);
            if (allItems.size()==0){
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
        }catch (Exception ex){
            result.setCode(ResultCode.UNKNOW_ERROR.getCode());
            result.setMessage(ResultCode.UNKNOW_ERROR.getMessage());
            return result.setData(ex.toString());
        }


    }




    @ApiOperation("导出签到信息")
    @PostMapping("ExportSingIn")
    public ResponseResult ExportSingIn(@RequestBody SearchModel searchModel){
        List<SinginModel> allItems = singinService.ListSingin(searchModel);
        String dateTime = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) +"签到信息"+ ".xls";
        File file = new File(System.getProperty("user.dir") + "\\WebApi\\ExportExecl\\"+ dateTime);
        result.setData(dateTime);
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet("打印历史定位信息");
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("序号");
            row.createCell(1).setCellValue("嫌疑人状态");
            row.createCell(2).setCellValue("姓名");
            row.createCell(3).setCellValue("性别");
            row.createCell(4).setCellValue("年龄");
            row.createCell(5).setCellValue("报到类型");
            row.createCell(6).setCellValue("报到状态");
            row.createCell(7).setCellValue("管辖民警");
            row.createCell(8).setCellValue("限定活动区域");
            row.createCell(9).setCellValue("签到位置");
            row.createCell(10).setCellValue("签到时间");


            allItems.forEach(printOrder -> {
                int lastRowNum = sheet.getLastRowNum();
                HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
                dataRow.createCell(0).setCellValue(printOrder.getId());
                dataRow.createCell(1).setCellValue(printOrder.getSponsor());
                dataRow.createCell(2).setCellValue(printOrder.getPersonname());
                dataRow.createCell(3).setCellValue(printOrder.getGender());
                dataRow.createCell(4).setCellValue(printOrder.getAge());
                dataRow.createCell(5).setCellValue(printOrder.getReporttype());
                dataRow.createCell(6).setCellValue(printOrder.getReportstatus());
                dataRow.createCell(7).setCellValue(printOrder.getActivityarea());
                dataRow.createCell(8).setCellValue(printOrder.getAddress());
                dataRow.createCell(9).setCellValue(printOrder.getCreatetime());

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
