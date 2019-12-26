package com.prisonapp.business.controller.dw_supervise;


import com.common.common.Uploadfiles.Upload;
import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.dw_supervise.*;
import com.prisonapp.business.service.dw_supervise.SuperviseService;
import com.prisonapp.token.getuserid.GetUserId;
import com.prisonapp.token.tation.UserLoginToken;
import com.prisonapp.tool.CacheUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;


@Api(value="监督controller",tags={"监督保外人员的请假"})
@RestController
@RequestMapping("/app/supervise")
public class SuperviseController {


    @Autowired
    private SuperviseService superviseService;

    private GetUserId getUserId = new GetUserId();
    private ResultGetApplyLeaveListModel resultGetApplyLeaveListModel = new ResultGetApplyLeaveListModel();
    private ResultGetSuperviseTaskModel resultGetSuperviseTaskModel =new ResultGetSuperviseTaskModel();
    private ResultSet result = new ResultSet();
    private Upload upload =new Upload();

  //  @UserLoginToken
    @ApiOperation(value = "获取保外人员的外出申请列表")
    @GetMapping("/getApplyLeaveList")
    public ResultSet getApplyLeaveList(@ApiParam(name = "statusCode",value = "审批状态编号") @RequestParam(required = true)String statusCode,@ApiParam(name = "count",value = "当前已经获取的数据条数") @RequestParam(required = true)int count,@ApiParam(name = "requestCount",value = "请求获取数据的条数") @RequestParam(required = true)int requestCount) {
        List<GetApplyLeaveListModel> ApplyLeaveListModels =superviseService.getApplyLeaveList(statusCode,count,requestCount,getUserId.getUserId());
        int totalCount =(superviseService.getTotalApplyLeaveList(statusCode,getUserId.getUserId())).size();
        for (GetApplyLeaveListModel item: ApplyLeaveListModels) {
            List<ApplyRecordModel> applyRecords = superviseService.applyRecord(item.getCode());//取出请假申请
            Long End =Long.parseLong(item.getEndTimestamp());
            Long Start =Long.parseLong(item.getStartTimestamp());
            int days =(int)((End-Start)/86400000);
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

  //  @UserLoginToken
    @ApiOperation(value = "提交保外人员外出申请")
    @PostMapping("/submitApplyLeave")
    public ResultSet submitApplyLeave(SubmitApplyLeaveModel submitApplyLeaveModel,@ApiParam(name = "startDate",value = "起始时间戳")@RequestParam(required = true)String  startDate,@ApiParam(name = "endDate",value = "结束时间戳") @RequestParam(required = true)String endDate){
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

   // @UserLoginToken
    @ApiOperation(value = "上传录音文件")
    @PostMapping("/uploadAudio")
    public ResultSet uploadAudio(MultipartFile file)  {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String url =System.getProperty("user.dir")+"\\prisonapp\\"+"\\src\\"+"\\main\\"+"\\resources\\"+"\\uploadFile\\"+formatter.format(date);

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

   // @UserLoginToken
    @ApiOperation(value = "获取保外人员的执行任务")
    @GetMapping("/getSuperviseTask")
    public  ResultSet getSuperviseTask(){
        List<GetSuperviseTaskModel> getSuperviseTaskModel = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        List<TReportsettingsModel> tReportsettingsModels =superviseService.getSuperviseTask();
        for(TReportsettingsModel item: tReportsettingsModels){
            GetSuperviseTaskModel getSuperviseTaskModel1=new GetSuperviseTaskModel();
            getSuperviseTaskModel1.setStartDate(item.getBegindate());
            //算出结束日期
            calendar.setTime(item.getBegindate());
            calendar.add(calendar.DATE,5); //把日期往后增加一天,整数  往后推,负数往前移动

            getSuperviseTaskModel1.setEndDate(calendar.getTime());
            getSuperviseTaskModel1.setType(item.getType());
            getSuperviseTaskModel1.setTypeName(item.getTypename());
            getSuperviseTaskModel.add(getSuperviseTaskModel1);
        }
        Collections.reverse(getSuperviseTaskModel);
        resultGetSuperviseTaskModel.setTotalCount(tReportsettingsModels.size());
        resultGetSuperviseTaskModel.setList(getSuperviseTaskModel);
        result.resultCode=0;
        result.resultMsg="";
        result.data=resultGetSuperviseTaskModel;
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = " 保外人员人脸识别签到")
    @PostMapping("/faceRecognize")
    public ResultSet faceRecognize(MultipartFile file){
        List<FaceRecognizeModel> faceRecognizeModels = superviseService.faceRecognize(getUserId.getUserId());
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = faceRecognizeModels;
        return result;
    }

}
