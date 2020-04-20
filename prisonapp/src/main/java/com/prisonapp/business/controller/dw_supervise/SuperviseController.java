package com.prisonapp.business.controller.dw_supervise;


import com.common.common.Uploadfiles.Upload;
import com.common.common.apppush.Demo;
import com.common.common.result.ResultSet;
import com.google.common.collect.ObjectArrays;
import com.prisonapp.business.controller.dw_voice.VoicePrintApi;
import com.prisonapp.business.entity.dw_supervise.*;
import com.prisonapp.business.entity.dw_user.UserModel;
import com.prisonapp.business.service.dw_supervise.SuperviseService;
import com.prisonapp.business.util.PersonInformationUtil;
import com.prisonapp.business.util.getServerPath;
import com.prisonapp.token.TokenUtil;
import com.prisonapp.token.tation.UserLoginToken;
import com.prisonapp.tool.AESDecode;
import com.prisonapp.tool.AddressResolutionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javafx.beans.binding.ObjectBinding;
import net.sf.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import java.net.MalformedURLException;
import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import java.awt.geom.Point2D;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Api(value = "监督controller", tags = {"监督保外人员的请假"})
@RestController
@RequestMapping("/app/supervise")
public class SuperviseController {

//    @Autowired
//    private PersonInformationUtil personInformationUtil;

    @Autowired
    private SuperviseService superviseService;
    private ResultGetApplyLeaveListModel resultGetApplyLeaveListModel = new ResultGetApplyLeaveListModel();
    private ResultGetSuperviseTaskModel resultGetSuperviseTaskModel = new ResultGetSuperviseTaskModel();
    private Timestamp timestamp = new Timestamp();
    private Code code = new Code();
    private FaceRecognizeModel faceRecognizeModel = new FaceRecognizeModel();

    private Upload upload = new Upload();


