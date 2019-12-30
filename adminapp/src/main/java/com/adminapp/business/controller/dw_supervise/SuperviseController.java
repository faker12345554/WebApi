package com.adminapp.business.controller.dw_supervise;

import com.adminapp.business.entity.dw_supervise.*;
import com.adminapp.business.service.dw_supervise.SuperviseService;
import com.adminapp.config.CacheUtils;
import com.adminapp.model.dw_supervise.*;
import com.common.common.result.ResultSet;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/app/admin/supervise")
public class SuperviseController {

    @Autowired
    private SuperviseService superviseService;

    private ResultSet rs=new ResultSet();

    @ApiOperation(value = "获取保外人员列表")
    @GetMapping("/getAgainstRule")
    public ResultSet getAgainstRule(@ApiParam(name = "type",value = "类别") @RequestParam(required = true)int type,@ApiParam(name = "count",value = "当前已经获取的数据条数") @RequestParam(required = true)int count,
                                    @ApiParam(name = "requestCount",value = "请求获取数据的条数") @RequestParam(required = true)int requestCount,@ApiParam(name = "key",value = "搜索关键字") @RequestParam(required = false)String key) {
        List<Personinformation> newPerson = new ArrayList<>();  //符合条件所有监居人员列表
        List<Personinformation> newRecentPerson = new ArrayList<>();  //符合条件的最近新增人员列表
        List<Personinformation> newBailoutPerson = new ArrayList<>(); //符合条件的取保候审人员列表
        List<Personinformation> newBailoutWatchPerson = new ArrayList<>(); //符合条件的监视居住人员列表
        String userId = CacheUtils.get("UserId").toString();        //获取工作人员id
        List<Personinformation> personinformation = superviseService.listPersonInformation(userId);  //获取该工作人员负责的所有监居人员列表
        SuperviseReturnModel superviseReturnModel = new SuperviseReturnModel();
        int totalCount =0;

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);   //设置当前时间
        long time1 = cal.getTimeInMillis();
        cal.add(Calendar.MONTH, -1);  //在当前时间基础上减一个月
        Date lastDate = cal.getTime();    //取得上一个月日期
        List<Personinformation> recentPerson = new ArrayList<>();
        for (Personinformation item : personinformation) {
            Date bailoutbegindate = item.getExecStartDate();
            if (lastDate.getTime() < bailoutbegindate.getTime()) {    //用监居开始时间与一月前时间比较，大于则为新增人员
                recentPerson.add(item);
            }
        }long time2 = cal.getTimeInMillis();
        long days = (time2 - time1) / (1000 * 60 * 60 * 24);

        List<Personinformation> bailoutPerson = new ArrayList<>();
        for (Personinformation item : personinformation) {
            String suspectStatus = item.getState();
            if (suspectStatus.equals("取保候审")) {
                bailoutPerson.add(item);
            }
        }

        List<Personinformation> bailoutWatchPerson = new ArrayList<>();
        for (Personinformation item : personinformation) {
            String suspectStatus = item.getState();
            if (suspectStatus.equals("监视居住")) {
                bailoutWatchPerson.add(item);
            }
        }

