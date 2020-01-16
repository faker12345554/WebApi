package com.prisonapp.business.controller.dw_supervise;


import com.common.common.Uploadfiles.Upload;
import com.common.common.result.ResultSet;
import com.prisonapp.business.entity.dw_supervise.*;
import com.prisonapp.business.entity.dw_user.UserModel;
import com.prisonapp.business.service.dw_supervise.SuperviseService;
import com.prisonapp.token.TokenUtil;
import com.prisonapp.token.tation.UserLoginToken;
import com.prisonapp.tool.AESDecode;
import com.prisonapp.tool.AddressResolutionUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import javax.servlet.http.HttpServletRequest;
import java.awt.geom.Point2D;
import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;


@Api(value = "监督controller", tags = {"监督保外人员的请假"})
@RestController
@RequestMapping("/app/supervise")
public class SuperviseController {


    @Autowired
    private SuperviseService superviseService;
    private ResultGetApplyLeaveListModel resultGetApplyLeaveListModel = new ResultGetApplyLeaveListModel();
    private ResultGetSuperviseTaskModel resultGetSuperviseTaskModel = new ResultGetSuperviseTaskModel();
    private FaceRecognizeModel faceRecognizeModel = new FaceRecognizeModel();
    private ResultSet result = new ResultSet();
    private Upload upload = new Upload();
    private HttpServletRequest request;