    @UserLoginToken
    @ApiOperation(value = "获取保外人员的外出申请列表")
    @GetMapping("/getApplyLeaveList")
    public ResultSet getApplyLeaveList(@ApiParam(name = "statusCode", value = "审批状态编号") @RequestParam(required = true) String statusCode, @ApiParam(name = "count", value = "当前已经获取的数据条数") @RequestParam(required = true) int count, @ApiParam(name = "requestCount", value = "请求获取数据的条数") @RequestParam(required = true) int requestCount) {
        ResultSet result = new ResultSet();
        List<GetApplyLeaveListModel> ApplyLeaveListModels;
        if (statusCode.equals("0")) {
            ApplyLeaveListModels = superviseService.getAllApplyLeaveList(count, requestCount, getPersonId());
        } else {
            ApplyLeaveListModels = superviseService.getApplyLeaveList(statusCode, count, requestCount, getPersonId());
        }
        if (ApplyLeaveListModels != null && !"".equals(ApplyLeaveListModels) && !"null".equals(ApplyLeaveListModels)) {

            int totalCount = (superviseService.getTotalApplyLeaveList(getPersonId())).size();
            for (GetApplyLeaveListModel item : ApplyLeaveListModels) {
                List<ApplyRecordModel> applyRecords = superviseService.applyRecord(item.getCode());//取出请假申请
                Long End = Long.parseLong(item.getEndTimestamp());
                Long Start = Long.parseLong(item.getStartTimestamp());
                int days = (int) ((End - Start) / 86400000) + 1;
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
    public ResultSet submitApplyLeave(@RequestBody SubmitApplyLeaveModel submitApplyLeaveModel) throws Exception {
        ResultSet result = new ResultSet();
        String strCode = "qj" + System.currentTimeMillis();
        code.setCode(strCode);
        TEnum tEnum = superviseService.getReview();//取出‘待审核’
        List<TPersoninformation> person = superviseService.getPersonname(getPersonId());
        LocalDate date = LocalDate.now(); // get the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String messageContent = person.get(0).getPersonname() + "于" + date.format(formatter) + "日提交的外出申请待审批，请及时处理。";
        String address = submitApplyLeaveModel.getProvince() + submitApplyLeaveModel.getCity() + submitApplyLeaveModel.getDistrict();
        int res = superviseService.submitApplyLeave(submitApplyLeaveModel.getCity(), submitApplyLeaveModel.getCityCode(), submitApplyLeaveModel.getDistrict(), submitApplyLeaveModel.getDistrictCode(),
                submitApplyLeaveModel.getProvince(), submitApplyLeaveModel.getProvinceCode(), submitApplyLeaveModel.getReason(), submitApplyLeaveModel.getReasonAudioUrl(),
                submitApplyLeaveModel.getEndDate(), submitApplyLeaveModel.getStartDate(), strCode,
                getPersonId(), person.get(0).getPersonname(), person.get(0).getSponsoralarm(), address, tEnum.getEnumname(), messageContent);
        if (res != 0) {
            appPush(person.get(0).getSponsoralarm(), "【外出审批通知】", messageContent);
            result.resultCode = 0;
            result.resultMsg = "";
            result.data = code;
        } else {
            result.resultCode = 0;
            result.resultMsg = "提交失败";
            result.data = null;
        }

        return result;
    }

    @UserLoginToken
    @ApiOperation(value = "上传录音文件")
    @PostMapping("/uploadAudio")
    public ResultSet uploadAudio(MultipartFile file) {
        ResultSet result = new ResultSet();
        UploadAudioUrl uploadAudioUrl = new UploadAudioUrl();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        //SSLEngine request = null;
        // String x=request.getSession(true).getServletContext().getRealPath(File.separator+"upload");
        String url = System.getProperty("user.dir") + "/../webapps/mypicture/personApp/Audio/" + formatter.format(date);

        File path = new File(url);
        if (!path.exists() && !path.isDirectory()) {
            path.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        String res = upload.upload(url, file);
        uploadAudioUrl.setUrl(getServerPath.ServerPath() + "Audio/" + formatter.format(date) + "/" + fileName);
        if (res.equals("上传成功")) {
            result.resultCode = 0;
            result.resultMsg = "";
            result.data = uploadAudioUrl;
        } else {
            result.resultCode = 1;
            result.resultMsg = "上传失败";
            result.data = null;
        }
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = "获取保外人员的执行任务")
    @GetMapping("/getSuperviseTask")
    public ResultSet getSuperviseTask() {
        ResultSet result = new ResultSet();
        // List<GetSuperviseTaskModel> getSuperviseTaskModel = new ArrayList<>();
        // Calendar calendar = Calendar.getInstance();
        List<GetSuperviseTaskModel> getSuperviseTaskModels = superviseService.getSuperviseTask(getPersonId());
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
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = resultGetSuperviseTaskModel;
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = "保外人员人脸识别签到")
    @PostMapping("/faceRecognize")
    public ResultSet faceRecognize(MultipartFile face) throws Exception {
        ResultSet result = new ResultSet();
        List<TPersoninformation> tPersoninformations = superviseService.faceRecognize(getPersonId());
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            //保存图片的路径
            String url = System.getProperty("user.dir") + "/../webapps/mypicture/personApp/Face/" + formatter.format(date);

            File path = new File(url);
            if (!path.exists() && !path.isDirectory()) {
                path.mkdirs();
            }
            String fileName = face.getOriginalFilename();
            String res = upload.upload(url, face);
            if (res.equals("上传成功")) {
                String upLoadFaceUrl = getServerPath.ServerPath() + "Face/" + formatter.format(date) + "/" + fileName;//这是真正有用的
                //if(true){
                if (tPersoninformations != null) {
                    //将两张图片进行对比，upLoadFaceUrl为用户传进来的图片路劲，第二个为数据库中的图片路劲
                    String comparedRes = AESDecode.faceCompared(upLoadFaceUrl, tPersoninformations.get(0).getFacepath());
                    //获取json中的可信度并转换成float类型
                    JSONObject jsonObject = new JSONObject(comparedRes);
                    String similar = jsonObject.getString("confidence");
                    float num = Float.parseFloat(similar);
                    float fSimilar = (float) (Math.round(num * 100)) / 100;
                    if (fSimilar >= 75.00) {
                        TEnum tEnum = superviseService.getQDLX("QDLX-001", "1");
                        superviseService.insertFaceRecognize(getPersonId(), 1, 0, upLoadFaceUrl, tEnum.getEnumname());
                        List<FaceRecognizeModel> faceRecognizeModels = superviseService.getFaceRecognize(getPersonId(), 1);
                        faceRecognizeModel.setCode(faceRecognizeModels.get(0).getCode());
                        faceRecognizeModel.setPassed(true);
                        faceRecognizeModel.setSimilar(fSimilar);
                        faceRecognizeModel.setUrl(upLoadFaceUrl);
                        result.resultCode = 0;
                        result.resultMsg = "";
                        result.data = faceRecognizeModel;
                    } else {
                        TEnum tEnum = superviseService.getQDLX("QDLX-001", "1");
                        superviseService.insertFaceRecognize(getPersonId(), 1, 1, upLoadFaceUrl, tEnum.getEnumname());
                        List<FaceRecognizeModel> faceRecognizeModels = superviseService.getFaceRecognize(getPersonId(), 1);
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
        } catch (Exception ex) {
            result.resultCode = 1;
            result.resultMsg = ex.toString();
            result.data = null;
        }
        return result;
    }


    @UserLoginToken
    @ApiOperation(value = " 自动上报取保监居人员位置")
    @PostMapping("/autoLocation")
    public ResultSet autoLocation(@ApiParam(name = "latitude", value = "纬度") @RequestParam(required = true) float latitude, @ApiParam(name = "longitude", value = "经度") @RequestParam(required = true) float longitude, @ApiParam(name = "locationType", value = "定位类型") @RequestParam(required = true) int locationType, @ApiParam(name = "address", value = "地址") @RequestParam(required = true) String address) throws Exception {
        ResultSet result = new ResultSet();
        boolean fScope = getPolygon(latitude, longitude);
        if (fScope) {
            //  superviseService.updateFscope(getPersonId(), false);

        } else {
            //生成报警内容
            List<TPersoninformation> tPersoninformations = superviseService.faceRecognize(getPersonId());
            String persionName = tPersoninformations.get(0).getPersonname();
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
            String nowTime = dateFormat.format(now);
            String content = persionName + "于" + nowTime + "未经批准离开限定区域，请及时与其取得联系。最后一次位置：" + address;
            String pushContent = persionName + "未经批准脱离管控区域，可前往查看详细违规记录，并根据规定对其采取措施。最后一次位置：" + address + "更新时间:" + nowTime;
            appPush(tPersoninformations.get(0).getSponsoralarm(), "【脱离管控区域报警通知】", pushContent);
            //   superviseService.updateFscope(getPersonId(), true);
            superviseService.insertFscope(getPersonId(), content);

        }
        int a = superviseService.autoLocation(latitude, longitude, locationType, address, getPersonId(), new Date(), !fScope);
        if (a != 0) {
            timestamp.setTimestamp(System.currentTimeMillis());
            result.resultCode = 0;
            result.resultMsg = "";
            result.data = timestamp;
        } else {
            result.resultCode = 1;
            result.resultMsg = "上报失败";
            result.data = new Object();
        }
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = " 上报保外人员定位错误信息")
    @PostMapping("/uploadLocationError")
    public ResultSet uploadLocationError(String errorCode, String errorMsg) {
        ResultSet result = new ResultSet();
        UserModel userModel = superviseService.getUserId(getPersonId());//根据personid查出对应user表中的id（整形）以便放入操作日志
        int a = superviseService.uploadLocationError(errorCode, errorMsg, (int) userModel.getId(), new Date());
        if (a != 0) {
            result.resultCode = 0;
            result.resultMsg = "";
            result.data = new Object();
        } else {
            result.resultCode = 0;
            result.resultMsg = "";
            result.data = null;
        }
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = " 上报保外人员电量信息")
    @PostMapping("/uploadBattery")
    public ResultSet uploadBattery(float percent) throws Exception {
        ResultSet result = new ResultSet();
        if (percent <= 20.0) {
            batteryAlarm(getPersonId());
            List<TPersoninformation> tPersoninformations = superviseService.faceRecognize(getPersonId());
            String persionName = tPersoninformations.get(0).getPersonname();
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
            String nowTime = dateFormat.format(now);
            String content = persionName + nowTime + "手机电量低于20%，请及时与其取得联系。";
            appPush(tPersoninformations.get(0).getSponsoralarm(), "【低电量报警通知】", content);
        }
        int a = superviseService.uploadBattery(percent, getPersonId(), new Date());
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = new Object();
        return result;
    }

    public void batteryAlarm(String userId) {
        String persionName = superviseService.faceRecognize(userId).get(0).getPersonname();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String nowTime = dateFormat.format(now);
        String content = persionName + "于" + nowTime + "手机电量低于20%，请及时与其取得联系。最后一次位置：";
        superviseService.batteryAlarm(userId, content);
    }

    @UserLoginToken
    @ApiOperation(value = " 获取保外人员的监管配置")
    @GetMapping("/getSuperviseConfig")
    public ResultSet getSuperviseConfig() throws ParseException {
        ResultSet result = new ResultSet();
        TRemindersettings tRemindersettings = superviseService.getLocationConfigTime();//获取待办提醒的所有数据

        //取出定位的数据
        TPrisonsetting tPrisonsettingLocation = superviseService.getLocationConfig(getPersonId(), 1);
        LocationModel locationModels = new LocationModel();
        if(tPrisonsettingLocation==null||tPrisonsettingLocation.equals("")){
            locationModels.setEnable(false);
        }else{
            locationModels.setEnable(tPrisonsettingLocation.isSettingcheck());
        }
        locationModels.setTimeSpan(Integer.parseInt(tRemindersettings.getSettingday()));

        //设置电量的数据
        TPrisonsetting tPrisonsettingBattery = superviseService.getLocationConfig(getPersonId(), 4);
        Battery battery = new Battery();
        if(tPrisonsettingBattery==null||tPrisonsettingBattery.equals("")){
            battery.setEnable(false);
        }else{
            battery.setEnable(tPrisonsettingBattery.isSettingcheck());
        }
        battery.setTimeSpan("20");
        battery.setAlarmThreshold(20.0f);
        GetSuperviseConfigModel getSuperviseConfigModel = new GetSuperviseConfigModel(); // = superviseService.getBatteryConfigTimestamp(getPersonId());
        getSuperviseConfigModel.setLocation(locationModels);
        getSuperviseConfigModel.setBattery(battery);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //最后时间
        if(tPrisonsettingLocation!=null&&tPrisonsettingBattery!=null){

            Date locationDate = sdf.parse(tPrisonsettingLocation.getSettingtime());
            Date batteryDate = sdf.parse(tPrisonsettingBattery.getSettingtime());
            if (locationDate.getTime() > batteryDate.getTime()) {
                getSuperviseConfigModel.setLastEditTime(locationDate);
            } else {
                getSuperviseConfigModel.setLastEditTime(batteryDate);
            }
        }
        else{
            Date date=sdf.parse("1970-01-01 08:00:00");
            getSuperviseConfigModel.setLastEditTime(date);
        }
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = getSuperviseConfigModel;
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = " 判断是否越界")
    @GetMapping("/getPolygon")
    public boolean getPolygon(@ApiParam(name = "latitude", value = "纬度") float latitude, @ApiParam(name = "longitude", value = "经度") float longitude) throws MalformedURLException {
        boolean fScope = false;
        try {
            //点的转型
            BigDecimal bLatitude = new BigDecimal(String.valueOf(latitude));
            double dLatitude = bLatitude.doubleValue();
            BigDecimal bLongitude = new BigDecimal(String.valueOf(longitude));
            double dLongitude = bLongitude.doubleValue();
            Point2D.Double point = new Point2D.Double(dLongitude, dLatitude);
            //画区域
            List<Point2D.Double> polygon = new ArrayList<Point2D.Double>();
            TEnclosure tEnclosure = superviseService.getPolygon(getPersonId());
            if (tEnclosure == null) {
                return true;
            }
            if (tEnclosure.getAreaarr() == null || "".equals(tEnclosure.getAreaarr())) {//数据库中没有坐标，只有地方名的情况
                String path = "https://restapi.amap.com/v3/config/district?key=f0bc84013740494ba5c697ce6b707606&keywords=" + tEnclosure.getAreaname() + "&subdistrict=0&extensions=all";
                net.sf.json.JSONObject josnResult = AddressResolutionUtil.getHttps(path);//高德api返回的结果集
                JSONArray jsonArray = josnResult.getJSONArray("districts");
                String coordinates = "";
                for (int i = 0; i < jsonArray.size(); i++) {
                    net.sf.json.JSONObject obj = jsonArray.getJSONObject(i);
                    coordinates = obj.getString("polyline").replace('|', ';');//边界坐标
                }
                String[] coordinatesArray = coordinates.split(";");
                for (int j = 0; j < coordinatesArray.length; j++) {
                    String[] everyCoordinates = coordinatesArray[j].split(",");
                    double polygonPoint_x = Double.parseDouble(everyCoordinates[0]);//113.512245经度
                    double polygonPoint_y = Double.parseDouble(everyCoordinates[1]);//23.652155纬度
                    Point2D.Double polygonPoint = new Point2D.Double(polygonPoint_x, polygonPoint_y);
                    polygon.add(polygonPoint);
                }
            } else {
                //数据库中存有坐标的情况
                String str = tEnclosure.getAreaarr();
                String[] strArray = str.split(",");
                for (int i = 0; i < strArray.length; i += 2) {
                    double polygonPoint_x = Double.parseDouble(strArray[i]);
                    double polygonPoint_y = Double.parseDouble(strArray[i + 1]);
                    Point2D.Double polygonPoint = new Point2D.Double(polygonPoint_x, polygonPoint_y);
                    polygon.add(polygonPoint);
                }
            }
            fScope = checkWithJdkGeneralPath(point, polygon);//点是否在区域内，则返回true时不越界，反之越界
   /*         if (a) {
                superviseService.updateFscope(getPersonId(), false);
                result.resultCode = 0;
                result.resultMsg = "没有越界";
                result.data = new Object();
            } else {
                //生成报警内容
                String persionName = superviseService.faceRecognize(getPersonId()).get(0).getPersonname();
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
                String nowTime = dateFormat.format(now);
                String content = persionName + "于" + nowTime + "未经批准离开限定区域，请及时与其取得联系。最后一次位置：";

                superviseService.updateFscope(getPersonId(), true);
                superviseService.insertFscope(getPersonId(), content);
                result.resultCode = 0;
                result.resultMsg = "越界了";
                result.data = new Object();
            }*/
        } catch (Exception ex) {
            throw ex;
        }
        return fScope;
    }

    /**
     * Param1:点的位置（经纬度）
     * Param2：区域的边界点
     * 该方法用于查看是否越界
     */
    public static boolean checkWithJdkGeneralPath(Point2D.Double point, List<Point2D.Double> polygon) {
        java.awt.geom.GeneralPath p = new java.awt.geom.GeneralPath();
        Point2D.Double first = polygon.get(0);
        p.moveTo(first.x, first.y);
        polygon.remove(0);
        for (Point2D.Double d : polygon) {
            p.lineTo(d.x, d.y);
        }
        p.lineTo(first.x, first.y);
        p.closePath();
        return p.contains(point);
    }

    @UserLoginToken
    @ApiOperation(value = " 获取语音签到识别串")
    @GetMapping("/generateVoiceSignNum")
    public ResultSet generateVoiceSignNum() {
        ResultSet resultSet = new ResultSet();
        StringBuilder signCode = new StringBuilder("sw");
        StringBuilder number = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            signCode.append(random.nextInt(10));
            if (i < 8) {
                number.append(random.nextInt(10));
            }
        }
        VoiceModel voiceModel = new VoiceModel();
        voiceModel.setNumber(number.toString());
        voiceModel.setSignCode(signCode.toString());
        resultSet.resultCode = 0;
        resultSet.resultMsg = "";
        resultSet.data = voiceModel;
        return resultSet;

    }

    @UserLoginToken
    @ApiOperation(value = " 保外语音签到识别（声纹识别）")
    @PostMapping("/voiceRecognize")
    public ResultSet voiceRecognize(MultipartFile voice, String signCode) throws Exception {
        ResultSet resultSet = new ResultSet();
        ArrayList<String> arrayList = new ArrayList<>();
        String filePath = "";
        String pathOfLocal = "";
        Boolean find = false;//是否存在声纹

        VoicePrintApi obj = new VoicePrintApi("203793248", "qhntfvf4x2a582059ji1z9vzlpr9r2cu");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        //将文件上传到服务器

        String url = System.getProperty("user.dir") + "/../webapps/mypicture/personApp/Audio/" + formatter.format(date);

        File path = new File(url);
        if (!path.exists() && !path.isDirectory()) {
            path.mkdirs();
        }
        String fileName = voice.getOriginalFilename();
        String uploadRes = upload.upload(url, voice);
        if (uploadRes.equals("上传成功")) {
            filePath=getServerPath.ServerPath()+"Audio/" + formatter.format(date) + "/" + fileName;//正式的语句

//            filePath = "https://pardon.cnnc626.com:8443/mypicture/personApp/Audio/2020-03-27/tjh123456.wav";//正式环境改动此处
            List<TPersoninformation> tPersoninformations = superviseService.faceRecognize(getPersonId());
//            obj.getAccess();
            List<TVoice> voices = superviseService.getVoiceToken();
            obj.setToken(voices.get(0).getUserid(), voices.get(0).getAccess_token());
            //调用阿里云的接口，获得fileid
            String fileIdStr = obj.uploadFile("voiceFile", filePath, 300000000);
            String fileId = "";
            if (Integer.parseInt(net.sf.json.JSONObject.fromObject(fileIdStr).get("statusCode").toString()) == 401) {
                List<String> list = obj.getAccess();//userid,token
                superviseService.updateVoiceToken(list.get(0), list.get(1));
                fileIdStr = obj.uploadFile("voiceFile", filePath, 300000000);
                fileId = net.sf.json.JSONObject.fromObject(net.sf.json.JSONObject.fromObject(net.sf.json.JSONObject.fromObject(fileIdStr).get("body")).get("data")).get("file_id").toString();
            } else {
                fileId = net.sf.json.JSONObject.fromObject(net.sf.json.JSONObject.fromObject(net.sf.json.JSONObject.fromObject(fileIdStr).get("body")).get("data")).get("file_id").toString();
            }

/*
        String voicePrintList = obj.getVoicePrintList("758001e8-e36b-4539-aaf7-4697b9a767c0", 0, 0);
            //取出声纹列表中的声纹id
           // String a ="{\"body\":\"{\\\"data\\\":[{\\\"vp_store_id\\\":\\\"a58dc17b-92c6-4300-aef3-3c4b95f98aac\\\",\\\"voice_print_id\\\":\\\"tangjihao1\\\"}],\\\"has_error\\\":false,\\\"error_message\\\":\\\"ok\\\",\\\"error_code\\\":0,\\\"request_id\\\":\\\"bafc093c-6817-11ea-b608-00163e02dfac\\\"}\",\"contentType\":\"application/json; charset=utf-8\",\"headers\":{\"Server\":\"Tengine\",\"Access-Control-Allow-Origin\":\"*\",\"Access-Control-Allow-Methods\":\"GET,POST,PUT,DELETE,HEAD,OPTIONS,PATCH\",\"Connection\":\"keep-alive\",\"Content-Length\":\"200\",\"Access-Control-Max-Age\":\"172800\",\"X-Ca-Request-Id\":\"6F3DE2CC-B334-4958-B7AB-BEC947C1F2BF\",\"Date\":\"Tue, 17 Mar 2020 06:22:51 GMT\",\"Access-Control-Allow-Headers\":\"X-Requested-With,X-Sequence,X-Ca-Key,X-Ca-Secret,X-Ca-Version,X-Ca-Timestamp,X-Ca-Nonce,X-Ca-API-Key,X-Ca-Stage,X-Ca-Client-DeviceId,X-Ca-Client-AppId,X-Ca-Signature,X-Ca-Signature-Headers,X-Ca-Signature-Method,X-Forwarded-For,X-Ca-Date,X-Ca-Request-Mode,Authorization,Content-Type,Accept,Accept-Ranges,Cache-Control,Range,Content-MD5\",\"Content-Type\":\"application/json; charset=utf-8\"},\"requestId\":\"6F3DE2CC-B334-4958-B7AB-BEC947C1F2BF\",\"statusCode\":200}";

            net.sf.json.JSONObject jVoicePrintList =net.sf.json.JSONObject.fromObject(voicePrintList);

            Object bodyOfList =jVoicePrintList.get("body");
            net.sf.json.JSONObject jsonBodyOfList =net.sf.json.JSONObject.fromObject(bodyOfList);
            if(Integer.parseInt(jsonBodyOfList.get("error_code").toString())==0){
                List<TPersoninformation> tPersoninformations= superviseService.faceRecognize(getPersonId());
                Object dataArrays =jsonBodyOfList.get("data");
                JSONArray jDataArrays =JSONArray.fromObject(dataArrays);
                for(int i=0;i<jDataArrays.size();i++){
                    Object OVoice_print_id =jDataArrays.get(i);
                    net.sf.json.JSONObject jVoice_print_id =net.sf.json.JSONObject.fromObject(OVoice_print_id);
                    String voice_print_id =jVoice_print_id.get("voice_print_id").toString();
                    if(voice_print_id.equals(account)){//有用的语句

                        arrayList.add(account);
                        find =true;
                    }
//                    if(voice_print_id.equals("SW12345670")){//正式环境改动此处
//
//                        arrayList.add("SW12345670");//正式环境改动此处
//                        find =true;
//                    }

                }*/


            //判断是否已注册
            if (tPersoninformations.get(0).getHasvoice()) {
                String[] vpIds = {tPersoninformations.get(0).getContact()};

                String compareResultStr = obj.compareVoicePrint("758001e8-e36b-4539-aaf7-4697b9a767c0", fileId, vpIds);
                net.sf.json.JSONObject compareResult = net.sf.json.JSONObject.fromObject(compareResultStr);
                Object compareResultBody = compareResult.get("body");
                net.sf.json.JSONObject JsonCompareResultBody = net.sf.json.JSONObject.fromObject(compareResultBody);
                if (JsonCompareResultBody.get("error_message").toString().equals("ok")) {
                    Object dataOfCompare = JsonCompareResultBody.get("data");
                    JSONArray dataOfCompareArray = JSONArray.fromObject(dataOfCompare);
                    VoiceRecognizeModel voiceRecognizeModel = new VoiceRecognizeModel();
                    //生成8位随机数作为本次识别编号
                    StringBuilder number = new StringBuilder();
                    Random random = new Random();
                    for (int i = 0; i < 8; i++) {
                        number.append(random.nextInt(10));
                    }
                    voiceRecognizeModel.setCode(number.toString());
                    for (int i = 0; i < dataOfCompareArray.size(); i++) {
                        Object sooreObject = dataOfCompareArray.get(i);
                        net.sf.json.JSONObject sorceJson = net.sf.json.JSONObject.fromObject(sooreObject);
                        voiceRecognizeModel.setScore(Float.valueOf(sorceJson.get("score").toString()));
                        if (Float.valueOf(sorceJson.get("score").toString()) > 0) {
                            voiceRecognizeModel.setPassed(true);
                            superviseService.insertVoice(getPersonId(), 2, 0, filePath, "语音签到");//正式环境改动此处

                        } else {
                            voiceRecognizeModel.setPassed(false);
                            superviseService.insertVoice(getPersonId(), 2, 1, filePath, "语音签到");//正式环境改动此处
                        }
                        voiceRecognizeModel.setUrl(filePath);

                    }

                    resultSet.resultCode = 0;
                    resultSet.resultMsg = "";
                    resultSet.data = voiceRecognizeModel;
                } else {

                    resultSet.resultCode = Integer.parseInt(JsonCompareResultBody.get("error_code").toString());
                    resultSet.resultMsg = JsonCompareResultBody.get("error_message").toString();//声纹验证失败
                    resultSet.data = null;
                }

            } else {
                resultSet.resultCode = 1;
                resultSet.resultMsg = "请先注册";
                resultSet.data = null;
            }

        } else {
            resultSet.resultCode = 1;
            resultSet.resultMsg = "文件上传至服务器失败";
            resultSet.data = null;
        }


        return resultSet;
    }


    @Configuration
    public class MultipartConfig {

        @Bean
        public MultipartConfigElement multipartConfigElement(@Value("${multipart.maxFileSize}") String maxFileSize, @Value("${multipart.maxRequestSize}") String maxRequestSize) {
            MultipartConfigFactory factory = new MultipartConfigFactory();
            factory.setMaxFileSize(DataSize.parse(maxFileSize));
            factory.setMaxRequestSize(DataSize.parse(maxRequestSize));
            return factory.createMultipartConfig();
        }
    }


    public void appPush(String recipientid, String tital, String content) throws Exception {
        ResultSet result = new ResultSet();
        superviseService.appPush(recipientid, tital, content);
        return;
    }

    public String getPersonId() {
//       String personid = personInformationUtil.getPersonId();

        TPersoninformation tPersoninformation = superviseService.RelatedId(TokenUtil.getTokenUserId());//根据user中的手机号去取出personid
        String personid = tPersoninformation.getPersonid();
        return personid;
    }
}
