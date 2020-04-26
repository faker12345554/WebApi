package com.admin.admin.controller.dw_person;


import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_prisonsetting.TPrisonsetting;
import com.admin.admin.entity.dw_user.User;
import com.admin.admin.service.dw_app.MessageService;
import com.admin.admin.service.dw_person.PersoinfoService;
import com.admin.model.search.SearchModel;
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
import java.util.Map;


@Api(value = "保外人员Controller", tags = {"保外人员信息管理"})
@RestController
@RequestMapping("/Person")
public class PersonController {

    @Autowired
    private PersoinfoService persoinfoService;//


    @Autowired
    private MessageService messageService;


    //新增
    @UserLoginToken
    @ApiOperation("新增人员信息")
    @PostMapping("/insertPersion")
    public ResponseResult insertPersion(@RequestBody Personinformation personinformation) {
        ResponseResult result = new ResponseResult();
        if (persoinfoService.getPersonByCard(personinformation.getCard()) > 1) {
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("参数'card'重复,身份证号应是唯一凭证");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.insertPersion(personinformation));
    }

    //修改
    @UserLoginToken
    @ApiOperation("修改人员信息")
    @PostMapping("/updatePersion")
    public ResponseResult updatePersion(@RequestBody Personinformation personinformation, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.updatePersion(personinformation));
    }

    //删除
    @UserLoginToken
    @ApiOperation("删除人员信息")
    @GetMapping("/deletePersion")
    public ResponseResult deletePersion(@RequestParam boolean flag, @RequestParam String PersonId, HttpServletResponse response) {
        ResponseResult result = new ResponseResult();
        if (flag == true) {
            result.setCode(ResultCode.DATA_DUPLICATION.getCode());
            result.setMessage(ResultCode.DATA_DUPLICATION.getMessage());
            return result.setData("参数'flag'错误");
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.deletePersion(flag, PersonId));
    }

    //获取
    //@UserLoginToken
    @ApiOperation("查看人员信息")
    @GetMapping("/getPersoin")
    public ResponseResult getPersoin(@RequestParam String id,String Caseno) throws  Exception {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(persoinfoService.getPersoin(id,Caseno));
    }
    @UserLoginToken
    @ApiOperation("变更主办人")
    @GetMapping("/updateSponsor")
    public ResponseResult updateSponsor(@RequestParam String Name, String id, String PersonId) {
        ResponseResult result = new ResponseResult();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("变更成功");
        return result.setData(persoinfoService.updateSponsor(Name, id, PersonId));
    }
    @UserLoginToken
    @ApiOperation("配置管理方式")
    @PostMapping("/insertprison")
    public ResponseResult insertprison(@RequestBody List<TPrisonsetting> List) {
        ResponseResult result = new ResponseResult();
        for (TPrisonsetting item : List) {
            if (persoinfoService.Getprison(item.getPersonid(), item.getSettingname()) ==0){
                item.setSettingcheck(true);
                item.setSettingtime(new Date());
                persoinfoService.insertprison(item);
            } else  {
              persoinfoService.delconfig(item);

            }
        }
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("配置成功");
        return result.setData("配置成功");
    }
