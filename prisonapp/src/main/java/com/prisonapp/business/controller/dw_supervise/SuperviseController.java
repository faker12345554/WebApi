package com.prisonapp.business.controller.dw_supervise;


import com.common.common.Uploadfiles.Upload;
import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.dw_supervise.*;
import com.prisonapp.business.entity.dw_user.UserModel;
import com.prisonapp.business.service.dw_supervise.SuperviseService;
import com.prisonapp.token.TokenUtil;
import com.prisonapp.token.tation.UserLoginToken;
import com.prisonapp.tool.AESDecode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.SSLEngine;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;


@Api(value="监督controller",tags={"监督保外人员的请假"})
@RestController
@RequestMapping("/app/supervise")
public class SuperviseController {


    @Autowired
    private SuperviseService superviseService;

    private ResultGetApplyLeaveListModel resultGetApplyLeaveListModel = new ResultGetApplyLeaveListModel();
    private ResultGetSuperviseTaskModel resultGetSuperviseTaskModel =new ResultGetSuperviseTaskModel();
    private FaceRecognizeModel faceRecognizeModel =new FaceRecognizeModel();
    private ResultSet result = new ResultSet();
    private Upload upload =new Upload();
    private HttpServletRequest request;


    @UserLoginToken
    @ApiOperation(value = "获取保外人员的外出申请列表")
    @GetMapping("/getApplyLeaveList")
    public ResultSet getApplyLeaveList(@ApiParam(name = "statusCode",value = "审批状态编号") @RequestParam(required = true)String statusCode,@ApiParam(name = "count",value = "当前已经获取的数据条数") @RequestParam(required = true)int count,@ApiParam(name = "requestCount",value = "请求获取数据的条数") @RequestParam(required = true)int requestCount) {
        List<GetApplyLeaveListModel> ApplyLeaveListModels;
        if (statusCode.equals("0")) {
            ApplyLeaveListModels = superviseService.getAllApplyLeaveList(count, requestCount, TokenUtil.getTokenUserId());
        } else {
            ApplyLeaveListModels = superviseService.getApplyLeaveList(statusCode, count, requestCount, TokenUtil.getTokenUserId());
        }
        if (ApplyLeaveListModels != null && !"".equals(ApplyLeaveListModels) && !"null".equals(ApplyLeaveListModels)) {

            int totalCount = (superviseService.getTotalApplyLeaveList(statusCode, TokenUtil.getTokenUserId())).size();
            for (GetApplyLeaveListModel item : ApplyLeaveListModels) {
                List<ApplyRecordModel> applyRecords = superviseService.applyRecord(item.getCode());//取出请假申请
                Long End = Long.parseLong(item.getEndTimestamp());
                Long Start = Long.parseLong(item.getStartTimestamp());
                int days = (int) ((End - Start) / 86400000);
                //(int) ((oDate.getTime() - fDate.getTime()) / 24 * 3600 * 1000)
                item.setApplyRecord(applyRecords);
                item.setDays(days);
            }
            resultGetApplyLeaveListModel.totalCount = totalCount;
            resultGetApplyLeaveListModel.list = ApplyLeaveListModels;

        }
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = resultGetApplyLeaveListModel;
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = "提交保外人员外出申请")
    @PostMapping("/submitApplyLeave")
    public ResultSet submitApplyLeave(SubmitApplyLeaveModel submitApplyLeaveModel,@ApiParam(name = "startDate",value = "起始时间戳")@RequestParam(required = true)String  startDate,@ApiParam(name = "endDate",value = "结束时间戳") @RequestParam(required = true)String endDate) throws ParseException {
        String code="qj"+System.currentTimeMillis();

        Long  longStartDate =Long.valueOf(startDate);//123456789
        Long  longEndDate =Long.valueOf(endDate);
        List<UserModel>  user =superviseService.getPersonname(TokenUtil.getTokenUserId());
        int res =  superviseService.submitApplyLeave(submitApplyLeaveModel,longStartDate,longEndDate,code,TokenUtil.getTokenUserId(),user.get(0).getAccountname());
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

    @UserLoginToken
    @ApiOperation(value = "上传录音文件")
    @PostMapping("/uploadAudio")
    public ResultSet uploadAudio(MultipartFile file)  {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        //SSLEngine request = null;
       // String x=request.getSession(true).getServletContext().getRealPath(File.separator+"upload");
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
            result.data="http:192.168.10.88:8009"+"/uploadFile/"+ formatter.format(date)+"/"+fileName;
        }else {
            result.resultCode=1;
            result.resultMsg="上传失败";
            result.data=null;
        }
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = "获取保外人员的执行任务")
    @GetMapping("/getSuperviseTask")
    public  ResultSet getSuperviseTask(){
       // List<GetSuperviseTaskModel> getSuperviseTaskModel = new ArrayList<>();
       // Calendar calendar = Calendar.getInstance();
        List<GetSuperviseTaskModel> getSuperviseTaskModels =superviseService.getSuperviseTask(TokenUtil.getTokenUserId());
        /*for(GetSuperviseTaskModel item: getSuperviseTaskModels){
            GetSuperviseTaskModel getSuperviseTaskModel1=new GetSuperviseTaskModel();
            getSuperviseTaskModel1.setStartDate(item.getStartDate());
            //算出结束日期
            calendar.setTime(item.getBegindate());
            calendar.add(calendar.DATE,5); //把日期往后增加一天,整数  往后推,负数往前移动

            getSuperviseTaskModel1.setEndDate(calendar.getTime());
            getSuperviseTaskModel1.setType(item.getType());
            getSuperviseTaskModel1.setTypeName(item.getTypename());
            getSuperviseTaskModel.add(getSuperviseTaskModel1);
        }*/
        Collections.reverse(getSuperviseTaskModels);
        resultGetSuperviseTaskModel.setTotalCount(getSuperviseTaskModels.size());
        resultGetSuperviseTaskModel.setList(getSuperviseTaskModels);
        result.resultCode=0;
        result.resultMsg="";
        result.data=resultGetSuperviseTaskModel;
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = " 保外人员人脸识别签到")
    @PostMapping("/faceRecognize")
    public ResultSet faceRecognize(MultipartFile file) throws Exception{
        List<TPersoninformation> tPersoninformations = superviseService.faceRecognize(TokenUtil.getTokenUserId());
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            //保存图片的路径
            String url = System.getProperty("user.dir") + "\\prisonapp\\" + "\\src\\" + "\\main\\" + "\\resources\\" + "\\uploadFace\\" + formatter.format(date);
          //  String url ="http:192.168.10.88:33389"+"\\uploadFace\\" + formatter.format(date);
            File path = new File(url);
            if (!path.exists() && !path.isDirectory()) {
                path.mkdirs();
            }
            String fileName = file.getOriginalFilename();
            String res = upload.upload(url, file);
            if (res.equals("上传成功")) {
               // String upLoadFaceUrl = "http:192.168.10.88:8009"+"/uploadFace/"+ formatter.format(date)+"/"+fileName;//这是真正有用的
                String upLoadFaceUrl = "http://sf.cnnc626.com/Data/image/2019-08-05/1.jpg";
                if (tPersoninformations != null) {
                   // upLoadFaceUrl="https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4058683704,1940854212&fm=26&gp=0.jpg";
                    //将两张图片进行对比，upLoadFaceUrl为用户传进来的图片路劲，第二个为数据库中的图片路劲
                    String comparedRes = AESDecode.faceCompared(upLoadFaceUrl, tPersoninformations.get(0).getFacepath());
                   // String comparedRes = AESDecode.faceCompared(upLoadFaceUrl, upLoadFaceUrl);
                    //获取json中的可信度并转换成float类型
                    JSONObject jsonObject = new JSONObject(comparedRes);
                    String similar = jsonObject.getString("confidence");
                    float fSimilar = Float.parseFloat(similar);
                    if (fSimilar >= 0.75) {
                        superviseService.insertFaceRecognize(TokenUtil.getTokenUserId(), 0, 0, upLoadFaceUrl);
                        List<FaceRecognizeModel> faceRecognizeModels= superviseService.getFaceRecognize(TokenUtil.getTokenUserId(),0);
                        faceRecognizeModel.setCode(faceRecognizeModels.get(0).getCode());
                        faceRecognizeModel.setPassed(true);
                        faceRecognizeModel.setSimilar(fSimilar);
                        faceRecognizeModel.setUrl(upLoadFaceUrl);
                        result.resultCode = 0;
                        result.resultMsg = "";
                        result.data = faceRecognizeModel;
                    }else{
                        superviseService.insertFaceRecognize(TokenUtil.getTokenUserId(), 0, 1, upLoadFaceUrl);
                        List<FaceRecognizeModel> faceRecognizeModels= superviseService.getFaceRecognize(TokenUtil.getTokenUserId(),0);
                        faceRecognizeModel.setCode(faceRecognizeModels.get(0).getCode());
                        faceRecognizeModel.setPassed(false);
                        faceRecognizeModel.setSimilar(fSimilar);
                        faceRecognizeModel.setUrl(upLoadFaceUrl);
                        result.resultCode = 0;
                        result.resultMsg = "";
                        result.data = faceRecognizeModel;
                    }
                } else {
                    result.resultCode = 1;
                    result.resultMsg = "该人尚未注册";
                    result.data = null;
                }
            } else {
                result.resultCode = 1;
                result.resultMsg = "上传失败，请重试";
                result.data = null;
            }
        }catch (Exception ex){
            result.resultCode = 1;
            result.resultMsg = ex.toString();
            result.data = null;
        }
        return result;
    }



    @UserLoginToken
    @ApiOperation(value = " 自动上报取保监居人员位置")
    @PostMapping("/autoLocation")
    public ResultSet autoLocation(@ApiParam(name = "latitude",value = "纬度")@RequestParam(required = true)float  latitude,@ApiParam(name = "longitude",value = "经度")@RequestParam(required = true)float longitude,@ApiParam(name = "locationType",value = "定位类型")@RequestParam(required = true)int locationType,@ApiParam(name = "address",value = "地址")@RequestParam(required = true)String address){

        int a = superviseService.autoLocation(latitude,longitude,locationType,address,TokenUtil.getTokenUserId(),new Date());
        if(a!=0){
            result.resultCode = 0;
            result.resultMsg = "";
            result.data = System.currentTimeMillis();
        }else{
            result.resultCode = 1;
            result.resultMsg = "上报失败";
            //result.data = "";
        }
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = " 上报保外人员定位错误信息")
    @PostMapping("/uploadLocationError")
    public ResultSet uploadLocationError( String errorCode, String errorMsg){

        int a = superviseService.uploadLocationError(errorCode,errorMsg,Integer.parseInt(TokenUtil.getTokenUserId()),new Date());
        if(a!=0) {
            result.resultCode = 0;
            result.resultMsg = "";
            result.data = new Object();
        }
        else
        {
            result.resultCode = 0;
            result.resultMsg = "";
            result.data = null;
        }
        return result;
    }
    @UserLoginToken
    @ApiOperation(value = " 上报保外人员电量信息")
    @PostMapping("/uploadBattery")
    public ResultSet uploadBattery( float percent){
        if(percent<=20.0){
            batteryAlarm(TokenUtil.getTokenUserId());
        }
        int a = superviseService.uploadBattery(percent,TokenUtil.getTokenUserId(),new Date());
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = new Object();
        return result;
    }

    public void batteryAlarm(String userId){
        String persionName =superviseService.faceRecognize(userId).get(0).getPersonname();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String nowTime = dateFormat.format( now );
        String workContent = persionName+"于"+nowTime+"手机电量低于20%，请及时与其取得联系。最后一次位置：";
        superviseService.batteryAlarm(userId,workContent);
    }

    @UserLoginToken
    @ApiOperation(value = " 获取保外人员的监管配置")
    @GetMapping("/getSuperviseConfig")
    public ResultSet  getSuperviseConfig(){
        List<LocationModel> locationModels = superviseService.getLocationConfig(TokenUtil.getTokenUserId());
        GetSuperviseConfigModel getSuperviseConfigModel =superviseService.getBatteryConfigTimestamp(TokenUtil.getTokenUserId());
        getSuperviseConfigModel.setLocation(locationModels.get(0));
       // getSuperviseConfigModel.setBatteries(locationModels.get(1));
        Battery battery =new Battery();
        battery.setEnable(locationModels.get(1).isEnable());
        battery.setTimeSpan(locationModels.get(1).getTimeSpan());
        battery.setAlarmThreshold(20.0f);
        getSuperviseConfigModel.setBattery(battery);

        result.resultCode = 0;
        result.resultMsg = "";
        result.data = getSuperviseConfigModel;
        return result;
    }
}