        int faceSingin = 0;      //人脸签到违规次数
        int voiceSingin = 0;     //声纹签到违规次数
        int locationViolateCount = 0;   //位置违规次数
        int faceSinginTimes = 0;    //上报设置中人脸签到的次数
        int voiceSinginTimes = 0;   //上报设置中声纹签到的次数
        List<ReportSettingsInformation> reportSettingsInformations = superviseService.listReportSetting();  //获取上报设置列表
        for (int a = 0; a < reportSettingsInformations.size(); a++) {
            ReportSettingsInformation reportSettingsInformation = reportSettingsInformations.get(a);
            if (reportSettingsInformation.getType() == 2) {
                faceSinginTimes = reportSettingsInformation.getReportcount();   //获取上报设置中人脸签到的次数
            }
            if (reportSettingsInformation.getType() == 3) {
                voiceSinginTimes = reportSettingsInformation.getReportcount();  //获取上报设置中声纹签到的次数
            }
        }
        for (int i = 0; i < personinformation.size(); i++) {
            Personinformation personinformation1 = personinformation.get(i);
            String personid = personinformation1.getCode();
            List<ReportLocationModel> reportLocationModels = superviseService.listLocation(personid);     //获取位置信息列表

            for (int j = 0; j < reportLocationModels.size(); j++) {         //计算位置定位违规次数
                ReportLocationModel reportLocationModel = reportLocationModels.get(j);
                boolean Fscope = reportLocationModel.isFscope();
                if (!reportLocationModel.isFscope()) {     //判断定位位置是否在范围内
                    locationViolateCount += 1;
                }
            }
            List<SinginInformation> singinFaceInformations = superviseService.listSingin(personid, 0);  //获取人脸签到次数
            faceSingin = faceSinginTimes - singinFaceInformations.size();
            if (faceSingin < 0) {
                faceSingin = 0;
            }
            List<SinginInformation> singinVoiceInformation = superviseService.listSingin(personid, 1);  //获取声纹签到次数
            voiceSingin = voiceSinginTimes - singinVoiceInformation.size();
            if (voiceSingin < 0) {
                voiceSingin = 0;
            }
        }
        //获取签到违规规则


