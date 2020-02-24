package com.admin.admin.controller.dw_leave;


import com.admin.admin.entity.dw_auditor.AuditorInformation;
import com.admin.admin.entity.dw_leave.CountResult;
import com.admin.admin.entity.dw_leave.LeaveInformation;
import com.admin.admin.entity.dw_location.Locationmation;
import com.admin.admin.service.dw_leave.LeaveService;
import com.admin.model.leave.LeavefModel;
import com.admin.model.search.SearchModel;
import com.admin.model.singin.SinginModel;
import com.admin.page.PageBean;
import com.admin.page.PageUtil;
import com.admin.token.tation.UserLoginToken;
import com.common.common.authenticator.CalendarAdjust;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

@Api(value="审核请假记录controller",tags={"外出记录管理"})

@RestController
@RequestMapping("/Leave")
public class LeaveController {
    @Autowired
    protected LeaveService leaveService;

    private ResponseResult result = new ResponseResult();

   // @UserLoginToken
    @ApiOperation(value = "获取全部请假信息")
    @PostMapping("/listLeave")
    public ResponseResult listLeave(@RequestBody SearchModel searchModel) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        PageHelper.startPage(searchModel.getPageIndex(), searchModel.getPageSize());
        List<LeavefModel> allItems = leaveService.getLeave(searchModel);
        try {
            if (allItems.size() == 0) {
                result.setCode(ResultCode.NULLDATA.getCode());
                result.setMessage(ResultCode.NULLDATA.getMessage());
                return result.setData("");
            }
            PageInfo<LeavefModel> info = new PageInfo<>(allItems);//全部商品
            int countNums = (int) info.getTotal();            //总记录数
            PageBean<LeavefModel> pageData = new PageBean<>(searchModel.getPageIndex(), searchModel.getPageSize(), countNums);
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
    @ApiOperation(value = "增加审批信息")
    @PostMapping("/addAuditor")
    public ResponseResult insertAuditor(@RequestBody AuditorInformation auditorInformation, HttpServletResponse response) {
        LeaveInformation leaveInformations = leaveService.getLeaveInformation(auditorInformation.getLeaveorder());
        if (leaveInformations != null) {
            int updateLeaveStatus = leaveService.updateLeaveStatus(auditorInformation);
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage(ResultCode.SUCCESS.getMessage());
            return result.setData(leaveService.insertAuditor(auditorInformation));
        } else {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("该请假单不存在");
        }
    }

    @UserLoginToken
    @ApiOperation(value = "删除审批信息")
    @PostMapping("/deleteAuditor")
    public ResponseResult deleteAuditor(@ApiParam(name = "leaveOrder", value = "请假单号") @RequestParam String leaveOrder, HttpServletResponse response) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveService.deleteAuditor(leaveOrder));
    }

//    @UserLoginToken
//    @ApiOperation(value = "销假")
//    @PostMapping("cancelLeave")
//    public ResponseResult cancelAuditor(@ApiParam(name = "leaveorder", value = "请假单号") @RequestParam(required = true) String leaveorder, HttpServletResponse response) {
//        result.setCode(ResultCode.SUCCESS.getCode());
//        result.setMessage(ResultCode.SUCCESS.getMessage());
//        return result.setData(leaveService.cancelAuditor(leaveorder));
//    }

    @ApiOperation(value = "导出请假信息")
    @PostMapping("/ExportExecl")
    @UserLoginToken
    public ResponseResult ExportExecl(@RequestBody SearchModel searchModel){
        List<LeavefModel> allItems = leaveService.getLeave(searchModel);
        String dateTime = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) +"外出信息"+ ".xls";
        File file = new File(System.getProperty("user.dir") + "\\WebApi\\ExportExecl\\"+ dateTime);
        result.setData(dateTime);
        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet("外出记录信息");
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("序号");
            row.createCell(1).setCellValue("申请人");
            row.createCell(2).setCellValue("单据状态");
            row.createCell(3).setCellValue("外出目的地");
            row.createCell(4).setCellValue("外出开始时间");
            row.createCell(5).setCellValue("外出结束时间");
            row.createCell(6).setCellValue("外出天数");
            row.createCell(7).setCellValue("外出理由");
            row.createCell(8).setCellValue("申请时间");
            row.createCell(9).setCellValue("审批时间");


            allItems.forEach(printOrder -> {
                int lastRowNum = sheet.getLastRowNum();
                HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
                dataRow.createCell(0).setCellValue(printOrder.getId());
                dataRow.createCell(1).setCellValue(printOrder.getPersonname());
                dataRow.createCell(2).setCellValue(printOrder.getStates());
                dataRow.createCell(3).setCellValue(printOrder.getLeavedestination());
                dataRow.createCell(4).setCellValue(printOrder.getStarttimestamp());
                dataRow.createCell(5).setCellValue(printOrder.getEndtimestamp());
                dataRow.createCell(6).setCellValue(CalendarAdjust.getDays(CalendarAdjust.GetYear(printOrder.getStarttimestamp()),CalendarAdjust.GetYear(printOrder.getEndtimestamp())));
                dataRow.createCell(7).setCellValue(printOrder.getReason());
                dataRow.createCell(8).setCellValue(printOrder.getSubittimestamp());
                dataRow.createCell(9).setCellValue(printOrder.getAuditordatetime());

            });



            workbook.write(file);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        result.setMessage("导出成功");
        result.setCode(200);
        return result;
    }

    @ApiOperation(value = "平台外出申请统计")
    @GetMapping("/CountLeave")
    @UserLoginToken
    public ResponseResult countLeave(@RequestParam String city,String area,String startTime, String endTime){
        try{
            List<CountResult> countResults =leaveService.countLeave(city,area, startTime,endTime);
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage(ResultCode.SUCCESS.getMessage());
            return result.setData(countResults);
        }catch (Exception ex){
            result.setCode(500);
            result.setMessage(ex.toString());
            return result.setData(" ");
        }

    }

//    @ApiOperation(value = "平台功能统计")
//    @PostMapping("/CountFunction")
//    @UserLoginToken
//    public  ResponseResult countFunction(@RequestParam String city,String area){
//        result.setCode(ResultCode.SUCCESS.getCode());
//        result.setMessage(ResultCode.SUCCESS.getMessage());
//        return result.setData(leaveService.countFunction(city,area));
//    }
}
