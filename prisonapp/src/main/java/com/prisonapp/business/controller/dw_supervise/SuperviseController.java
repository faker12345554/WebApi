package com.prisonapp.business.controller.dw_supervise;


import com.common.common.Uploadfiles.Upload;
import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.dw_supervise.ApplyRecord;
import com.prisonapp.business.entity.dw_supervise.GetApplyLeaveListModel;
import com.prisonapp.business.entity.dw_supervise.ResultGetApplyLeaveListModel;
import com.prisonapp.business.entity.dw_supervise.SubmitApplyLeaveModel;
import com.prisonapp.business.service.dw_supervise.SuperviseService;
import com.prisonapp.tool.CacheUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/app/supervise")
public class SuperviseController {


    @Autowired
    private SuperviseService superviseService;

    private ResultGetApplyLeaveListModel resultGetApplyLeaveListModel = new ResultGetApplyLeaveListModel();
    private ResultSet result = new ResultSet();
    private Upload upload =new Upload();

    @ApiOperation(value = "获取保外人员的外出申请列表")
    @GetMapping("/getApplyLeaveList")
    public ResultSet getApplyLeaveList( @RequestParam(required = true)String statusCode,@RequestParam(required = true)int count,@RequestParam(required = true)int requestCount) {
        List<GetApplyLeaveListModel> ApplyLeaveListModels =superviseService.getApplyLeaveList(statusCode,count,requestCount,CacheUtils.get("UserId").toString());
        int totalCount =(superviseService.getTotalApplyLeaveList(statusCode,CacheUtils.get("UserId").toString())).size();
        for (GetApplyLeaveListModel item: ApplyLeaveListModels) {
            List<ApplyRecord> applyRecords = superviseService.applyRecord(item.getCode());//取出请假申请
            int days =(int)((item.getEndTimestamp().getTime()-item.getStartTimestamp().getTime())/86400000);
            //(int) ((oDate.getTime() - fDate.getTime()) / 24 * 3600 * 1000)
            item.setApplyRecord(applyRecords);
            item.setDays(days);
        }

        resultGetApplyLeaveListModel.totalCount=totalCount;
        resultGetApplyLeaveListModel.list=ApplyLeaveListModels;
        result.resultCode=0;
        result.resultMsg="";
        result.data=resultGetApplyLeaveListModel;

        return result;
    }


    @ApiOperation(value = "提交保外人员外出申请")
    @GetMapping("/submitApplyLeave")
    public ResultSet submitApplyLeave(SubmitApplyLeaveModel submitApplyLeaveModel,String  startDate, String endDate){
        String code="qj"+System.currentTimeMillis();
        Long  longStartDate =Long.valueOf(startDate);
        Date dateStartDate = new Date(Long.valueOf(longStartDate));
        Long  longEndDate =Long.valueOf(endDate);
        Date dateEndDate= new Date(Long.valueOf(longEndDate));
        int res =  superviseService.submitApplyLeave(submitApplyLeaveModel,dateStartDate,dateEndDate,code,CacheUtils.get("UserId").toString());
        if(res!=0){
            result.resultCode=0;
            result.resultMsg="";
            result.data=code;
        }else
        {
            result.resultCode=0;
            result.resultMsg="提交失败";
            result.data=null;
        }

        return  result;
    }

    @ApiOperation(value = "上传录音文件")
    @PostMapping("/uploadAudio")
    public ResultSet uploadAudio(MultipartFile file){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String url =System.getProperty("user.dir")+"\\prisonapp\\"+"\\src\\"+"\\main\\"+"\\resources\\"+"\\uploadFile\\"+formatter.format(date);
        /*//C:\\Users\\tjh\\Desktop\\新建文件夹 (6)\\WebApi\\prisonapp\\src\\main\\resources\\uploadFile*/
        File path =new File(url);
        if  (!path.exists()  && !path.isDirectory())
        {
            path.mkdirs();
        }
        String fileName =file.getOriginalFilename();
        String res = upload.upload(url,file);
        if(res.equals("上传成功")){
            result.resultCode=0;
            result.resultMsg="";
            result.data=url+"\\"+fileName;
        }else {
            result.resultCode=1;
            result.resultMsg="上传失败";
            result.data=null;
        }
        return result;
    }

}