        if (key == null) {
            //superviseReturnModel.setTotalCount(totalCount);
            if (type == 0) {       //所有人员列表
                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = personinformation.get(h);
                    newPerson.add(personinformation1);
                }
                superviseReturnModel.setTotalCount(personinformation.size());
                superviseReturnModel.setList(newPerson);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            if (type == 1) {       //最近新增人员

                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = recentPerson.get(h);
                    newRecentPerson.add(personinformation1);
                }
                superviseReturnModel.setTotalCount(recentPerson.size());
                superviseReturnModel.setList(newRecentPerson);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            if (type == 2) {    //取保候审人员

                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = bailoutPerson.get(h);
                    newBailoutPerson.add(personinformation1);
                }
                superviseReturnModel.setTotalCount(bailoutPerson.size());
                superviseReturnModel.setList(newBailoutPerson);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            if (type == 3) {     //监视居住人员

                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = bailoutWatchPerson.get(h);
                    newBailoutWatchPerson.add(personinformation1);
                }
                superviseReturnModel.setTotalCount(bailoutWatchPerson.size());
                superviseReturnModel.setList(newBailoutWatchPerson);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            return rs;
        }
        else{   //key关键字不为空
            List<Personinformation> keyPerson=new ArrayList<>();
            List<Personinformation> newKeyPerson=new ArrayList<>();
            if(type==0){
                for (Personinformation item:personinformation ){
                    if(item.getCode().contains(key)||item.getName().contains(key)){
                        keyPerson.add(item);
                    }
                }
                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = keyPerson.get(h);
                    newPerson.add(personinformation1);
                }
            }
            if(type==1){
                for (Personinformation item:recentPerson ){
                    if(item.getCode().contains(key)||item.getName().contains(key)){
                        keyPerson.add(item);
                    }
                }
                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = keyPerson.get(h);
                    newKeyPerson.add(personinformation1);
                }
            }
            if(type==2){
                for (Personinformation item:bailoutPerson ){
                    if(item.getCode().contains(key)||item.getName().contains(key)){
                        keyPerson.add(item);
                    }
                }
                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = keyPerson.get(h);
                    newKeyPerson.add(personinformation1);
                }
            }
            if(type==3){
                for (Personinformation item:bailoutWatchPerson ){
                    if(item.getCode().contains(key)||item.getName().contains(key)){
                        keyPerson.add(item);
                    }
                }
                for (int h = count; h < count + requestCount; h++) {
                    Personinformation personinformation1 = keyPerson.get(h);
                    newKeyPerson.add(personinformation1);
                }
            }

            superviseReturnModel.setList(keyPerson);
            rs.resultCode = 0;
            rs.resultMsg = "";
            rs.data = superviseReturnModel;
            return rs;
        }
    }

    @ApiOperation(value = "获取保外人员传讯记录")
    @GetMapping("/getCiteRecords")
    public ResultSet getCiteRecords(@RequestParam(required = false)String key,@RequestParam(required = false)String startDate,@RequestParam(required = false)String endDate,
                                    @RequestParam(required = true)int count,@RequestParam(required = true)int requestCount){
        CiteRecordReturnModel citeRecordReturnModel=new CiteRecordReturnModel();
        List<SummonsInformation> summonsInformations=new ArrayList<>();
        if(key==null){
            summonsInformations=superviseService.listCiteRecord();
        }
        else{
            summonsInformations=superviseService.listKeyCiteRecord(key);
        }
        List<SummonsInformation> newSummonsInformations=new ArrayList<>();    //经过时间筛选之后的传讯记录
        if(startDate!=null&&endDate==null){    //开始日期不为空
            for (SummonsInformation item:summonsInformations) {
                if(Long.parseLong(startDate)<=(item.getSummontime()).getTime()){
                    newSummonsInformations.add(item);
                }
            }
        }
        if(startDate==null&&endDate!=null){   //结束日期不为空
            for (SummonsInformation item:summonsInformations) {
                if(Long.parseLong(endDate)>(item.getSummontime()).getTime()){
                    newSummonsInformations.add(item);
                }
            }
        }
        if(startDate!=null&&endDate!=null){    //都不为空
            for (SummonsInformation item:summonsInformations) {
                if(Long.parseLong(startDate)<=(item.getSummontime()).getTime()&&Long.parseLong(endDate)>(item.getSummontime()).getTime()){
                    newSummonsInformations.add(item);
                }
            }
        }
        if(startDate==null&&endDate==null){  //都为空
            for (SummonsInformation item:summonsInformations) {
                newSummonsInformations.add(item);
            }
        }
        List<CiteRecordsModel> citeRecordsModel=new ArrayList<>();
        for (SummonsInformation item:newSummonsInformations
        ) {
            CiteRecordsModel citeRecordsModel1=new CiteRecordsModel();
            citeRecordsModel1.setName(item.getPersonname());
            long arrivedTime=(item.getSummontime()).getTime();
            citeRecordsModel1.setArrivedTime(Long.toString(arrivedTime));
            PersonAllInformationModel personinformation=superviseService.getPersonInformation(item.getPersonid());
            citeRecordsModel1.setIdCardNo(personinformation.getCard());
            citeRecordsModel1.setRecordPerson(personinformation.getSponsor());
            citeRecordsModel1.setArrivedUnit(personinformation.getPolicestation());
            citeRecordsModel.add(citeRecordsModel1);
        }

        List<CiteRecordsModel> summonsInformations1=new ArrayList<>();  //取count到count+requestCount之间的记录

        if(citeRecordsModel.size()>count&&citeRecordsModel.size()<count+requestCount){
            for(int i=count;i<citeRecordsModel.size();i++){
                CiteRecordsModel summonsInformation=citeRecordsModel.get(i);
                summonsInformations1.add(summonsInformation);
            }
        }
        if(citeRecordsModel.size()>=count+requestCount){
            for(int i=count;i<count+requestCount;i++){
                CiteRecordsModel summonsInformation=citeRecordsModel.get(i);
                summonsInformations1.add(summonsInformation);
            }
        }
        citeRecordReturnModel.setTotalCount(newSummonsInformations.size());
        citeRecordReturnModel.setList(summonsInformations1);
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=citeRecordReturnModel;
        return rs;
    }

    @ApiOperation(value = "提交传讯记录")
    @PostMapping("/submitCiteRecord")
    public ResultSet postCiteRecord(@ApiParam(name = "code",value = "保外人员编码")@RequestParam(required = true)String code){
        PersonAllInformationModel personinformation=superviseService.getPersonInformation(code);
        if(personinformation!=null){
            String personName=personinformation.getPersonname();
            Date summonsTime=new Date();
            try {
                CiteRecordSubmitModel citeRecordSubmitModel=new CiteRecordSubmitModel();
                citeRecordSubmitModel.setPersonid(code);
                citeRecordSubmitModel.setPersonname(personName);
                citeRecordSubmitModel.setSummonsTime(summonsTime);
                int summonsId = superviseService.insertCiteRecord(citeRecordSubmitModel);
                CiteRecordSubmitReturnModel citeRecordSubmitReturnModel=new CiteRecordSubmitReturnModel();
                citeRecordSubmitReturnModel.setCode(String.valueOf(citeRecordSubmitModel.getId()));
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = citeRecordSubmitReturnModel;
            }catch (Exception e){
                rs.resultCode=1;
                rs.resultMsg=e.getMessage();
                rs.data=null;
            }
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此监居人员";

            rs.data=null;
        }
        return rs;
    }