//    @UserLoginToken
//    @ApiOperation("删除配置管理方式")
//    @PostMapping("/delconfig")
//    public  ResponseResult delconfig(@RequestBody List<TPrisonsetting> List){
//        for (TPrisonsetting item : List) {
//            persoinfoService.delconfig(item);
//        }
//        result.setCode(ResultCode.SUCCESS.getCode());
//        result.setMessage("取消成功");
//        return result.setData("取消成功");
//
//    }

    //@UserLoginToken
    @ApiOperation("人员信息列表")
    @PostMapping("/ListPerson")
    public ResponseResult ListPerson(@RequestBody(required = false) SearchModel searchModel) throws Exception {
        ResponseResult result = new ResponseResult();

        PageHelper.startPage(searchModel.getPageIndex(), searchModel.getPageSize());
        List<Personinformation> allitems = persoinfoService.ListPerson(searchModel);
        PageInfo<Personinformation> info = new PageInfo<>(allitems);//全部商品
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<Personinformation> pageData = new PageBean<>(searchModel.getPageIndex(), searchModel.getPageSize(), countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allitems);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(pageData);
    }


    @UserLoginToken
    @ApiOperation("导出监居人员信息")
    @PostMapping("/ExportPerson")
    public ResponseResult ExportExcel(@RequestBody SearchModel searchModel) throws Exception{

        ResponseResult rtn = new ResponseResult();
        List<Personinformation> allItems = persoinfoService.ListPerson(searchModel);

        try (HSSFWorkbook workbook = new HSSFWorkbook()) {
            HSSFSheet sheet = workbook.createSheet("导出取保监居人员信息");
            HSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("姓名");
            row.createCell(1).setCellValue("人员编号");
            row.createCell(2).setCellValue("案件编号");
            row.createCell(3).setCellValue("办案单位");
            row.createCell(4).setCellValue("嫌疑人状态");
            row.createCell(5).setCellValue("性别");
            row.createCell(6).setCellValue("主办人");
            row.createCell(7).setCellValue("执行机关");
            row.createCell(8).setCellValue("执行开始时间");
            row.createCell(9).setCellValue("执行民警");
            row.createCell(10).setCellValue("案件类型");
            row.createCell(11).setCellValue("采取管理方式");
            row.createCell(12).setCellValue("违规程度");
            row.createCell(13).setCellValue("创建人");
            row.createCell(14).setCellValue("创建时间");

            allItems.forEach(printOrder -> {
                int lastRowNum = sheet.getLastRowNum();
                HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
                dataRow.createCell(0).setCellValue(printOrder.getPersonname());
                dataRow.createCell(1).setCellValue(printOrder.getPersonid());
                dataRow.createCell(2).setCellValue(printOrder.getCaseno());
                dataRow.createCell(3).setCellValue(printOrder.getHandleunit());

                dataRow.createCell(4).setCellValue(printOrder.getSuspectstatus());
                dataRow.createCell(5).setCellValue(printOrder.getGender());
                dataRow.createCell(6).setCellValue(printOrder.getHandlepeson());
                dataRow.createCell(7).setCellValue(printOrder.getPolicestation());
                dataRow.createCell(8).setCellValue(printOrder.getBailoutbegindate());
                dataRow.createCell(9).setCellValue(printOrder.getSponsor());
                dataRow.createCell(10).setCellValue(printOrder.getCasetype());
                String type = "";
                dataRow.createCell(11).setCellValue(type);
                dataRow.createCell(12).setCellValue(printOrder.getCasetype());
                dataRow.createCell(13).setCellValue(printOrder.getFounderid());
                dataRow.createCell(14).setCellValue(printOrder.getFoundertime());
                for (String item : printOrder.getManagementStyle()) {
                    type += item + ',';
                }

//                dataRow.createCell(8).setCellValue(printOrder.getModifierid());
//                dataRow.createCell(9).setCellValue(printOrder.getModifiertime());
//                dataRow.createCell(10).setCellValue(printOrder.getAddress());
            });
            String dateTime = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + ".xls";
            File file = new File(System.getProperty("user.dir") + "/../webapps/Execl/" +
                    "监居人员"+ dateTime);
            rtn.setData("监居人员"+dateTime);
            workbook.write(file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        rtn.setMessage("导出成功");
        rtn.setCode(200);

        return rtn;
    }
    @UserLoginToken
    @ApiOperation("获取人员违规信息")
    @GetMapping("/getvolocation")
    public ResponseResult getvolocation(String personid){
        ResponseResult rtn = new ResponseResult();
        //rtn.setMessage("");
        rtn.setCode(200);
        return rtn.setData(persoinfoService.getvolocation(personid));
    }

    @UserLoginToken
    @ApiOperation("查看人员违规信息")
    @GetMapping("/getdetails")
    public ResponseResult getdetails(int id,String personid,int Pageindex,int pageSize){
        ResponseResult rtn = new ResponseResult();
        PageHelper.startPage(Pageindex, pageSize);
        List<Map<String,String>> allitems = persoinfoService.getdetails(id,personid);
        PageInfo<Map<String,String>> info = new PageInfo<>(allitems);//全部商品
        int countNums = (int) info.getTotal();            //总记录数
        PageBean<Map<String,String>> pageData = new PageBean<>(Pageindex, pageSize, countNums);
        pageData.setTotalPage(info.getPages());//总页数
        pageData.setItems(allitems);
        //rtn.setMessage("");
        rtn.setCode(200);
        return rtn.setData(pageData);
    }
    @UserLoginToken
    @ApiOperation("同步信息")
    @GetMapping("/Synchrondata")
    public ResponseResult SynchronData(int type) throws Exception{
        ResponseResult result=new ResponseResult();
        try {
            if(type==1){ //同步所有信息
                messageService.Synchronizedpolice();
                persoinfoService.getlistpernson();
                messageService.Synchronouscase();
            }else if (type==2){ //同步主办人
                messageService.Synchronizedpolice();
            }else if(type==3){ //同步嫌疑人
                persoinfoService.getlistpernson();
                persoinfoService.getpersonid();
            }else if (type==4){ //同步人脸照片
                messageService.Synchronouscase();
            }
            result.setCode(200);
            result.setMessage("成功");

        }catch (Exception ex){
            result.setCode(500);
            result.setMessage(ex.toString());
        }
        return result;

    }


}