    @UserLoginToken
    @ApiOperation(value = "获取保外人员的外出申请列表")
    @GetMapping("/getApplyLeaveList")
    public ResultSet getApplyLeaveList(@ApiParam(name = "statusCode", value = "审批状态编号") @RequestParam(required = true) String statusCode, @ApiParam(name = "count", value = "当前已经获取的数据条数") @RequestParam(required = true) int count, @ApiParam(name = "requestCount", value = "请求获取数据的条数") @RequestParam(required = true) int requestCount) {
        List<GetApplyLeaveListModel> ApplyLeaveListModels;
        if (statusCode.equals("0")) {
            ApplyLeaveListModels = superviseService.getAllApplyLeaveList(count, requestCount, getPersonId());
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
    public ResultSet submitApplyLeave(SubmitApplyLeaveModel submitApplyLeaveModel) throws ParseException {
        String code = "qj" + System.currentTimeMillis();

//        Long longStartDate = Long.valueOf(submitApplyLeaveModel.getStartDate());//123456789
//        Long longEndDate = Long.valueOf(submitApplyLeaveModel.getEndDate());
        List<TPersoninformation> person = superviseService.getPersonname(TokenUtil.getTokenUserId());
        int res = superviseService.submitApplyLeave(submitApplyLeaveModel.getCity(),    submitApplyLeaveModel.getCityCode(),    submitApplyLeaveModel.getDistrict(),submitApplyLeaveModel.getDistrictCode(),
                                                    submitApplyLeaveModel.getProvince(),submitApplyLeaveModel.getProvinceCode(),submitApplyLeaveModel.getReason(),  submitApplyLeaveModel.getReasonAudioUrl(),
                                                    submitApplyLeaveModel.getEndDate(), submitApplyLeaveModel.getStartDate(),code,
                                                    TokenUtil.getTokenUserId(), person.get(0).getPersonname());
        if (res != 0) {
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
        if (res.equals("上传成功")) {
            result.resultCode = 0;
            result.resultMsg = "";
            result.data = "https://pardon.cnnc626.com:8443/mypicture/personApp/Audio/" + formatter.format(date) + "/" + fileName;
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
        // List<GetSuperviseTaskModel> getSuperviseTaskModel = new ArrayList<>();
        // Calendar calendar = Calendar.getInstance();
        List<GetSuperviseTaskModel> getSuperviseTaskModels = superviseService.getSuperviseTask(TokenUtil.getTokenUserId());
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
    public ResultSet faceRecognize(MultipartFile file) throws Exception {
        List<TPersoninformation> tPersoninformations = superviseService.faceRecognize(TokenUtil.getTokenUserId());
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            //保存图片的路径
            String url = System.getProperty("user.dir") + "/../webapps/mypicture/personApp/Face/" + formatter.format(date);

            File path = new File(url);
            if (!path.exists() && !path.isDirectory()) {
                path.mkdirs();
            }
            String fileName = file.getOriginalFilename();
            String res = upload.upload(url, file);
            if (res.equals("上传成功")) {
                 String upLoadFaceUrl = "https://pardon.cnnc626.com:8443/mypicture/personApp/Face/"+ formatter.format(date)+"/"+fileName;//这是真正有用的
                //if(true){
                  if (tPersoninformations != null) {
                    //将两张图片进行对比，upLoadFaceUrl为用户传进来的图片路劲，第二个为数据库中的图片路劲
                    String comparedRes = AESDecode.faceCompared(upLoadFaceUrl, tPersoninformations.get(0).getFacepath());
                    //获取json中的可信度并转换成float类型
                    JSONObject jsonObject = new JSONObject(comparedRes);
                    String similar = jsonObject.getString("confidence");
                    float fSimilar = Float.parseFloat(similar);
                    if (fSimilar >= 0.75) {
                        superviseService.insertFaceRecognize(TokenUtil.getTokenUserId(), 0, 0, upLoadFaceUrl);
                        List<FaceRecognizeModel> faceRecognizeModels = superviseService.getFaceRecognize(TokenUtil.getTokenUserId(), 0);
                        faceRecognizeModel.setCode(faceRecognizeModels.get(0).getCode());
                        faceRecognizeModel.setPassed(true);
                        faceRecognizeModel.setSimilar(fSimilar);
                        faceRecognizeModel.setUrl(upLoadFaceUrl);
                        result.resultCode = 0;
                        result.resultMsg = "";
                        result.data = faceRecognizeModel;
                    } else {
                        superviseService.insertFaceRecognize(TokenUtil.getTokenUserId(), 0, 1, upLoadFaceUrl);
                        List<FaceRecognizeModel> faceRecognizeModels = superviseService.getFaceRecognize(TokenUtil.getTokenUserId(), 0);
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
    public ResultSet autoLocation(@ApiParam(name = "latitude", value = "纬度") @RequestParam(required = true) float latitude, @ApiParam(name = "longitude", value = "经度") @RequestParam(required = true) float longitude, @ApiParam(name = "locationType", value = "定位类型") @RequestParam(required = true) int locationType, @ApiParam(name = "address", value = "地址") @RequestParam(required = true) String address) throws MalformedURLException {
       boolean fScope = getPolygon(latitude,longitude);
        if (fScope) {
          //  superviseService.updateFscope(TokenUtil.getTokenUserId(), false);

        } else {
            //生成报警内容
            String persionName = superviseService.faceRecognize(TokenUtil.getTokenUserId()).get(0).getPersonname();
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
            String nowTime = dateFormat.format(now);
            String content = persionName + "于" + nowTime + "未经批准离开限定区域，请及时与其取得联系。最后一次位置：";
         //   superviseService.updateFscope(TokenUtil.getTokenUserId(), true);
            superviseService.insertFscope(TokenUtil.getTokenUserId(), content);

        }
        int a = superviseService.autoLocation(latitude, longitude, locationType, address, TokenUtil.getTokenUserId(), new Date(),fScope);
        if (a != 0) {
            result.resultCode = 0;
            result.resultMsg = "";
            result.data = System.currentTimeMillis();
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

        int a = superviseService.uploadLocationError(errorCode, errorMsg, Integer.parseInt(TokenUtil.getTokenUserId()), new Date());
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
    public ResultSet uploadBattery(float percent) {
        if (percent <= 20.0) {
            batteryAlarm(TokenUtil.getTokenUserId());
        }
        int a = superviseService.uploadBattery(percent, TokenUtil.getTokenUserId(), new Date());
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
    public ResultSet getSuperviseConfig() {
        TRemindersettings tRemindersettings = superviseService.getLocationConfig();//获取待办提醒的所有数据
        //取出定位的数据
        LocationModel locationModels =new LocationModel();
        locationModels.setEnable(tRemindersettings.isStatus());
        locationModels.setTimeSpan(tRemindersettings.getSettingday());
        //设置电量的数据
        Battery battery = new Battery();
        battery.setEnable(true);
        battery.setTimeSpan("20");
        battery.setAlarmThreshold(20.0f);
        GetSuperviseConfigModel getSuperviseConfigModel = new GetSuperviseConfigModel(); // = superviseService.getBatteryConfigTimestamp(TokenUtil.getTokenUserId());
        getSuperviseConfigModel.setLocation(locationModels);
        getSuperviseConfigModel.setBattery(battery);
        //最后时间
        getSuperviseConfigModel.setLastEditTime(tRemindersettings.getCreatetime());
        result.resultCode = 0;
        result.resultMsg = "";
        result.data = getSuperviseConfigModel;
        return result;
    }

    @UserLoginToken
    @ApiOperation(value = " 判断是否越界")
    @GetMapping("/getPolygon")
    public  boolean getPolygon(@ApiParam(name = "latitude", value = "纬度") float latitude, @ApiParam(name = "longitude", value = "经度") float longitude) throws MalformedURLException {
        boolean fScope =false;
        try {
            //点的转型
            BigDecimal bLatitude = new BigDecimal(String.valueOf(latitude));
            double dLatitude = bLatitude.doubleValue();
            BigDecimal bLongitude = new BigDecimal(String.valueOf(longitude));
            double dLongitude = bLongitude.doubleValue();
            Point2D.Double point = new Point2D.Double(dLatitude, dLongitude);
            //画区域
            List<Point2D.Double> polygon = new ArrayList<Point2D.Double>();
            TEnclosure tEnclosure = superviseService.getPolygon(TokenUtil.getTokenUserId());
            if (tEnclosure.getAreaarr() == null || "".equals(tEnclosure.getAreaarr())) {//数据库中没有坐标，只有地方名的情况
                String path = "https://restapi.amap.com/v3/config/district?key=f0bc84013740494ba5c697ce6b707606&keywords=" + tEnclosure.getAreaname() + "&subdistrict=0&extensions=all";
                net.sf.json.JSONObject josnResult = AddressResolutionUtil.getHttps(path);//高德api返回的结果集
                JSONArray jsonArray = josnResult.getJSONArray("districts");
                String coordinates = "";
                for (int i = 0; i < jsonArray.size(); i++) {
                    net.sf.json.JSONObject obj = jsonArray.getJSONObject(i);
                    coordinates = obj.getString("polyline").replace('|',';');//边界坐标
                }
                String[] coordinatesArray = coordinates.split(";");
                for (int j = 0; j < coordinatesArray.length; j++) {
                    String[] everyCoordinates = coordinatesArray[j].split(",");
                    double polygonPoint_x = Double.parseDouble(everyCoordinates[0]);
                    double polygonPoint_y = Double.parseDouble(everyCoordinates[1]);
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
                superviseService.updateFscope(TokenUtil.getTokenUserId(), false);
                result.resultCode = 0;
                result.resultMsg = "没有越界";
                result.data = new Object();
            } else {
                //生成报警内容
                String persionName = superviseService.faceRecognize(TokenUtil.getTokenUserId()).get(0).getPersonname();
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
                String nowTime = dateFormat.format(now);
                String content = persionName + "于" + nowTime + "未经批准离开限定区域，请及时与其取得联系。最后一次位置：";

                superviseService.updateFscope(TokenUtil.getTokenUserId(), true);
                superviseService.insertFscope(TokenUtil.getTokenUserId(), content);
                result.resultCode = 0;
                result.resultMsg = "越界了";
                result.data = new Object();
            }*/
        }catch (Exception ex){
            throw ex;
        }
        return fScope;
    }

    /**
     * Param1:点的位置（经纬度）
     * Param2：区域的边界点
     * 该方法用于查看是否越界
     * */
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

//    public static void getPolygon() {
//        String keyName = "****************";//这里是key名称
//        String keyCode = "***************************";//这个是秘钥
//        String admAddress = "https://restapi.amap.com/v3/config/district?key=f0bc84013740494ba5c697ce6b707606&keywords=广东省&subdistrict=0&extensions=all";
////        Map<String, Object> params = new HashMap<>();
////        params.put("f0bc84013740494ba5c697ce6b707606", keyCode);
////        params.put("keywords", "安徽");
////        params.put("subdistrict", 1);
////        params.put("extensions", "base");
//        String result = HttpClientUtil.doGet(admAddress);
//        System.out.println("result");
//
//    }

    public  String getPersonId(){

        TPersoninformation tPersoninformation = superviseService.RelatedId(TokenUtil.getTokenUserId());//根据user中的手机号去取出personid
        String personid = tPersoninformation.getPersonid();
        return personid;
    }
}