//    @ApiOperation(value = "获取保外人员未到案统计")
//    @GetMapping("/getCiteNotArrived")
//    public ResultSet getCiteNotArrived(@ApiParam(name="startDate",value = "开始日期时间戳")@RequestParam(required = true)String startDate,
//                                       @ApiParam(name="endDate",value = "结束日期时间戳")@RequestParam(required = true)String endDate){
//        List<ReminderSettingsInformation> allSettingInformation=superviseService.listSummonSetting();
//        ReminderSettingsInformation summonSetting=new ReminderSettingsInformation();    //符合条件的传讯设置日期
//        for (ReminderSettingsInformation item:allSettingInformation) {
//            if(item.getCreatetime().getTime()>=Long.parseLong(startDate)&&item.getCreatetime().getTime()<Long.parseLong(endDate)){
//                summonSetting=item;
//            }
//            else if(item.isStatus()){
//                summonSetting=item;
//            }
//        }
//        int summonSettingTimes=summonSetting.getSettingday().toCharArray().length;   //需要传讯次数 //将传讯设置日期转为数组计算日期个数
//
//        List<SummonsInformation> summonsInformations=superviseService.listCiteRecord();
//        List<SummonsInformation> newSummonsInformations=new ArrayList<>();     //筛选规定时间内的传讯数据
//        for (SummonsInformation item:summonsInformations
//             ) {
//            if(item.getReporttime().getTime()>=Long.parseLong(startDate)&&item.getReporttime().getTime()<Long.parseLong(endDate)){
//                newSummonsInformations.add(item);
//            }
//        }
//
//        List<SummonsInformation> newTemp=new ArrayList<>();    //筛选重复数据
//        for(int i=0;i<newSummonsInformations.size();i++){
//            SummonsInformation listTemp=newSummonsInformations.get(i);
//            for (SummonsInformation item:newTemp
//                 ) {
//                if(item.getPersonid()!=listTemp.getPersonid()){
//                    newTemp.add(listTemp);
//                }
//            }
//        }
//
//        int slightFens=0;   //轻微级别次数
//        int seriousFens=0;  //严重级别次数
//        List<ViolationFensInformation> violationFensInformations=superviseService.listViolationFensInformation("传讯未报道");  //查找传讯违规的程度级别
//        for (ViolationFensInformation item:violationFensInformations
//             ) {
//            if(item.getCode().equals("1")){
//                slightFens=item.getRangefens();
//            }
//            if(item.getCode().equals("2")){
//                seriousFens=item.getRangefens();
//            }
//        }
//        int totalCount=0;
//        int summonsTimes=0;   //个人实际传讯次数
//        int missSummonsTimes=0;  //缺少的传讯次数
//        List<CiteNotArrivedModel> citeNotArrivedModels=new ArrayList<>();
//        for (SummonsInformation item:newTemp
//             ) {
//            for (SummonsInformation temp:newSummonsInformations
//                 ) {
//                if(item.getPersonid()==temp.getPersonid()){  //
//                    summonsTimes++;
//                }
//            }
//            missSummonsTimes=summonSettingTimes-summonsTimes;   //缺少的传讯次数=需要传讯次数-个人实际传讯次数
//            totalCount+=missSummonsTimes;
//            CiteNotArrivedModel citeNotArrivedModel=new CiteNotArrivedModel();
//            citeNotArrivedModel.setName(item.getPersonname());
//            if(missSummonsTimes==0&&missSummonsTimes<slightFens){
//                citeNotArrivedModel.setViolateCode("0");
//                citeNotArrivedModel.setViolate("正常");
//                citeNotArrivedModel.setNotArrivedCount(missSummonsTimes);
//            }
//            if(missSummonsTimes<seriousFens&&missSummonsTimes>=slightFens){
//                citeNotArrivedModel.setViolateCode("1");
//                citeNotArrivedModel.setViolate("轻微");
//                citeNotArrivedModel.setNotArrivedCount(missSummonsTimes);
//            }
//            if(missSummonsTimes>=seriousFens){
//                citeNotArrivedModel.setViolateCode("2");
//                citeNotArrivedModel.setViolate("严重");
//                citeNotArrivedModel.setNotArrivedCount(missSummonsTimes);
//            }
//            citeNotArrivedModels.add(citeNotArrivedModel);
//        }
//        CiteNotArrivedReturnModel citeNotArrivedReturnModel=new CiteNotArrivedReturnModel();
//        citeNotArrivedReturnModel.setTotalCount(totalCount);
//        citeNotArrivedReturnModel.setList(citeNotArrivedModels);
//        rs.resultCode=0;
//        rs.resultMsg="";
//        rs.data=citeNotArrivedReturnModel;
//        return rs;
//    }

    @ApiOperation(value = "获取保外人员未到案统计")
    @GetMapping("/getCiteNotArrived")
    public ResultSet getCiteNotArrived(@ApiParam(name="startDate",value = "开始日期时间戳")@RequestParam(required = true)String startDate,
                                       @ApiParam(name="endDate",value = "结束日期时间戳")@RequestParam(required = true)String endDate,
                                       @ApiParam(name="count",value = "当前已获取的数据条数")@RequestParam(required = true)int count,
                                       @ApiParam(name="requestCount",value = "请求获取数据的条数")@RequestParam(required = true)int requestCount,
                                       @ApiParam(name="key",value = "搜索关键字")@RequestParam(required = false)String key) {
        if(key==null) {   //关键字为空
            List<SummonsInformation> summonsInformations = superviseService.listCiteRecord();
            List<SummonsInformation> newSummonsInformations = new ArrayList<>();     //筛选规定时间内的传讯数据
            for (SummonsInformation item : summonsInformations
            ) {
                if (item.getReporttime().getTime() >= Long.parseLong(startDate) && item.getReporttime().getTime() < Long.parseLong(endDate)) {
                    newSummonsInformations.add(item);
                }
            }
            List<SummonsInformation> newTemp = new ArrayList<>();    //筛选重复数据
            for (int i = 0; i < newSummonsInformations.size(); i++) {
                SummonsInformation listTemp = newSummonsInformations.get(i);
                for (SummonsInformation item : newTemp
                ) {
                    if (item.getPersonid() != listTemp.getPersonid()) {
                        newTemp.add(listTemp);
                    }
                }
            }
            for (SummonsInformation item:newTemp
                 ) {
                //Personinformation personinformation=superviseService.listPersonInformation(item.getPersonid());
            }
        }
        else{

        }
        return  rs;
    }

    @ApiOperation(value = "获取保外人员的外出申请列表")
    @GetMapping("/getApplyLeaveList")
    public ResultSet getApplyLeaveList(@RequestParam(required = true)String status,@RequestParam(required = false)String key,
                                       @RequestParam(required = true)int count,@RequestParam(required = true)int requestCount){
        List<LeaveListModel> leaveListModels=new ArrayList<>();
        if(key==null){   //没有key关键字
            if(status.equals("0")){       //全部外出申请列表数据
                leaveListModels=superviseService.getLeaveList();
            }
            if(status.equals("1")){    //"待审批"申请列表
                leaveListModels=superviseService.listLeaveType("1");
            }
            if(status.equals("2")){    //"审批通过"申请列表
                leaveListModels=superviseService.listLeaveType("2");
            }
            if(status.equals("3")){   //"审批未通过"申请列表
                leaveListModels=superviseService.listLeaveType("3");
            }
        }
        else{      //有key关键字
            if(status.equals("0")){
                leaveListModels=superviseService.getKeyLeaveList(key);
            }
            if(status.equals("1")){
                leaveListModels=superviseService.listKeyLeaveType(key,"1");
            }
            if(status.equals("2")){
                leaveListModels=superviseService.listKeyLeaveType(key,"2");
            }
            if(status.equals("3")){
                leaveListModels=superviseService.listKeyLeaveType(key,"3");
            }
        }
        for (LeaveListModel item:leaveListModels) {
            long startDate=Long.parseLong(item.getStartTimestamp());
            long endDate=Long.parseLong(item.getEndTimestamp());
            long days = (endDate/1000 - startDate/1000) / (60 * 60 * 24)+1;
            int Days=Integer.parseInt(String.valueOf(days));
            item.setDays(Days);
            String leaveOrder=item.getCode();
            List<AuditorRecordModel> getAuditorList=superviseService.getAuditorList(leaveOrder);
            item.setApplyRecord(getAuditorList);
        }
        List<LeaveListModel> newLeaveList=new ArrayList<>();
        if(leaveListModels.size()>count&&leaveListModels.size()<count+requestCount){
            for(int i=count;i<leaveListModels.size();i++){
                LeaveListModel summonsInformation=leaveListModels.get(i);
                newLeaveList.add(summonsInformation);
            }
        }
        if(leaveListModels.size()>count+requestCount){
            for(int i=count;i<count+requestCount;i++){
                LeaveListModel summonsInformation=leaveListModels.get(i);
                newLeaveList.add(summonsInformation);
            }
        }
        LeaveListReturnModel leaveListReturnModel=new LeaveListReturnModel();
        leaveListReturnModel.setTotalCount(leaveListModels.size());
        leaveListReturnModel.setList(newLeaveList);
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=leaveListReturnModel;
        return rs;
    }

    @ApiOperation(value = "审批保外人员外出申请")
    @PostMapping("/approveApplyLeave")
    public ResultSet approveApplyLeave(@ApiParam(name = "code",value = "外出申请单号")@RequestParam(required = true)String code,
                                       @ApiParam(name = "isApprove",value = "是否通过")@RequestParam(required = true)boolean isApprove){
        LeaveListModel leaveInformation=superviseService.getLeaveInformation(code);
        if(leaveInformation!=null){
            if(leaveInformation.getCode().equals("1")){    //判断该请假单是否为待审批状态
                String userId=CacheUtils.get("UserId").toString();
                String userName = CacheUtils.get("UserName").toString();
                Date date=new Date();
                String message=leaveInformation.getReason();
                if(isApprove){    //审批为通过
                    int updateLeaveInformation=superviseService.updateLeaveInformation(code,"2","审批通过");  //修改请假单信息
                    int insertAuditorInformation=superviseService.insertAuditorInformation(code,userId,userName,date,message,"2","审批通过");
                }
                else{      //审批不通过
                    int updateLeaveInformation=superviseService.updateLeaveInformation(code,"3","审批未通过"); //修改请假单信息
                    int insertAuditorInformation=superviseService.insertAuditorInformation(code,userId,userName,date,message,"3","审批不通过");
                }
                rs.resultCode=0;
                rs.resultMsg="";
                rs.data=null;
            }
            else{
                rs.resultCode=1;
                rs.resultMsg="该请假单已审批";
                rs.data=null;
            }
        }
        else {
            rs.resultCode=1;
            rs.resultMsg="该请假单不存在";
            rs.data=null;
        }
        return rs;
    }
}
