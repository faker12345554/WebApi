package com.adminapp.business.controller.dw_supervise;

import com.adminapp.business.entity.dw_supervise.*;
import com.adminapp.business.service.dw_supervise.SuperviseService;
import com.adminapp.config.CacheUtils;
import com.adminapp.config.token.TokenUtil;
import com.adminapp.config.token.tation.UserLoginToken;
import com.adminapp.model.dw_supervise.*;
import com.common.common.result.ResultSet;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/app/admin/supervise")
public class SuperviseController {

    @Autowired
    private SuperviseService superviseService;

    private ResultSet rs=new ResultSet();

//    @ApiOperation(value = "获取保外人员列表")
//    @GetMapping("/getAgainstRule")
//    public ResultSet getAgainstRule(@ApiParam(name = "type",value = "类别") @RequestParam(required = true)int type,@ApiParam(name = "count",value = "当前已经获取的数据条数") @RequestParam(required = true)int count,
//                                    @ApiParam(name = "requestCount",value = "请求获取数据的条数") @RequestParam(required = true)int requestCount,@ApiParam(name = "key",value = "搜索关键字") @RequestParam(required = false)String key) {
//        List<Personinformation> newPerson = new ArrayList<>();  //符合条件所有监居人员列表
//        List<Personinformation> newRecentPerson = new ArrayList<>();  //符合条件的最近新增人员列表
//        List<Personinformation> newBailoutPerson = new ArrayList<>(); //符合条件的取保候审人员列表
//        List<Personinformation> newBailoutWatchPerson = new ArrayList<>(); //符合条件的监视居住人员列表
//        String userId = CacheUtils.get("UserId").toString();        //获取工作人员id
//        List<Personinformation> personinformation = superviseService.listPersonInformation(userId);  //获取该工作人员负责的所有监居人员列表
//        SuperviseReturnModel superviseReturnModel = new SuperviseReturnModel();
//        int totalCount =0;
//
//        Date date = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);   //设置当前时间
//        long time1 = cal.getTimeInMillis();
//        cal.add(Calendar.MONTH, -1);  //在当前时间基础上减一个月
//        Date lastDate = cal.getTime();    //取得上一个月日期
//        List<Personinformation> recentPerson = new ArrayList<>();
//        for (Personinformation item : personinformation) {
//            Date bailoutbegindate = item.getExecStartDate();
//            if (lastDate.getTime() < bailoutbegindate.getTime()) {    //用监居开始时间与一月前时间比较，大于则为新增人员
//                recentPerson.add(item);
//            }
//        }long time2 = cal.getTimeInMillis();
//        long days = (time2 - time1) / (1000 * 60 * 60 * 24);
//
//        List<Personinformation> bailoutPerson = new ArrayList<>();
//        for (Personinformation item : personinformation) {
//            String suspectStatus = item.getState();
//            if (suspectStatus.equals("取保候审")) {
//                bailoutPerson.add(item);
//            }
//        }
//
//        List<Personinformation> bailoutWatchPerson = new ArrayList<>();
//        for (Personinformation item : personinformation) {
//            String suspectStatus = item.getState();
//            if (suspectStatus.equals("监视居住")) {
//                bailoutWatchPerson.add(item);
//            }
//        }
//
//        int faceSingin = 0;      //人脸签到违规次数
//        int voiceSingin = 0;     //声纹签到违规次数
//        int locationViolateCount = 0;   //位置违规次数
//        int faceSinginTimes = 0;    //上报设置中人脸签到的次数
//        int voiceSinginTimes = 0;   //上报设置中声纹签到的次数
//        List<ReportSettingsInformation> reportSettingsInformations = superviseService.listReportSetting();  //获取上报设置列表
//        for (int a = 0; a < reportSettingsInformations.size(); a++) {
//            ReportSettingsInformation reportSettingsInformation = reportSettingsInformations.get(a);
//            if (reportSettingsInformation.getType() == 2) {
//                faceSinginTimes = reportSettingsInformation.getReportcount();   //获取上报设置中人脸签到的次数
//            }
//            if (reportSettingsInformation.getType() == 3) {
//                voiceSinginTimes = reportSettingsInformation.getReportcount();  //获取上报设置中声纹签到的次数
//            }
//        }
//        for (int i = 0; i < personinformation.size(); i++) {
//            Personinformation personinformation1 = personinformation.get(i);
//            String personid = personinformation1.getCode();
//            List<ReportLocationModel> reportLocationModels = superviseService.listLocation(personid);     //获取位置信息列表
//
//            for (int j = 0; j < reportLocationModels.size(); j++) {         //计算位置定位违规次数
//                ReportLocationModel reportLocationModel = reportLocationModels.get(j);
//                boolean Fscope = reportLocationModel.isFscope();
//                if (!reportLocationModel.isFscope()) {     //判断定位位置是否在范围内
//                    locationViolateCount += 1;
//                }
//            }
//            List<SinginInformation> singinFaceInformations = superviseService.listSingin(personid, 0);  //获取人脸签到次数
//            faceSingin = faceSinginTimes - singinFaceInformations.size();
//            if (faceSingin < 0) {
//                faceSingin = 0;
//            }
//            List<SinginInformation> singinVoiceInformation = superviseService.listSingin(personid, 1);  //获取声纹签到次数
//            voiceSingin = voiceSinginTimes - singinVoiceInformation.size();
//            if (voiceSingin < 0) {
//                voiceSingin = 0;
//            }
//        }
//        //获取签到违规规则
//
//
//        if (key == null) {
//            //superviseReturnModel.setTotalCount(totalCount);
//            if (type == 0) {       //所有人员列表
//                for (int h = count; h < count + requestCount; h++) {
//                    Personinformation personinformation1 = personinformation.get(h);
//                    newPerson.add(personinformation1);
//                }
//                superviseReturnModel.setTotalCount(personinformation.size());
//                superviseReturnModel.setList(newPerson);
//                rs.resultCode = 0;
//                rs.resultMsg = "";
//                rs.data = superviseReturnModel;
//            }
//            if (type == 1) {       //最近新增人员
//
//                for (int h = count; h < count + requestCount; h++) {
//                    Personinformation personinformation1 = recentPerson.get(h);
//                    newRecentPerson.add(personinformation1);
//                }
//                superviseReturnModel.setTotalCount(recentPerson.size());
//                superviseReturnModel.setList(newRecentPerson);
//                rs.resultCode = 0;
//                rs.resultMsg = "";
//                rs.data = superviseReturnModel;
//            }
//            if (type == 2) {    //取保候审人员
//
//                for (int h = count; h < count + requestCount; h++) {
//                    Personinformation personinformation1 = bailoutPerson.get(h);
//                    newBailoutPerson.add(personinformation1);
//                }
//                superviseReturnModel.setTotalCount(bailoutPerson.size());
//                superviseReturnModel.setList(newBailoutPerson);
//                rs.resultCode = 0;
//                rs.resultMsg = "";
//                rs.data = superviseReturnModel;
//            }
//            if (type == 3) {     //监视居住人员
//
//                for (int h = count; h < count + requestCount; h++) {
//                    Personinformation personinformation1 = bailoutWatchPerson.get(h);
//                    newBailoutWatchPerson.add(personinformation1);
//                }
//                superviseReturnModel.setTotalCount(bailoutWatchPerson.size());
//                superviseReturnModel.setList(newBailoutWatchPerson);
//                rs.resultCode = 0;
//                rs.resultMsg = "";
//                rs.data = superviseReturnModel;
//            }
//            return rs;
//        }
//        else{   //key关键字不为空
//            List<Personinformation> keyPerson=new ArrayList<>();
////            List<Personinformation> newKeyPerson=new ArrayList<>();
////            if(type==0){
////                for (Personinformation item:personinformation ){
////                    if(item.getCode().contains(key)||item.getName().contains(key)){
////                        keyPerson.add(item);
////                    }
////                }
//                for (int h = count; h < count + requestCount; h++) {
//                    Personinformation personinformation1 = keyPerson.get(h);
//                    newPerson.add(personinformation1);
//                }
//            }
//            if(type==1){
//                for (Personinformation item:recentPerson ){
//                    if(item.getCode().contains(key)||item.getName().contains(key)){
//                        keyPerson.add(item);
//                    }
//                }
//                for (int h = count; h < count + requestCount; h++) {
//                    Personinformation personinformation1 = keyPerson.get(h);
//                    newKeyPerson.add(personinformation1);
//                }
//            }
//            if(type==2){
//                for (Personinformation item:bailoutPerson ){
//                    if(item.getCode().contains(key)||item.getName().contains(key)){
//                        keyPerson.add(item);
//                    }
//                }
//                for (int h = count; h < count + requestCount; h++) {
//                    Personinformation personinformation1 = keyPerson.get(h);
//                    newKeyPerson.add(personinformation1);
//                }
//            }
//            if(type==3){
//                for (Personinformation item:bailoutWatchPerson ){
//                    if(item.getCode().contains(key)||item.getName().contains(key)){
//                        keyPerson.add(item);
//                    }
//                }
//                for (int h = count; h < count + requestCount; h++) {
//                    Personinformation personinformation1 = keyPerson.get(h);
//                    newKeyPerson.add(personinformation1);
//                }
//            }
//
//            superviseReturnModel.setList(keyPerson);
//            rs.resultCode = 0;
//            rs.resultMsg = "";
//            rs.data = superviseReturnModel;
//            return rs;
//        }
//    }
    @UserLoginToken
    @ApiOperation(value = "获取保外人员列表")
    @GetMapping("/getSuperviseList")
    public ResultSet getSuperviseList(@ApiParam(name = "type",value = "类别") @RequestParam(required = true)int type,@ApiParam(name = "count",value = "当前已经获取的数据条数") @RequestParam(required = true)int count,
                                    @ApiParam(name = "requestCount",value = "请求获取数据的条数") @RequestParam(required = true)int requestCount,@ApiParam(name = "key",value = "搜索关键字") @RequestParam(required = false)String key) {
        String userId=TokenUtil.getTokenUserId();
        List<Personinformation> newPerson = new ArrayList<>();  //符合条件所有监居人员列表
        List<Personinformation> newRecentPerson = new ArrayList<>();  //符合条件的最近新增人员列表
        List<Personinformation> newBailoutPerson = new ArrayList<>(); //符合条件的取保候审人员列表
        List<Personinformation> newBailoutWatchPerson = new ArrayList<>(); //符合条件的监视居住人员列表

        List<Personinformation> personinformation = superviseService.listPersonInformation(userId);  //获取该工作人员负责的所有监居人员列表
        SuperviseReturnModel superviseReturnModel = new SuperviseReturnModel();
//        int totalCount =0;
//
//        int locationViolateSlight=superviseService.listViolationFensInformation("脱离管控区域","1");  //上报设置中位置轻微违规次数
//        int locationViolateSerious =superviseService.listViolationFensInformation("脱离管控区域","2"); //上报设置中位置严重违规次数
//        int summonsViolateSlight=superviseService.listViolationFensInformation("传讯取证未报到","1");   //上报设置中传讯轻微违规次数
//        int summonsViolateSerious=superviseService.listViolationFensInformation("传讯取证未报到","2");  //上报设置中传讯严重违规次数
//        int faceSinginSlight =superviseService.listViolationFensInformation("视频签到缺勤","1");    //上报设置中人脸签到轻微违规次数
//        int faceSinginSerious =superviseService.listViolationFensInformation("视频签到缺勤","2");   //上报设置中人脸签到严重违规次数
//        int voiceSinginSlight =superviseService.listViolationFensInformation("语音签到缺勤","1");   //上报设置中声纹签到轻微违规次数
//        int voiceSinginSerious =superviseService.listViolationFensInformation("语音签到缺勤","2");  //上报设置中声纹签到严重违规次数

//        int locationViolateCount=0;    //位置违规次数
//            List<ReportLocationModel> reportLocationModels = superviseService.listLocation(item.getCode());     //获取位置信息列表
//            for (int j = 0; j < reportLocationModels.size(); j++) {         //计算位置定位违规次数
//                ReportLocationModel reportLocationModel = reportLocationModels.get(j);
//                boolean Fscope = reportLocationModel.isFscope();
//                if (reportLocationModel.isFscope()) {     //判断定位位置是否在范围内
//                    locationViolateCount += 1;
//                }
//            }
//            int locationViolateStatus=0;    //违规状态为正常
//            if(locationViolateCount>=locationViolateSlight&&locationViolateCount<locationViolateSerious){   //位置违规次数
//                locationViolateStatus=1;   //违规状态为轻微
//            }
//            if(locationViolateCount>=locationViolateSerious){
//                locationViolateStatus=2;  //违规状态为严重
//            }
//            int summonsViolateTimes=0;    //传讯违规次数
//
//            List<SummonsInformation> summonsInformations=superviseService.getSummonsInformation(item.getCode());
//            for (SummonsInformation item3:summonsInformations
//            ) {
//                if(item3.getReporttime()==null){
//                    summonsViolateTimes++;
//                }
//            }
//            int summonsViolateStatus=0;   //传讯违规状态
//            if(summonsViolateTimes>=summonsViolateSlight&&summonsViolateTimes<summonsViolateSerious){
//                summonsViolateStatus=1;
//            }
//            if(summonsViolateTimes>=summonsViolateSerious){
//                summonsViolateStatus=2;
//            }
//            List<SinginInformation> faceSinginInformations=superviseService.listPersonSingin(item.getCode(),0);   //获取人脸签到记录
//            int faceViolateTimes=0;   //人脸签到违规次数
//            for (SinginInformation item5:faceSinginInformations
//            ) {
//                if(item5.getResult()==1){     //签到状态为1时是违规状态
//                    faceViolateTimes++;
//                }
//            }
//            int faceViolateStatus=0;   //人脸违规状态
//            if(faceViolateTimes>=faceSinginSlight&&faceViolateTimes<faceSinginSerious){
//                faceViolateStatus=1;
//            }
//            if(faceViolateTimes>=faceSinginSerious){
//                faceViolateStatus=2;
//            }
//            List<SinginInformation> voiceSinginInformations=superviseService.listPersonSingin(item.getCode(),1);   //获取声纹签到记录
//            int voiceViolateTimes=0;   //声纹签到违规次数
//            for (SinginInformation item7:voiceSinginInformations
//            ) {
//                if(item7.getResult()==1){
//                    voiceViolateTimes++;
//                }
//            }
//            int voiceViolateStatus=0;    //声纹违规状态
//            if(voiceViolateTimes>=voiceSinginSlight&&voiceViolateTimes<voiceSinginSerious){
//                voiceViolateStatus=1;
//            }
//            if(voiceViolateTimes>=voiceSinginSerious){
//                voiceViolateStatus=2;
//            }
//            if(locationViolateStatus==0&&summonsViolateStatus==0&&faceViolateStatus==0&&voiceViolateStatus==0){
//                item.setViolateCode("0");
//                item.setViolate("正常");
//            }
//            if(locationViolateStatus==1||summonsViolateStatus==1||faceViolateStatus==1||voiceViolateStatus==1){
//                item.setViolateCode("1");
//                item.setViolate("轻微");
//            }
//            if(locationViolateStatus==1||summonsViolateStatus==2||faceViolateStatus==2||voiceViolateStatus==2){
//                item.setViolateCode("2");
//                item.setViolate("严重");
//            }
//        }

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);   //设置当前时间
        long time1 = cal.getTimeInMillis();
        cal.add(Calendar.MONTH, -1);  //在当前时间基础上减一个月
        Date lastDate = cal.getTime();    //取得上一个月日期
        List<Personinformation> recentPerson = new ArrayList<>();   //新增人员
        List<Personinformation> bailoutPerson = new ArrayList<>();  //取保候审人员
        List<Personinformation> bailoutWatchPerson = new ArrayList<>();  //监视居住人员
        for (Personinformation item : personinformation) {
            String violateCode=item.getViolateCode();
            String violateName=superviseService.getViolateName("WGCD-001",violateCode);
            item.setViolate(violateName);

            if (lastDate.getTime() < Long.parseLong(item.getExecStartDate())) {    //用监居开始时间与一月前时间比较，大于则为新增人员
                recentPerson.add(item);
            }

            if (item.getState().equals("取保候审")) {
                bailoutPerson.add(item);
            }

            if (item.getState().equals("监视居住")) {
                bailoutWatchPerson.add(item);
            }
        }

        if(key==null||key==""){
            if (type == 0){
                List<Personinformation> newPersonInformations=new ArrayList<>();   //所有人员信息
                if(personinformation.size()>count&&personinformation.size()<count+requestCount) {
                    for(int i=count;i<personinformation.size();i++){
                        Personinformation personInformation1=personinformation.get(i);
                        newPersonInformations.add(personInformation1);
                    }
                }
                if(personinformation.size()>=count+requestCount){
                    for(int i=count;i<count+requestCount;i++){
                        Personinformation summonsInformation=personinformation.get(i);
                        newPersonInformations.add(summonsInformation);
                    }
                }
                superviseReturnModel.setTotalCount(personinformation.size());
                superviseReturnModel.setList(newPersonInformations);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            if(type==1){
                List<Personinformation> newPersonInformations=new ArrayList<>();   //新增人员信息
                if(recentPerson.size()>count&&recentPerson.size()<count+requestCount) {
                    for(int i=count;i<recentPerson.size();i++){
                        Personinformation personInformation1=recentPerson.get(i);
                        newPersonInformations.add(personInformation1);
                    }
                }
                if(recentPerson.size()>=count+requestCount){
                    for(int i=count;i<count+requestCount;i++){
                        Personinformation summonsInformation=recentPerson.get(i);
                        newPersonInformations.add(summonsInformation);
                    }
                }
                superviseReturnModel.setTotalCount(recentPerson.size());
                superviseReturnModel.setList(newPersonInformations);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            if(type==2){
                List<Personinformation> newPersonInformations=new ArrayList<>();   //取保候审人员信息
                if(bailoutPerson.size()>count&&bailoutPerson.size()<count+requestCount) {
                    for(int i=count;i<bailoutPerson.size();i++){
                        Personinformation personInformation1=bailoutPerson.get(i);
                        newPersonInformations.add(personInformation1);
                    }
                }
                if(bailoutPerson.size()>=count+requestCount){
                    for(int i=count;i<count+requestCount;i++){
                        Personinformation summonsInformation=bailoutPerson.get(i);
                        newPersonInformations.add(summonsInformation);
                    }
                }
                superviseReturnModel.setTotalCount(bailoutPerson.size());
                superviseReturnModel.setList(newPersonInformations);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            if(type==3){
                List<Personinformation> newPersonInformations=new ArrayList<>();     //监视居住人员
                if(bailoutWatchPerson.size()>count&&bailoutWatchPerson.size()<count+requestCount) {
                    for(int i=count;i<bailoutWatchPerson.size();i++){
                        Personinformation personInformation1=bailoutWatchPerson.get(i);
                        newPersonInformations.add(personInformation1);
                    }
                }
                if(bailoutWatchPerson.size()>=count+requestCount){
                    for(int i=count;i<count+requestCount;i++){
                        Personinformation summonsInformation=bailoutWatchPerson.get(i);
                        newPersonInformations.add(summonsInformation);
                    }
                }
                superviseReturnModel.setTotalCount(bailoutWatchPerson.size());
                superviseReturnModel.setList(newPersonInformations);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
        }
        else{   //关键字不为空
            List<Personinformation> keyPerson=new ArrayList<>();
            for (Personinformation item:personinformation
                 ) {
                if(item.getCode().contains(key)||item.getName().contains(key)){
                    keyPerson.add(item);
                }
            }
            List<Personinformation> keyRecentPerson=new ArrayList<>();
            for (Personinformation item:recentPerson ) {
                if (item.getCode().contains(key) || item.getName().contains(key)) {
                    keyRecentPerson.add(item);
                }
            }
            List<Personinformation> keyBailoutPerson=new ArrayList<>();
            for (Personinformation item:bailoutPerson ) {
                if (item.getCode().contains(key) || item.getName().contains(key)) {
                    keyBailoutPerson.add(item);
                }
            }
            List<Personinformation> keyBailoutWatchPerson=new ArrayList<>();
            for (Personinformation item:bailoutWatchPerson ) {
                if (item.getCode().contains(key) || item.getName().contains(key)) {
                    keyBailoutWatchPerson.add(item);
                }
            }
            if(type==0){
                List<Personinformation> newPersonInformations=new ArrayList<>();
                if(keyPerson.size()>count&&keyPerson.size()<count+requestCount) {
                    for(int i=count;i<keyPerson.size();i++){
                        Personinformation personInformation1=keyPerson.get(i);
                        newPersonInformations.add(personInformation1);
                    }
                }
                if(keyPerson.size()>=count+requestCount){
                    for(int i=count;i<count+requestCount;i++){
                        Personinformation summonsInformation=keyPerson.get(i);
                        newPersonInformations.add(summonsInformation);
                    }
                }
                superviseReturnModel.setTotalCount(keyPerson.size());
                superviseReturnModel.setList(newPersonInformations);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            if(type==1){
                List<Personinformation> newPersonInformations=new ArrayList<>();
                if(keyRecentPerson.size()>count&&keyRecentPerson.size()<count+requestCount) {
                    for(int i=count;i<keyRecentPerson.size();i++){
                        Personinformation personInformation1=keyRecentPerson.get(i);
                        newPersonInformations.add(personInformation1);
                    }
                }
                if(keyRecentPerson.size()>=count+requestCount){
                    for(int i=count;i<count+requestCount;i++){
                        Personinformation summonsInformation=keyRecentPerson.get(i);
                        newPersonInformations.add(summonsInformation);
                    }
                }
                superviseReturnModel.setTotalCount(keyRecentPerson.size());
                superviseReturnModel.setList(newPersonInformations);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            if(type==2){
                List<Personinformation> newPersonInformations=new ArrayList<>();
                if(keyBailoutPerson.size()>count&&keyBailoutPerson.size()<count+requestCount) {
                    for(int i=count;i<keyBailoutPerson.size();i++){
                        Personinformation personInformation1=keyBailoutPerson.get(i);
                        newPersonInformations.add(personInformation1);
                    }
                }
                if(keyBailoutPerson.size()>=count+requestCount){
                    for(int i=count;i<count+requestCount;i++){
                        Personinformation summonsInformation=keyBailoutPerson.get(i);
                        newPersonInformations.add(summonsInformation);
                    }
                }
                superviseReturnModel.setTotalCount(keyRecentPerson.size());
                superviseReturnModel.setList(newPersonInformations);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
            if(type==3){
                List<Personinformation> newPersonInformations=new ArrayList<>();
                if(keyBailoutWatchPerson.size()>count&&keyBailoutWatchPerson.size()<count+requestCount) {
                    for(int i=count;i<keyBailoutWatchPerson.size();i++){
                        Personinformation personInformation1=keyBailoutWatchPerson.get(i);
                        newPersonInformations.add(personInformation1);
                    }
                }
                if(keyBailoutWatchPerson.size()>=count+requestCount){
                    for(int i=count;i<count+requestCount;i++){
                        Personinformation summonsInformation=keyBailoutWatchPerson.get(i);
                        newPersonInformations.add(summonsInformation);
                    }
                }
                superviseReturnModel.setTotalCount(keyRecentPerson.size());
                superviseReturnModel.setList(newPersonInformations);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = superviseReturnModel;
            }
        }
        return rs;
}

    @UserLoginToken
    @ApiOperation(value = "获取保外人员传讯记录")
    @GetMapping("/getCiteRecords")
    public ResultSet getCiteRecords(@RequestParam(required = false)String key,
                                    @RequestParam(required = false)String startDate,
                                    @RequestParam(required = false)String endDate,
                                    @RequestParam(required = true)int count,
                                    @RequestParam(required = true)int requestCount){
        String userId=TokenUtil.getTokenUserId();
        CiteRecordReturnModel citeRecordReturnModel=new CiteRecordReturnModel();
        List<SummonsInformation> summonsInformations=new ArrayList<>();
        if(key==null||key==""){
            summonsInformations=superviseService.listCiteRecord();
        }
        else{
            summonsInformations=superviseService.listKeyCiteRecord(key);
        }

        List<CiteRecordsModel> summonsInformations1 = new ArrayList<>();  //取count到count+requestCount之间的记录
        List<SummonsInformation> newSummonsInformations = new ArrayList<>();    //经过时间筛选之后的传讯记录
        if(summonsInformations.size()!=0) {
            for (int i = 0; i < summonsInformations.size(); i++) {
                SummonsInformation summonsInformation = summonsInformations.get(i);
                PersonAllInformationModel personAllInformationModel1 = superviseService.getPersonInformation(summonsInformation.getPersonid());
                if (personAllInformationModel1.getSponsoralarm().equals(userId) == false) {
                    summonsInformations.remove(summonsInformation);
                    i = i - 1;
                }
            }
            if (startDate != null&&startDate!="" && (endDate == null||endDate=="")) {    //开始日期不为空
                for (SummonsInformation item : summonsInformations) {
                    if (Long.parseLong(startDate) <= Long.parseLong(item.getReporttime())) {
                        newSummonsInformations.add(item);
                    }
                }
            }
            if ((startDate == null||startDate=="") && endDate != null&&endDate!="") {   //结束日期不为空
                for (SummonsInformation item : summonsInformations) {
                    if (Long.parseLong(endDate) > Long.parseLong(item.getReporttime())) {
                        newSummonsInformations.add(item);
                    }
                }
            }
            if (startDate != null&&startDate!="" && endDate != null&&endDate!="") {    //都不为空
                for (SummonsInformation item : summonsInformations) {
                    if (Long.parseLong(startDate) <= Long.parseLong(item.getReporttime()) && Long.parseLong(endDate) > Long.parseLong(item.getReporttime())) {
                        newSummonsInformations.add(item);
                    }
                }
            }
            if ((startDate == null||startDate=="") && (endDate == null||endDate=="")) {  //都为空
                for (SummonsInformation item : summonsInformations) {
                    newSummonsInformations.add(item);
                }
            }
            List<CiteRecordsModel> citeRecordsModel = new ArrayList<>();
            for (SummonsInformation item : newSummonsInformations
            ) {
                CiteRecordsModel citeRecordsModel1 = new CiteRecordsModel();
                citeRecordsModel1.setName(item.getPersonname());
                //long arrivedTime=(item.getSummontime()).getTime();
                long arrivedTime = Long.parseLong(item.getReporttime());
                citeRecordsModel1.setArrivedTime(Long.toString(arrivedTime));
                PersonAllInformationModel personinformation = superviseService.getPersonInformation(item.getPersonid());
                citeRecordsModel1.setIdCardNo(personinformation.getCard());
                citeRecordsModel1.setRecordPerson(personinformation.getSponsor());
                citeRecordsModel1.setArrivedUnit(personinformation.getPolicestation());
                citeRecordsModel.add(citeRecordsModel1);
            }

            if (citeRecordsModel.size() > count && citeRecordsModel.size() < count + requestCount) {
                for (int i = count; i < citeRecordsModel.size(); i++) {
                    CiteRecordsModel summonsInformation = citeRecordsModel.get(i);
                    summonsInformations1.add(summonsInformation);
                }
            }
            if (citeRecordsModel.size() >= count + requestCount) {
                for (int i = count; i < count + requestCount; i++) {
                    CiteRecordsModel summonsInformation = citeRecordsModel.get(i);
                    summonsInformations1.add(summonsInformation);
                }
            }
        }
        citeRecordReturnModel.setTotalCount(newSummonsInformations.size());
        citeRecordReturnModel.setList(summonsInformations1);
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=citeRecordReturnModel;
        return rs;
    }

    @UserLoginToken
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

    @UserLoginToken
    @ApiOperation(value = "获取保外人员未到案统计")
    @GetMapping("/getCiteNotArrived")
    public ResultSet getCiteNotArrived(@ApiParam(name="startDate",value = "开始日期时间戳")@RequestParam(required = true)String startDate,
                                       @ApiParam(name="endDate",value = "结束日期时间戳")@RequestParam(required = true)String endDate,
                                       @ApiParam(name="count",value = "当前已获取的数据条数")@RequestParam(required = true)int count,
                                       @ApiParam(name="requestCount",value = "请求获取数据的条数")@RequestParam(required = true)int requestCount,
                                       @ApiParam(name="key",value = "搜索关键字")@RequestParam(required = false)String key) {
        String userId= TokenUtil.getTokenUserId();
//        int locationViolateSlight=superviseService.listViolationFensInformation("脱离管控区域","1");  //上报设置中位置轻微违规次数
//        int locationViolateSerious =superviseService.listViolationFensInformation("脱离管控区域","2"); //上报设置中位置严重违规次数
//        int summonsViolateSlight=superviseService.listViolationFensInformation("传讯取证未报到","1");   //上报设置中传讯轻微违规次数
//        int summonsViolateSerious=superviseService.listViolationFensInformation("传讯取证未报到","2");  //上报设置中传讯严重违规次数
//        int faceSinginSlight =superviseService.listViolationFensInformation("视频签到缺勤","1");    //上报设置中人脸签到轻微违规次数
//        int faceSinginSerious =superviseService.listViolationFensInformation("视频签到缺勤","2");   //上报设置中人脸签到严重违规次数
//        int voiceSinginSlight =superviseService.listViolationFensInformation("语音签到缺勤","1");   //上报设置中声纹签到轻微违规次数
//        int voiceSinginSerious =superviseService.listViolationFensInformation("语音签到缺勤","2");  //上报设置中声纹签到严重违规次数
        SuperviseReturnModel superviseReturnModel=new SuperviseReturnModel();
        try{
        List<SummonsInformation> summonsInformations = superviseService.listNotCiteRecord();
        List<SummonsInformation> newSummonsInformations = new ArrayList<>();     //筛选规定时间内的传讯数据
        for (SummonsInformation item : summonsInformations
        ) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d=sdf.parse(item.getSummonsbegintime());
            if (Long.parseLong(String.valueOf(d.getTime())) >= Long.parseLong(startDate) && Long.parseLong(String.valueOf(d.getTime())) < Long.parseLong(endDate)) {
                newSummonsInformations.add(item);
            }
        }
        List<SummonsInformation> newTemp = new ArrayList<>();    //筛选重复数据
            if(newSummonsInformations.size()!=0) {
                newTemp.add(newSummonsInformations.get(0));
                for (int i = 0; i < newSummonsInformations.size(); i++) {
                    SummonsInformation listTemp = newSummonsInformations.get(i);
                    int k = 0;
                    for (SummonsInformation item : newTemp
                    ) {
                        if (item.getPersonid().equals(listTemp.getPersonid())) {
                            k = 1;   //k=1，证明有重复数据
                        }
                    }
                    if (k == 0) {
                        newTemp.add(listTemp);
                    }
                }
            }
        //寻找该工作人员管理的监居人员
        List<Personinformation> personinformations=new ArrayList<>();
        for (SummonsInformation item:newTemp
        ) {
            PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(item.getPersonid());
            if(personAllInformationModel.getSponsoralarm().equals(userId)){
                Personinformation personinformation=new Personinformation();
                personinformation.setCode(personAllInformationModel.getPersonid());
                personinformation.setName(personAllInformationModel.getPersonname());
                personinformation.setNumber(personAllInformationModel.getGuid());
                personinformation.setIdCardNo(personAllInformationModel.getCard());
                personinformation.setAge(personAllInformationModel.getAge());
                personinformation.setGender(personAllInformationModel.getGendercode());
                personinformation.setHeadUrl(personAllInformationModel.getFacepath());
                personinformation.setStateCode(personAllInformationModel.getSuspectstatuscode());
                personinformation.setState(personAllInformationModel.getSuspectstatus());
                personinformation.setViolateCode(personAllInformationModel.getViolationcode());
                String violateName=superviseService.getViolateName("WGCD-001",personAllInformationModel.getViolationcode());
                personinformation.setViolate(violateName);
                personinformation.setExecStartDate(personAllInformationModel.getBailoutbegindate());
                personinformation.setExecEndDate(personAllInformationModel.getBailoutenddate());
                personinformation.setPhone(personAllInformationModel.getContact());
                personinformations.add(personinformation);
            }
        }
//            for (Personinformation item:personinformations) {
//
//            }
//        int locationViolateCount=0;   //位置违规次数
//        for (Personinformation item:personinformations
//        ) {
//            List<ReportLocationModel> reportLocationModels = superviseService.listLocation(item.getCode());     //获取位置信息列表
//            List<ReportLocationModel> reportLocationModels1=new ArrayList<>();  //筛选时间内的位置记录
//            for (ReportLocationModel item1:reportLocationModels
//            ) {
//                if(item1.getTimestamp().getTime() >= Long.parseLong(startDate) && item1.getTimestamp().getTime() < Long.parseLong(endDate)){
//                    reportLocationModels1.add(item1);
//                }
//            }
//            for (int j = 0; j < reportLocationModels1.size(); j++) {         //计算位置定位违规次数
//                ReportLocationModel reportLocationModel = reportLocationModels1.get(j);
//                boolean Fscope = reportLocationModel.isFscope();
//                if (!reportLocationModel.isFscope()) {     //判断定位位置是否在范围内
//                    locationViolateCount += 1;
//                }
//            }
//            int locationViolateStatus=0;    //违规状态为正常
//            if(locationViolateCount>=locationViolateSlight&&locationViolateCount<locationViolateSerious){   //位置违规次数
//                locationViolateStatus=1;   //违规状态为轻微
//            }
//            if(locationViolateCount>=locationViolateSerious){
//                locationViolateStatus=2;  //违规状态为严重
//            }
//            List<SummonsInformation> personSummonsInformation=new ArrayList<>();
//            for (SummonsInformation item2:newSummonsInformations
//            ) {
//                if(item2.getPersonid()==item.getCode()){
//                    personSummonsInformation.add(item2);
//                }
//            }
//            int summonsViolateTimes=0;    //传讯违规次数
//            for (SummonsInformation item3:personSummonsInformation
//            ) {
//                if(item3.getReporttime()==null){
//                    summonsViolateTimes++;
//                }
//            }
//            int summonsViolateStatus=0;   //传讯违规状态
//            if(summonsViolateTimes>=summonsViolateSlight&&summonsViolateTimes<summonsViolateSerious){
//                summonsViolateStatus=1;
//            }
//            if(summonsViolateTimes>=summonsViolateSerious){
//                summonsViolateStatus=2;
//            }
//            List<SinginInformation> faceSinginInformations=superviseService.listPersonSingin(item.getCode(),0);   //获取人脸签到记录
//            List<SinginInformation> faceSinginInformations1=new ArrayList<>();
//            for (SinginInformation item4:faceSinginInformations
//            ) {
//                if(item4.getCreatetime().getTime() >= Long.parseLong(startDate) && item4.getCreatetime().getTime() < Long.parseLong(endDate)){
//                    faceSinginInformations1.add(item4);
//                }
//            }
//            int faceViolateTimes=0;   //人脸签到违规次数
//            for (SinginInformation item5:faceSinginInformations1
//            ) {
//                if(item5.getResult()==1){     //签到状态为1时是违规状态
//                    faceViolateTimes++;
//                }
//            }
//            int faceViolateStatus=0;   //人脸违规状态
//            if(faceViolateTimes>=faceSinginSlight&&faceViolateTimes<faceSinginSerious){
//                faceViolateStatus=1;
//            }
//            if(faceViolateTimes>=faceSinginSerious){
//                faceViolateStatus=2;
//            }
//            List<SinginInformation> voiceSinginInformations=superviseService.listPersonSingin(item.getCode(),1);   //获取声纹签到记录
//            List<SinginInformation> voiceSinginInformations1=new ArrayList<>();
//            for (SinginInformation item6:voiceSinginInformations
//            ) {
//                if(item6.getCreatetime().getTime() >= Long.parseLong(startDate) && item6.getCreatetime().getTime() < Long.parseLong(endDate)){
//                    voiceSinginInformations1.add(item6);
//                }
//            }
//            int voiceViolateTimes=0;   //声纹签到违规次数
//            for (SinginInformation item7:voiceSinginInformations1
//            ) {
//                if(item7.getResult()==1){
//                    voiceViolateTimes++;
//                }
//            }
//            int voiceViolateStatus=0;    //声纹违规状态
//            if(voiceViolateTimes>=voiceSinginSlight&&voiceViolateTimes<voiceSinginSerious){
//                voiceViolateStatus=1;
//            }
//            if(voiceViolateTimes>=voiceSinginSerious){
//                voiceViolateStatus=2;
//            }
//            if(locationViolateStatus==0&&summonsViolateStatus==0&&faceViolateStatus==0&&voiceViolateStatus==0){
//                item.setViolateCode("0");
//                item.setViolate("正常");
//            }
//            if(locationViolateStatus==1||summonsViolateStatus==1||faceViolateStatus==1||voiceViolateStatus==1){
//                item.setViolateCode("1");
//                item.setViolate("轻微");
//            }
//            if(locationViolateStatus==1||summonsViolateStatus==2||faceViolateStatus==2||voiceViolateStatus==2){
//                item.setViolateCode("2");
//                item.setViolate("严重");
//            }
//        }
        if(key==null||key=="") {   //关键字为空

            superviseReturnModel.setTotalCount(personinformations.size());
            superviseReturnModel.setList(personinformations);
        }
        else{    //关键字不为空
            List<Personinformation> personInformationsKey=new ArrayList<>();
            for (Personinformation item:personinformations
                 ) {
                if(item.getName().contains(key)){
                    personInformationsKey.add(item);
                }
            }
            superviseReturnModel.setTotalCount(personInformationsKey.size());
            superviseReturnModel.setList(personInformationsKey);
        }
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=superviseReturnModel;
        }
        catch (Exception e){
            rs.resultCode=1;
            rs.resultMsg=e.getMessage();
            rs.data=null;
        }
        return  rs;
    }

    @UserLoginToken
    @ApiOperation(value = "获取保外人员的外出申请列表")
    @GetMapping("/getApplyLeaveList")
    public ResultSet getApplyLeaveList(@RequestParam(required = true)String status,@RequestParam(required = false)String key,
                                       @RequestParam(required = true)int count,@RequestParam(required = true)int requestCount){
        String userId= TokenUtil.getTokenUserId();
        List<LeaveListModel> leaveListModels=new ArrayList<>();
        if(key==null||key==""){   //没有key关键字
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

        for(int i=0;i<leaveListModels.size();i++){
            LeaveListModel item=leaveListModels.get(i);
            PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformationFromName(item.getApplicant());
            if(personAllInformationModel.getSponsoralarm().equals(userId)){
                long startDate=Long.parseLong(item.getStartTimestamp());
                long endDate=Long.parseLong(item.getEndTimestamp());
                long days = (endDate/1000 - startDate/1000) / (60 * 60 * 24)+1;
                int Days=Integer.parseInt(String.valueOf(days));
                item.setDays(Days);
                String leaveOrder=item.getCode();
                List<AuditorRecordModel> getAuditorList=superviseService.getAuditorList(leaveOrder);
                item.setApplyRecord(getAuditorList);
            }
            else{
                leaveListModels.remove(item);
                i--;
            }
        }
        List<LeaveListModel> newLeaveList=new ArrayList<>();
        if(leaveListModels.size()>count&&leaveListModels.size()<=count+requestCount){
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

    @UserLoginToken
    @ApiOperation(value = "审批保外人员外出申请")
    @PostMapping("/approveApplyLeave")
    public ResultSet approveApplyLeave(@ApiParam(name = "code",value = "外出申请单号")@RequestParam(required = true)String code,
                                       @ApiParam(name = "isApprove",value = "是否通过")@RequestParam(required = true)boolean isApprove){
        String userId=TokenUtil.getTokenUserId();
        LeaveListModel leaveInformation=superviseService.getLeaveInformation(code);
        if(leaveInformation!=null){
            if(leaveInformation.getStatusCode().equals("1")){    //判断该请假单是否为待审批状态
                //String userId=CacheUtils.get("UserId").toString();
                String userName =leaveInformation.getApplicant();
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
                rs.data=new Object();
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

    @UserLoginToken
    @ApiOperation(value = "获取保外人员定位记录")
    @GetMapping("/getLocationRecords")
    public ResultSet listLocationRecords(@ApiParam(name="code",value = "监居人员编号")@RequestParam(required = true)String code,
                                         @ApiParam(name="startDate",value = "开始时间戳")@RequestParam(required = false)String startDate,
                                         @ApiParam(name="endDate",value = "结束时间戳")@RequestParam(required = false)String endDate,
                                         @ApiParam(name="type",value = "类型")@RequestParam(required = true)int type,
                                         @ApiParam(name="count",value = "当前已获取数据条数")@RequestParam(required = true)int count,
                                         @ApiParam(name="requestCount",value = "请求获取的条数")@RequestParam(required = true)int requestCount){
        String userId=TokenUtil.getTokenUserId();
        PersonAllInformationModel personinformation=superviseService.getPersonInformation(code);
        if(personinformation!=null) {
            List<LocationRecordModel> locationRecordModels = superviseService.listLocationRecord(code);  //获取监居人员所有定位信息

            List<LocationRecordModel> newLocationRecords = new ArrayList<>();
            if (startDate != null&&startDate!="" && (endDate == null||endDate=="")) {   //开始时间戳不为空
                for (LocationRecordModel item : locationRecordModels
                ) {
                    if (Long.parseLong(item.getTimestamp()) >= Long.parseLong(startDate)) {
                        newLocationRecords.add(item);
                    }
                }
            }
            if ((startDate == null||startDate=="") && endDate != null&&endDate!="") {   //结束时间戳不为空
                for (LocationRecordModel item : locationRecordModels
                ) {
                    if (Long.parseLong(item.getTimestamp()) < Long.parseLong(endDate)) {
                        newLocationRecords.add(item);
                    }
                }
            }
            if ((startDate == null||startDate=="") && (endDate == null||endDate=="")) {     //都为空
                for (LocationRecordModel item : locationRecordModels
                ) {
                    newLocationRecords.add(item);
                }
            }
            if (startDate != null&&startDate!="" && endDate != null&&endDate!="") {     //都不为空
                for (LocationRecordModel item : locationRecordModels
                ) {
                    if (Long.parseLong(item.getTimestamp()) >= Long.parseLong(startDate) && Long.parseLong(item.getTimestamp()) < Long.parseLong(endDate)) {
                        newLocationRecords.add(item);
                    }
                }
            }

            List<LocationRecordModel> locationRecordModels1 = new ArrayList<>();  //经过类型筛选之后的数据
            if (type == 0) {    //全部数据
                locationRecordModels1 = newLocationRecords;
            }
            if (type == 1) {    //越界数据
                for (LocationRecordModel item : newLocationRecords
                ) {
                    if (item.getIsOutBound()) {
                        locationRecordModels1.add(item);
                    }
                }
            }
            if (type == 2) {   //最近一条数据
                locationRecordModels1.add(newLocationRecords.get(0));
            }

            List<LocationRecordModel> locationRecordModels2 = new ArrayList<>();   //经过count和requestCount筛选后的数据
            if (locationRecordModels1.size() > count && locationRecordModels1.size() <= count + requestCount) {
                for (int i = count; i < locationRecordModels1.size(); i++) {
                    LocationRecordModel summonsInformation = locationRecordModels1.get(i);
                    locationRecordModels2.add(summonsInformation);
                }
            }
            if (locationRecordModels1.size() > count + requestCount) {
                for (int i = count; i < count + requestCount; i++) {
                    LocationRecordModel summonsInformation = locationRecordModels1.get(i);
                    locationRecordModels2.add(summonsInformation);
                }
            }
            String areaFence = superviseService.getAreaFence(code);   //获取监居人员区域围栏
            String areaCode=superviseService.getAreaCode(code);     //获取监居人员区域编码
            List<AreaFenceModel> areaFenceModelList = new ArrayList<>();
            if (areaFence!=null) {   //区域围栏不为空为空
                String[] area = areaFence.split(",");
                for (int i = 0; i < area.length; i = i + 2) {
                    AreaFenceModel areaFenceModel = new AreaFenceModel();
                    areaFenceModel.setLatitude(Float.valueOf(area[i]));
                    areaFenceModel.setLongitude(Float.valueOf(area[i + 1]));
                    areaFenceModelList.add(areaFenceModel);
                }
                for (LocationRecordModel item:locationRecordModels2
                ) {
                    item.setArea(areaFenceModelList);
                }
            }
            else{     //区域围栏为空，传区域编码
                for (LocationRecordModel item:locationRecordModels2
                ) {
                    item.setAreaCode(areaCode);
                }
            }

            LocationRecordReturnModel locationRecordReturnModel = new LocationRecordReturnModel();
            locationRecordReturnModel.setTotalCount(locationRecordModels1.size());
            locationRecordReturnModel.setList(locationRecordModels2);
            rs.resultCode = 0;
            rs.resultMsg = "";
            rs.data = locationRecordReturnModel;
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此监居人员";
            rs.data=null;
        }
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "获取违规记录统计")
    @GetMapping("/getAgainstRule")
    public ResultSet getAgainstRule(@ApiParam(name="code",value = "监居人员编号")@RequestParam(required = true)String code) {
        AgainstRuleModel againstRuleModel1 = new AgainstRuleModel();
        againstRuleModel1.setTypeCode("1");
        againstRuleModel1.setType("脱离管控区域");
        againstRuleModel1.setViolateCode("0");
        againstRuleModel1.setViolate("正常");
        againstRuleModel1.setCount(0);
        AgainstRuleModel againstRuleModel2 = new AgainstRuleModel();
        againstRuleModel2.setTypeCode("2");
        againstRuleModel2.setType("传讯未及时到案");
        againstRuleModel2.setViolateCode("0");
        againstRuleModel2.setViolate("正常");
        againstRuleModel2.setCount(0);
        List<AgainstRuleModel> againstRuleModels = new ArrayList<>();
        againstRuleModels.add(againstRuleModel1);
        againstRuleModels.add(againstRuleModel2);
        AgainstRuleReturnModel againstRuleReturnModel = new AgainstRuleReturnModel();
        againstRuleReturnModel.setTotalCount(0);
        againstRuleReturnModel.setList(againstRuleModels);
        rs.resultCode = 0;
        rs.resultMsg = "";
        rs.data = againstRuleReturnModel;
        return rs;
    }
    @UserLoginToken
    @ApiOperation(value = "获取违规记录列表")
    @GetMapping("/getAgainstRuleList")
    public ResultSet getAgainstRuleList(@ApiParam(name="code",value = "监居人员编号")@RequestParam(required = true)String code,
                                        @ApiParam(name="startTime",value = "开始时间戳")@RequestParam(required = false)String startTime,
                                        @ApiParam(name="endTime",value = "结束时间戳")@RequestParam(required = false)String endTime,
                                        @ApiParam(name="count",value = "已获取数据数")@RequestParam(required = true)int count,
                                        @ApiParam(name="requestCount",value = "请求获取条数")@RequestParam(required = true)int requestCount,
                                        @ApiParam(name="typeCode",value = "违规类型编号")@RequestParam(required = true)String typeCode) {
        SignRecordReturnModel signRecordReturnModel = new SignRecordReturnModel();
        List<SignRecordModel> newSignRecordModelList = new ArrayList<>();
        signRecordReturnModel.setTotalCount(0);
        signRecordReturnModel.setList(newSignRecordModelList);
        rs.resultCode = 0;
        rs.resultMsg = "";
        rs.data = signRecordReturnModel;
        return rs;
    }

//    @UserLoginToken
//    @ApiOperation(value = "获取违规记录统计")
//    @GetMapping("/getAgainstRule")
//    public ResultSet getAgainstRule(@ApiParam(name="code",value = "监居人员编号")@RequestParam(required = true)String code){
//
//        PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(code);
//        if(personAllInformationModel!=null) {
//            List<AgainstRuleModel> againstRuleModels = new ArrayList<>();
//            int locationViolateSlight = superviseService.listViolationFensInformation("脱离管控区域", "1");  //上报设置中位置轻微违规次数
//            int locationViolateSerious = superviseService.listViolationFensInformation("脱离管控区域", "2"); //上报设置中位置严重违规次数
//            int summonsViolateSlight = superviseService.listViolationFensInformation("传讯取证未报到", "1");   //上报设置中传讯轻微违规次数
//            int summonsViolateSerious = superviseService.listViolationFensInformation("传讯取证未报到", "2");  //上报设置中传讯严重违规次数
//
//            List<ReportLocationModel> reportLocationModels = superviseService.listLocation(code);    //获取监居人员定位信息
//            int locationViolateCount = 0;    //位置违规次数
//            for (int j = 0; j < reportLocationModels.size(); j++) {         //计算位置定位违规次数
//                ReportLocationModel reportLocationModel = reportLocationModels.get(j);
//                boolean Fscope = reportLocationModel.isFscope();
//                if (reportLocationModel.isFscope()) {     //判断定位位置是否违规
//                    locationViolateCount += 1;
//                }
//            }
//            AgainstRuleModel againstRuleModel1 = new AgainstRuleModel();
//            againstRuleModel1.setTypeCode("1");
//            againstRuleModel1.setType("越界记录");
//            againstRuleModel1.setViolateCode("0");
//            againstRuleModel1.setViolate("正常");
//            againstRuleModel1.setCount(locationViolateCount);
//            int locationViolateStatus = 0;    //违规状态为正常
//            if (locationViolateCount >= locationViolateSlight && locationViolateCount < locationViolateSerious) {   //位置违规次数
//                locationViolateStatus = 1;   //违规状态为轻微
//                againstRuleModel1.setViolateCode("1");
//                againstRuleModel1.setViolate("轻微");
//            }
//            if (locationViolateCount >= locationViolateSerious) {
//                locationViolateStatus = 2;  //违规状态为严重
//                againstRuleModel1.setViolateCode("2");
//                againstRuleModel1.setViolate("严重");
//            }
//            againstRuleModels.add(againstRuleModel1);
//
//            int summonsViolateTimes = 0;    //传讯违规次数
//            List<SummonsInformation> summonsInformations = superviseService.getSummonsInformation(code);
//            for (SummonsInformation item3 : summonsInformations
//            ) {
//                if (item3.getReporttime() == null) {
//                    summonsViolateTimes++;
//                }
//            }
//            AgainstRuleModel againstRuleModel2 = new AgainstRuleModel();
//            againstRuleModel2.setTypeCode("2");
//            againstRuleModel2.setType("传讯未及时到案记录");
//            againstRuleModel2.setViolateCode("0");
//            againstRuleModel2.setViolate("正常");
//            againstRuleModel2.setCount(summonsViolateTimes);
//            int summonsViolateStatus = 0;   //传讯违规状态
//            if (summonsViolateTimes >= summonsViolateSlight && summonsViolateTimes < summonsViolateSerious) {
//                summonsViolateStatus = 1;
//                againstRuleModel2.setViolateCode("1");
//                againstRuleModel2.setViolate("轻微");
//            }
//            if (summonsViolateTimes >= summonsViolateSerious) {
//                summonsViolateStatus = 2;
//                againstRuleModel2.setViolateCode("2");
//                againstRuleModel2.setViolate("严重");
//            }
//            againstRuleModels.add(againstRuleModel2);
//
//            Collections.sort(againstRuleModels, new Comparator<AgainstRuleModel>() {
//                @Override
//                public int compare(AgainstRuleModel againstRuleModel, AgainstRuleModel t1) {
//                    int i=t1.getCount()-againstRuleModel.getCount();    //按照违规次数排序
//                    if(i==0){     //违规次数相等再按照类型顺序排序
//                        return Integer.parseInt(againstRuleModel.getTypeCode())-Integer.parseInt(t1.getTypeCode());
//                    }
//                    return i;
//                }
//            });
//            AgainstRuleReturnModel againstRuleReturnModel = new AgainstRuleReturnModel();
//            againstRuleReturnModel.setTotalCount(locationViolateCount + summonsViolateTimes);
//            againstRuleReturnModel.setList(againstRuleModels);
//            rs.resultCode = 0;
//            rs.resultMsg = "";
//            rs.data = againstRuleReturnModel;
//        }
//        else
//        {
//            rs.resultCode=1;
//            rs.resultMsg="无此监居人员";
//            rs.data=null;
//        }
//        return rs;
//    }

//    @UserLoginToken
//    @ApiOperation(value = "获取违规记录列表")
//    @GetMapping("/getAgainstRuleList")
//    public ResultSet getAgainstRuleList(@ApiParam(name="code",value = "监居人员编号")@RequestParam(required = true)String code,
//                                        @ApiParam(name="startTime",value = "开始时间戳")@RequestParam(required = false)String startTime,
//                                        @ApiParam(name="endTime",value = "结束时间戳")@RequestParam(required = false)String endTime,
//                                        @ApiParam(name="count",value = "已获取数据数")@RequestParam(required = true)int count,
//                                        @ApiParam(name="requestCount",value = "请求获取条数")@RequestParam(required = true)int requestCount,
//                                        @ApiParam(name="typeCode",value = "违规类型编号")@RequestParam(required = true)String typeCode){
////        int locationViolateSlight = superviseService.listViolationFensInformation("脱离管控区域", "1");  //上报设置中位置轻微违规次数
////        int locationViolateSerious = superviseService.listViolationFensInformation("脱离管控区域", "2"); //上报设置中位置严重违规次数
////        int summonsViolateSlight = superviseService.listViolationFensInformation("传讯取证未报到", "1");   //上报设置中传讯轻微违规次数
////        int summonsViolateSerious = superviseService.listViolationFensInformation("传讯取证未报到", "2");  //上报设置中传讯严重违规次数
//
//        PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(code);
//        if(personAllInformationModel!=null){
//            List<LocationInformation> locationRecordModels=superviseService.listViolateLocationRecord(code);   //获取监居人员越界定位信息
//            List<SummonsInformation> summonsInformations = superviseService.getSummonsInformation(code);   //获取该监居人员传讯记录
//            List<SummonsInformation> summonsViolateInformations=new ArrayList<>();    //获取该监居人员传讯违规记录
//            for (SummonsInformation item : summonsInformations
//            ) {
//                if (item.getReporttime() == null) {
//                    summonsViolateInformations.add(item);
//                }
//            }
//
//            String locationViolateStatus = "0";    //违规状态为正常
//            String locationViolateType="正常";
//            if (locationRecordModels.size() >= locationViolateSlight && locationRecordModels.size() < locationViolateSerious) {   //位置违规次数
//                locationViolateStatus = "1";   //违规状态为轻微
//                locationViolateType="轻微";
//            }
//            if (locationRecordModels.size() >= locationViolateSerious) {
//                locationViolateStatus = "2";  //违规状态为严重
//                locationViolateType="严重";
//            }
//
//            List<AgainstRuleListModel> againstRuleListModels=new ArrayList<>();
//            if(typeCode.equals("1")){
//                for (LocationInformation item:locationRecordModels
//                     ) {
//                    AgainstRuleListModel againstRuleListModel = new AgainstRuleListModel();
//                    againstRuleListModel.setTimestamp(item.getTimestamp());
//                    againstRuleListModel.setAddress(item.getAddress());
//                    againstRuleListModel.setTypeCode("1");
//                    againstRuleListModel.setType("越界记录");
//                    againstRuleListModel.setViolateCode(locationViolateStatus);
//                    againstRuleListModel.setViolate(locationViolateType);
//                    againstRuleListModels.add(againstRuleListModel);
//                }
//            }
//
//            String summonsViolateStatus = "0";   //传讯违规状态
//            String summonsViolateType="正常";    //传讯违规描述
//            if (summonsViolateInformations.size() >= summonsViolateSlight && summonsViolateInformations.size() < summonsViolateSerious) {
//                summonsViolateStatus = "1";
//                summonsViolateType="轻微";
//            }
//            if (summonsViolateInformations.size() >= summonsViolateSerious) {
//                summonsViolateStatus = "2";
//                summonsViolateType="严重";
//            }
//            if(typeCode.equals("2")){
//                for (SummonsInformation item:summonsViolateInformations
//                     ) {
//                    AgainstRuleListModel againstRuleListModel = new AgainstRuleListModel();
//                    againstRuleListModel.setTimestamp(item.getSummontime());
//                    againstRuleListModel.setTypeCode("2");
//                    againstRuleListModel.setType("传讯未及时到案记录");
//                    againstRuleListModel.setViolateCode(summonsViolateStatus);
//                    againstRuleListModel.setViolate(summonsViolateType);
//                    againstRuleListModels.add(againstRuleListModel);
//                }
//            }
//            if(typeCode.equals("0")){
//                for (LocationInformation item:locationRecordModels
//                ) {
//                    AgainstRuleListModel againstRuleListModel = new AgainstRuleListModel();
//                    againstRuleListModel.setTimestamp(item.getTimestamp());
//                    againstRuleListModel.setAddress(item.getAddress());
//                    againstRuleListModel.setTypeCode("1");
//                    againstRuleListModel.setType("越界记录");
//                    againstRuleListModel.setViolateCode(locationViolateStatus);
//                    againstRuleListModel.setViolate(locationViolateType);
//                    againstRuleListModels.add(againstRuleListModel);
//                }
//                for (SummonsInformation item:summonsViolateInformations
//                ) {
//                    AgainstRuleListModel againstRuleListModel = new AgainstRuleListModel();
//                    againstRuleListModel.setTimestamp(item.getSummontime());
//                    againstRuleListModel.setTypeCode("2");
//                    againstRuleListModel.setType("传讯未及时到案记录");
//                    againstRuleListModel.setViolateCode(summonsViolateStatus);
//                    againstRuleListModel.setViolate(summonsViolateType);
//                    againstRuleListModels.add(againstRuleListModel);
//                }
//            }
//
//            List<AgainstRuleListModel> newAgainstRuleListModels=new ArrayList<>();  //经过时间戳筛选之后的数据列表
//            if(startTime!=null&&endTime==null){   //开始时间戳不为空
//                for (AgainstRuleListModel item:againstRuleListModels
//                     ) {
//                    if(Long.parseLong(item.getTimestamp())>=Long.parseLong(startTime)){
//                        newAgainstRuleListModels.add(item);
//                    }
//                }
//            }
//            if(startTime==null&&endTime!=null){   //结束时间戳不为空
//                for (AgainstRuleListModel item:againstRuleListModels
//                ) {
//                    if(Long.parseLong(item.getTimestamp())<Long.parseLong(endTime)){
//                        newAgainstRuleListModels.add(item);
//                    }
//                }
//            }
//            if(startTime==null&endTime==null){   //都为空
//                newAgainstRuleListModels=againstRuleListModels;
//            }
//            if(startTime!=null&&endTime!=null){  //都不为空
//                for (AgainstRuleListModel item:againstRuleListModels
//                ) {
//                    if(Long.parseLong(item.getTimestamp())>=Long.parseLong(startTime)&&Long.parseLong(item.getTimestamp())<Long.parseLong(endTime)){
//                        newAgainstRuleListModels.add(item);
//                    }
//                }
//            }
//
//            List<AgainstRuleListModel> againstRuleListModelList=new ArrayList<>();
//            if (newAgainstRuleListModels.size() > count && newAgainstRuleListModels.size() <= count + requestCount) {
//                for (int i = count; i < newAgainstRuleListModels.size(); i++) {
//                    AgainstRuleListModel summonsInformation = newAgainstRuleListModels.get(i);
//                    againstRuleListModelList.add(summonsInformation);
//                }
//            }
//            if (newAgainstRuleListModels.size() > count + requestCount) {
//                for (int i = count; i < count + requestCount; i++) {
//                    AgainstRuleListModel summonsInformation = newAgainstRuleListModels.get(i);
//                    againstRuleListModelList.add(summonsInformation);
//                }
//            }
//
//            Collections.sort(againstRuleListModelList, new Comparator<AgainstRuleListModel>() {   //排序
//                @Override
//                public int compare(AgainstRuleListModel againstRuleListModel, AgainstRuleListModel t1) {
//                    long a=Long.parseLong(t1.getTimestamp());
//                    long b=Long.parseLong(againstRuleListModel.getTimestamp());
//                    long c=a-b;
//                    return Integer.parseInt(String.valueOf(c));
//                }
//            });
//            AgainstRuleListReturnModel againstRuleListReturnModel=new AgainstRuleListReturnModel();
//            againstRuleListReturnModel.setTotalCount(newAgainstRuleListModels.size());
//            againstRuleListReturnModel.setList(againstRuleListModelList);
//            rs.resultCode=0;
//            rs.resultMsg="";
//            rs.data=againstRuleListReturnModel;
//        }
//        else{
//            rs.resultCode=1;
//            rs.resultMsg="无此监居人员";
//            rs.data=null;
//        }
//        return rs;
//    }

    @UserLoginToken
    @ApiOperation(value = "获取签到记录列表")
    @GetMapping("/getSignRecord")
    public ResultSet getSignRecord(@ApiParam(name="type",value = "签到类型")@RequestParam(required = true)String type,
                                   @ApiParam(name="startDate",value = "开始时间戳")@RequestParam(required = false)String startDate,
                                   @ApiParam(name="endDate",value = "结束时间戳")@RequestParam(required = false)String endDate,
                                   @ApiParam(name="count",value = "当前已获取数据条数")@RequestParam(required = true)int count,
                                   @ApiParam(name="requestCount",value = "请求获取条数")@RequestParam(required = true)int requestCount,
                                   @ApiParam(name="key",value = "关键字")@RequestParam(required = false)String key){
        if(type.equals("0")||type.equals("1")||type.equals("2")) {
            String userId = TokenUtil.getTokenUserId();
            List<SinginInformation> singinInformations = superviseService.listAllSinginInformation();   //获取全部签到数据
            List<SinginInformation> newsinginInformations = new ArrayList<>();    //筛选重复数据
            newsinginInformations.add(singinInformations.get(0));
            for (int i = 0; i < singinInformations.size(); i++) {
                SinginInformation listTemp = singinInformations.get(i);
                int k=0;
                for (SinginInformation item : newsinginInformations
                ) {
                    if (item.getPersonid().equals( listTemp.getPersonid())) {
                        k=1;   //k=1，证明有重复数据
                    }
                }
                if(k==0) {
                    newsinginInformations.add(listTemp);
                }
            }
//            for (SinginInformation item : newsinginInformations   //去除不是该警员管理的监居人员
//            ) {
//                PersonAllInformationModel personAllInformationModel = superviseService.getPersonInformation(item.getPersonid());
//                if (personAllInformationModel.getSponsoralarm().equals(userId)==false) {
//                    newsinginInformations.remove(item);
//                }
//            }

            for(int i=0;i<newsinginInformations.size();i++){
                SinginInformation singinInformation=newsinginInformations.get(i);
                PersonAllInformationModel personAllInformationModel = superviseService.getPersonInformation(singinInformation.getPersonid());
                if (personAllInformationModel.getSponsoralarm().equals(userId)==false) {
                    newsinginInformations.remove(singinInformation);
                    i=i-1;
                }
            }
            List<SinginInformation> listAllFaceSinginInformations = new ArrayList<>();   //所有视频签到记录
            List<SinginInformation> listAllVoiceSinginInformations = new ArrayList<>();  //所有声纹签到记录
            for (SinginInformation item : newsinginInformations
            ) {
                List<SinginInformation> faceSinginInformationList = superviseService.listSingin(item.getPersonid(), 0);  //获取视频签到记录
                for (SinginInformation item1 : faceSinginInformationList
                ) {
                    listAllFaceSinginInformations.add(item1);
                }
                List<SinginInformation> voiceSinginInformationList = superviseService.listSingin(item.getPersonid(), 1);  //获取声纹签到记录
                for (SinginInformation item2 : voiceSinginInformationList
                ) {
                    listAllVoiceSinginInformations.add(item2);
                }
            }

            List<SignRecordModel> signRecordModels = new ArrayList<>();
            if (type.equals("1")) {     //视频签到记录
                for (SinginInformation item : listAllFaceSinginInformations
                ) {
                    PersonAllInformationModel personAllInformationModel = superviseService.getPersonInformation(item.getPersonid());
                    SignRecordModel faceSignRecordModel = new SignRecordModel();
                    faceSignRecordModel.setPerson(personAllInformationModel.getPersonname());
                    faceSignRecordModel.setTimestamp(String.valueOf(item.getCreatetime().getTime()));
                    faceSignRecordModel.setType("1");
                    faceSignRecordModel.setTypeName(item.getTypename());
                    signRecordModels.add(faceSignRecordModel);
                }
            }
            if (type.equals("2")) {   //语音签到记录
                for (SinginInformation item : listAllVoiceSinginInformations
                ) {
                    PersonAllInformationModel personAllInformationModel = superviseService.getPersonInformation(item.getPersonid());
                    SignRecordModel voiceSignRecordModel = new SignRecordModel();
                    voiceSignRecordModel.setPerson(personAllInformationModel.getPersonname());
                    voiceSignRecordModel.setTimestamp(String.valueOf(item.getCreatetime().getTime()));
                    voiceSignRecordModel.setType("2");
                    voiceSignRecordModel.setTypeName(item.getTypename());
                    signRecordModels.add(voiceSignRecordModel);
                }
            }
            if (type.equals("0")) {    //全部记录
                for (SinginInformation item : listAllFaceSinginInformations
                ) {
                    PersonAllInformationModel personAllInformationModel = superviseService.getPersonInformation(item.getPersonid());
                    SignRecordModel faceSignRecordModel = new SignRecordModel();
                    faceSignRecordModel.setPerson(personAllInformationModel.getPersonname());
                    faceSignRecordModel.setTimestamp(String.valueOf(item.getCreatetime().getTime()));
                    faceSignRecordModel.setType("1");
                    faceSignRecordModel.setTypeName(item.getTypename());
                    signRecordModels.add(faceSignRecordModel);
                }
                for (SinginInformation item : listAllVoiceSinginInformations
                ) {
                    PersonAllInformationModel personAllInformationModel = superviseService.getPersonInformation(item.getPersonid());
                    SignRecordModel voiceSignRecordModel = new SignRecordModel();
                    voiceSignRecordModel.setPerson(personAllInformationModel.getPersonname());
                    voiceSignRecordModel.setTimestamp(String.valueOf(item.getCreatetime().getTime()));
                    voiceSignRecordModel.setType("2");
                    voiceSignRecordModel.setTypeName(item.getTypename());
                    signRecordModels.add(voiceSignRecordModel);
                }
            }
            if (startDate != null&&startDate!="" && (endDate == null||endDate=="")) {   //开始时间不为空
                for (SignRecordModel item : signRecordModels
                ) {
                    if (Long.parseLong(item.getTimestamp()) < Long.parseLong(startDate)) {
                        signRecordModels.remove(item);   //时间戳小于开始时间，去除
                    }
                }
            }
            if ((startDate == null||startDate=="") && endDate != null&&endDate!="") {    //结束时间不为空
                for (SignRecordModel item : signRecordModels
                ) {
                    if (Long.parseLong(item.getTimestamp()) >= Long.parseLong(endDate)) {
                        signRecordModels.remove(item);   //时间戳大于等于结束时间，去除
                    }
                }
            }
            if (startDate != null&&startDate!="" && endDate != null&&endDate!="") {     //开始时间和结束时间都不为空
//                for (SignRecordModel item : signRecordModels
//                ) {
//                    if (Long.parseLong(item.getTimestamp()) < Long.parseLong(startDate) || Long.parseLong(item.getTimestamp()) >= Long.parseLong(endDate)) {
//                        signRecordModels.remove(item);  //时间戳小于开始时间或大于等于结束时间，去除
//                    }
//                }
                for(int i=0;i<signRecordModels.size();i++){
                    SignRecordModel item=signRecordModels.get(i);
                    if(Long.parseLong(item.getTimestamp()) < Long.parseLong(startDate) || Long.parseLong(item.getTimestamp()) >= Long.parseLong(endDate)){
                        signRecordModels.remove(item);
                        i=i-1;
                    }
                }
            }
            List<SignRecordModel> newSignRecordModels = new ArrayList<>();
            if (key == null||key=="") {
                newSignRecordModels = signRecordModels;
            }
            if (key != null&&key!="") {
                for (SignRecordModel item : signRecordModels
                ) {
                    if (item.getPerson().contains(key)) {
                        newSignRecordModels.add(item);
                    }
                }
            }
            List<SignRecordModel> newSignRecordModelList = new ArrayList<>();
            if (newSignRecordModels.size() > count && newSignRecordModels.size() <= count + requestCount) {
                for (int i = count; i < newSignRecordModels.size(); i++) {
                    SignRecordModel summonsInformation = newSignRecordModels.get(i);
                    newSignRecordModelList.add(summonsInformation);
                }
            }
            if (newSignRecordModels.size() > count + requestCount) {
                for (int i = count; i < count + requestCount; i++) {
                    SignRecordModel summonsInformation = newSignRecordModels.get(i);
                    newSignRecordModelList.add(summonsInformation);
                }
            }
            Collections.sort(newSignRecordModelList, new Comparator<SignRecordModel>() {
                @Override
                public int compare(SignRecordModel signRecordModel, SignRecordModel t1) {
                    long a=Long.parseLong(t1.getTimestamp());
                    long b=Long.parseLong(signRecordModel.getTimestamp());
                    long c=a-b;
                    return Integer.parseInt(String.valueOf(c));
                }
            });
            SignRecordReturnModel signRecordReturnModel = new SignRecordReturnModel();
            signRecordReturnModel.setTotalCount(signRecordModels.size());
            signRecordReturnModel.setList(newSignRecordModelList);
            rs.resultCode = 0;
            rs.resultMsg = "";
            rs.data = signRecordReturnModel;
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此类型";
            rs.data=null;
        }
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "提交保外人员的管理配置")
    @PostMapping("/submitValidWay")
    public ResultSet submitValidWay(@RequestBody(required = true)ValidWayModel validWay){
        String userName=TokenUtil.getTokenUserId();
        PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(validWay.getCode());
        Date date=new Date();
        if(personAllInformationModel!=null&&personAllInformationModel.getSponsoralarm().equals(userName)){
            String personId=validWay.getCode();
            List<ValidWayListModel> validWayListModels=validWay.getValidWay();
            for (ValidWayListModel item:validWayListModels
                 ) {
                PrisonSettingInformation prisonSettingInformation=superviseService.getPrisonValidSetting(personId,item.getName());
                if(prisonSettingInformation!=null){
                    int status=superviseService.updatePrisonSetting(personId,item.getName(),item.isEnable(),date);
                }
                else{
                    int status=superviseService.insertPrisonSetting(personId,item.getName(),item.isEnable(),date,Integer.parseInt(item.getCode()));
                }
            }
            rs.resultCode=0;
            rs.resultMsg="";
            rs.data=new Object();
        }
        else
        {
            rs.resultCode=1;
            rs.resultMsg="无此监居人员";
            rs.data=null;
        }
        return  rs;
    }

    @UserLoginToken
    @ApiOperation(value = "获取保外人员管理配置")
    @GetMapping("/getValidWay")
    public ResultSet getValidWay(@ApiParam(name="code",value = "监居人员编号")@RequestParam(required = true)String code){
        String userId=TokenUtil.getTokenUserId();
        PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(code);
        if(personAllInformationModel!=null&&personAllInformationModel.getSponsoralarm().equals(userId)){
            List<PrisonSettingModel> prisonSettingModels=superviseService.getPrisonValidWay(code);
            ValidWayReturnModel validWayReturnModel=new ValidWayReturnModel();
            if(prisonSettingModels.size()!=0){
                validWayReturnModel.setTotalCount(prisonSettingModels.size());
                validWayReturnModel.setList(prisonSettingModels);
            }
            else{
                for(int i=1;i<=4;i++){
                    String validWayName=superviseService.getViolateName("GLPZ-001",String.valueOf(i));
                    Date date=new Date();
                    int status=superviseService.insertPrisonSetting(code,validWayName,false,date,i);
                }
                List<PrisonSettingModel> prisonSettingModel=superviseService.getPrisonValidWay(code);
                validWayReturnModel.setTotalCount(prisonSettingModel.size());
                validWayReturnModel.setList(prisonSettingModel);
            }

            rs.resultCode=0;
            rs.resultMsg="";
            rs.data=validWayReturnModel;
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此监居人员";
            rs.data=null;
        }
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "获取保外人员简要信息")
    @GetMapping("/getSuperviseSimple")
    public ResultSet getSuperviseSimple(@ApiParam(name = "code",value = "监居人员编号")@RequestParam(required = true)String code){
        String userId=TokenUtil.getTokenUserId();
        PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(code);
        if(personAllInformationModel.getSponsoralarm().equals(userId)){
            Personinformation personinformation=new Personinformation();
            personinformation.setCode(personAllInformationModel.getPersonid());
            personinformation.setName(personAllInformationModel.getPersonname());
            personinformation.setNumber(personAllInformationModel.getGuid());
            personinformation.setIdCardNo(personAllInformationModel.getCard());
            personinformation.setAge(personAllInformationModel.getAge());
            personinformation.setGender(personAllInformationModel.getGendercode());
            personinformation.setHeadUrl(personAllInformationModel.getFacepath());
            personinformation.setStateCode(personAllInformationModel.getSuspectstatuscode());
            personinformation.setState(personAllInformationModel.getSuspectstatus());
            personinformation.setExecStartDate(personAllInformationModel.getBailoutbegindate());
            personinformation.setExecEndDate(personAllInformationModel.getBailoutenddate());
            personinformation.setPhone(personAllInformationModel.getContact());
            personinformation.setViolateCode(personAllInformationModel.getViolationcode());
            String violateName=superviseService.getViolateName("WGCD-001",personAllInformationModel.getViolationcode());
            personinformation.setViolate(violateName);

//            int locationViolateSlight = superviseService.listViolationFensInformation("脱离管控区域", "1");  //上报设置中位置轻微违规次数
//            int locationViolateSerious = superviseService.listViolationFensInformation("脱离管控区域", "2"); //上报设置中位置严重违规次数
//            int summonsViolateSlight = superviseService.listViolationFensInformation("传讯取证未报到", "1");   //上报设置中传讯轻微违规次数
//            int summonsViolateSerious = superviseService.listViolationFensInformation("传讯取证未报到", "2");  //上报设置中传讯严重违规次数
//            int faceSinginSlight =superviseService.listViolationFensInformation("视频签到缺勤","1");    //上报设置中人脸签到轻微违规次数
//            int faceSinginSerious =superviseService.listViolationFensInformation("视频签到缺勤","2");   //上报设置中人脸签到严重违规次数
//            int voiceSinginSlight =superviseService.listViolationFensInformation("语音签到缺勤","1");   //上报设置中声纹签到轻微违规次数
//            int voiceSinginSerious =superviseService.listViolationFensInformation("语音签到缺勤","2");  //上报设置中声纹签到严重违规次数
//
//            List<ReportLocationModel> reportLocationModels = superviseService.listLocation(code);    //获取监居人员定位信息
//            int locationViolateCount = 0;    //位置违规次数
//            for (int j = 0; j < reportLocationModels.size(); j++) {         //计算位置定位违规次数
//                ReportLocationModel reportLocationModel = reportLocationModels.get(j);
//                boolean Fscope = reportLocationModel.isFscope();
//                if (reportLocationModel.isFscope()) {     //判断定位位置是否违规
//                    locationViolateCount += 1;
//                }
//            }
//            int locationViolateStatus = 0;    //违规状态为正常
//            if (locationViolateCount >= locationViolateSlight && locationViolateCount < locationViolateSerious) {   //位置违规次数
//                locationViolateStatus = 1;   //违规状态为轻微
//            }
//            if (locationViolateCount >= locationViolateSerious) {
//                locationViolateStatus = 2;  //违规状态为严重
//            }
//            int summonsViolateTimes = 0;    //传讯违规次数
//            List<SummonsInformation> summonsInformations = superviseService.getSummonsInformation(code);
//            for (SummonsInformation item3 : summonsInformations
//            ) {
//                if (item3.getReporttime() == null) {
//                    summonsViolateTimes++;
//                }
//            }
//            int summonsViolateStatus = 0;   //传讯违规状态
//            if (summonsViolateTimes >= summonsViolateSlight && summonsViolateTimes < summonsViolateSerious) {
//                summonsViolateStatus = 1;
//            }
//            if (summonsViolateTimes >= summonsViolateSerious) {
//                summonsViolateStatus = 2;
//            }
//            List<SinginInformation> faceSinginInformations=superviseService.listPersonSingin(code,0);   //获取人脸签到记录
//            int faceViolateTimes=0;   //人脸签到违规次数
//            for (SinginInformation item5:faceSinginInformations
//            ) {
//                if(item5.getResult()==1){     //签到状态为1时是违规状态
//                    faceViolateTimes++;
//                }
//            }
//            int faceViolateStatus=0;   //人脸违规状态
//            if(faceViolateTimes>=faceSinginSlight&&faceViolateTimes<faceSinginSerious){
//                faceViolateStatus=1;
//            }
//            if(faceViolateTimes>=faceSinginSerious){
//                faceViolateStatus=2;
//            }
//            List<SinginInformation> voiceSinginInformations=superviseService.listPersonSingin(code,1);   //获取声纹签到记录
//            int voiceViolateTimes=0;   //声纹签到违规次数
//            for (SinginInformation item7:voiceSinginInformations
//            ) {
//                if(item7.getResult()==1){
//                    voiceViolateTimes++;
//                }
//            }
//            int voiceViolateStatus=0;    //声纹违规状态
//            if(voiceViolateTimes>=voiceSinginSlight&&voiceViolateTimes<voiceSinginSerious){
//                voiceViolateStatus=1;
//            }
//            if(voiceViolateTimes>=voiceSinginSerious){
//                voiceViolateStatus=2;
//            }
//            if(locationViolateStatus==0&&summonsViolateStatus==0&&faceViolateStatus==0&&voiceViolateStatus==0){
//                personinformation.setViolateCode("0");
//                personinformation.setViolate("正常");
//            }
//            if(locationViolateStatus==1||summonsViolateStatus==1||faceViolateStatus==1||voiceViolateStatus==1){
//                personinformation.setViolateCode("1");
//                personinformation.setViolate("轻微");
//            }
//            if(locationViolateStatus==1||summonsViolateStatus==2||faceViolateStatus==2||voiceViolateStatus==2){
//                personinformation.setViolateCode("2");
//                personinformation.setViolate("严重");
//            }
            rs.resultCode=0;
            rs.resultMsg="";
            rs.data=personinformation;
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此监居人员";
            rs.data=null;
        }
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "获取保外人员的外出申请单")
    @GetMapping("/getApplyLeave")
    public ResultSet getApplyLeave(@ApiParam(name="code",value = "外出申请单单号")@RequestParam(required = true)String code){
        String userId=TokenUtil.getTokenUserId();
        LeaveListModel leaveListModel=superviseService.getApplyLeave(code);    //根据申请单号获取申请单数据
        if(leaveListModel!=null){
            PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformationFromName(leaveListModel.getApplicant());
            if(personAllInformationModel.getSponsoralarm().equals(userId)){      //判断该申请单的申请人是否为该警员管辖
                long startDate=Long.parseLong(leaveListModel.getStartTimestamp());
                long endDate=Long.parseLong(leaveListModel.getEndTimestamp());
                long days = (endDate/1000 - startDate/1000) / (60 * 60 * 24)+1;
                int Days=Integer.parseInt(String.valueOf(days));
                leaveListModel.setDays(Days);
                List<AuditorRecordModel> getAuditorList=superviseService.getAuditorList(leaveListModel.getCode());
                leaveListModel.setApplyRecord(getAuditorList);
                rs.resultCode=0;
                rs.resultMsg="";
                rs.data=leaveListModel;
            }
            else{
                rs.resultCode=1;
                rs.resultMsg="申请单不存在";
                rs.data=null;
            }
        }
        else {
            rs.resultCode=1;
            rs.resultMsg="申请单不存在";
            rs.data=null;
        }
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = " 获取保外人员详细信息")
    @GetMapping("/getSuperviseDetail")
    public ResultSet getSuperviseDetail(@ApiParam(name = "code",value = "监居人员编号")@RequestParam(required = true)String code){
        PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(code);
        SuperviseBaseInformation superviseBaseInformation=new SuperviseBaseInformation();
//        int locationViolateSlight = superviseService.listViolationFensInformation("脱离管控区域", "1");  //上报设置中位置轻微违规次数
//        int locationViolateSerious = superviseService.listViolationFensInformation("脱离管控区域", "2"); //上报设置中位置严重违规次数
//        int summonsViolateSlight = superviseService.listViolationFensInformation("传讯取证未报到", "1");   //上报设置中传讯轻微违规次数
//        int summonsViolateSerious = superviseService.listViolationFensInformation("传讯取证未报到", "2");  //上报设置中传讯严重违规次数
//        int faceSinginSlight =superviseService.listViolationFensInformation("视频签到缺勤","1");    //上报设置中人脸签到轻微违规次数
//        int faceSinginSerious =superviseService.listViolationFensInformation("视频签到缺勤","2");   //上报设置中人脸签到严重违规次数
//        int voiceSinginSlight =superviseService.listViolationFensInformation("语音签到缺勤","1");   //上报设置中声纹签到轻微违规次数
//        int voiceSinginSerious =superviseService.listViolationFensInformation("语音签到缺勤","2");  //上报设置中声纹签到严重违规次数
//
//        List<ReportLocationModel> reportLocationModels = superviseService.listLocation(code);    //获取监居人员定位信息
//        int locationViolateCount = 0;    //位置违规次数
//        for (int j = 0; j < reportLocationModels.size(); j++) {         //计算位置定位违规次数
//            ReportLocationModel reportLocationModel = reportLocationModels.get(j);
//            boolean Fscope = reportLocationModel.isFscope();
//            if (reportLocationModel.isFscope()) {     //判断定位位置是否违规
//                locationViolateCount += 1;
//            }
//        }
//        int locationViolateStatus = 0;    //违规状态为正常
//        if (locationViolateCount >= locationViolateSlight && locationViolateCount < locationViolateSerious) {   //位置违规次数
//            locationViolateStatus = 1;   //违规状态为轻微
//        }
//        if (locationViolateCount >= locationViolateSerious) {
//            locationViolateStatus = 2;  //违规状态为严重
//        }
//        int summonsViolateTimes = 0;    //传讯违规次数
//        List<SummonsInformation> summonsInformations = superviseService.getSummonsInformation(code);
//        for (SummonsInformation item3 : summonsInformations
//        ) {
//            if (item3.getReporttime() == null) {
//                summonsViolateTimes++;
//            }
//        }
//        int summonsViolateStatus = 0;   //传讯违规状态
//        if (summonsViolateTimes >= summonsViolateSlight && summonsViolateTimes < summonsViolateSerious) {
//            summonsViolateStatus = 1;
//        }
//        if (summonsViolateTimes >= summonsViolateSerious) {
//            summonsViolateStatus = 2;
//        }
//        List<SinginInformation> faceSinginInformations=superviseService.listPersonSingin(code,0);   //获取人脸签到记录
//        int faceViolateTimes=0;   //人脸签到违规次数
//        for (SinginInformation item5:faceSinginInformations
//        ) {
//            if(item5.getResult()==1){     //签到状态为1时是违规状态
//                faceViolateTimes++;
//            }
//        }
//        int faceViolateStatus=0;   //人脸违规状态
//        if(faceViolateTimes>=faceSinginSlight&&faceViolateTimes<faceSinginSerious){
//            faceViolateStatus=1;
//        }
//        if(faceViolateTimes>=faceSinginSerious){
//            faceViolateStatus=2;
//        }
//        List<SinginInformation> voiceSinginInformations=superviseService.listPersonSingin(code,1);   //获取声纹签到记录
//        int voiceViolateTimes=0;   //声纹签到违规次数
//        for (SinginInformation item7:voiceSinginInformations
//        ) {
//            if(item7.getResult()==1){
//                voiceViolateTimes++;
//            }
//        }
//        int voiceViolateStatus=0;    //声纹违规状态
//        if(voiceViolateTimes>=voiceSinginSlight&&voiceViolateTimes<voiceSinginSerious){
//            voiceViolateStatus=1;
//        }
//        if(voiceViolateTimes>=voiceSinginSerious){
//            voiceViolateStatus=2;
//        }
//        if(locationViolateStatus==0&&summonsViolateStatus==0&&faceViolateStatus==0&&voiceViolateStatus==0){
//            superviseBaseInformation.setViolateCode("0");
//            superviseBaseInformation.setViolate("正常");
//        }
//        if(locationViolateStatus==1||summonsViolateStatus==1||faceViolateStatus==1||voiceViolateStatus==1){
//            superviseBaseInformation.setViolateCode("1");
//            superviseBaseInformation.setViolate("轻微");
//        }
//        if(locationViolateStatus==1||summonsViolateStatus==2||faceViolateStatus==2||voiceViolateStatus==2){
//            superviseBaseInformation.setViolateCode("2");
//            superviseBaseInformation.setViolate("严重");
//        }
        String violateName=superviseService.getViolateName("WGCD-001",personAllInformationModel.getViolationcode());
        superviseBaseInformation.setViolate(violateName);
        superviseBaseInformation.setViolateCode(personAllInformationModel.getViolationcode());
        superviseBaseInformation.setCode(personAllInformationModel.getPersonid());
        superviseBaseInformation.setHeadUrl(personAllInformationModel.getFacepath());
        superviseBaseInformation.setStateCode(personAllInformationModel.getSuspectstatuscode());
        superviseBaseInformation.setState(personAllInformationModel.getSuspectstatus());
        superviseBaseInformation.setNumber(personAllInformationModel.getGuid());
        superviseBaseInformation.setIdCardNo(personAllInformationModel.getCard());
        superviseBaseInformation.setName(personAllInformationModel.getPersonname());
        superviseBaseInformation.setPreName(personAllInformationModel.getBeforname());
        superviseBaseInformation.setBirthday(String.valueOf(personAllInformationModel.getBirthdate().getTime()));
        superviseBaseInformation.setAge(personAllInformationModel.getAge());
        superviseBaseInformation.setGender(personAllInformationModel.getGendercode());
        superviseBaseInformation.setNation(personAllInformationModel.getNation());
        superviseBaseInformation.setMaritalStatus(personAllInformationModel.getMarriage());
        superviseBaseInformation.setEducation(personAllInformationModel.getDegreeeducation());
        superviseBaseInformation.setBirthPlace(personAllInformationModel.getNativeplace());
        superviseBaseInformation.setJob(personAllInformationModel.getOccupation());
        superviseBaseInformation.setContact(personAllInformationModel.getContact());
        superviseBaseInformation.setUnit(personAllInformationModel.getWorkunit());
        superviseBaseInformation.setNationality(personAllInformationModel.getNationality());
        superviseBaseInformation.setHouseholdAddressArea(personAllInformationModel.getRegisteredarea());
        superviseBaseInformation.setHouseholdAddress(personAllInformationModel.getPermanentaddress());
        superviseBaseInformation.setResidentAddressArea(personAllInformationModel.getCurrentaddress());
        superviseBaseInformation.setResidentAddress(personAllInformationModel.getNowaddress());

        SuperviseCaseInformation superviseCaseInformation=superviseService.getPersonCaseInformation(code);  //获取监居人员案件基本信息

        SuperviseBailInformation superviseBailInformation=new SuperviseBailInformation();
        superviseBailInformation.setExecStartDate(String.valueOf(personAllInformationModel.getBailoutbegindate().getTime()));
        superviseBailInformation.setExecEndDate(String.valueOf(personAllInformationModel.getBailoutenddate().getTime()));
        superviseBailInformation.setExecUnit(personAllInformationModel.getPolicestation());
        superviseBailInformation.setInChargePerson(personAllInformationModel.getSponsor());
        if(personAllInformationModel.getSuspectstatus().equals("监视居住")==false){
            superviseBailInformation.setExecType(personAllInformationModel.getExectype());
        }
        if(personAllInformationModel.getSuspectstatus().equals("监视居住")){
            superviseBailInformation.setKeepAddress(personAllInformationModel.getMonitoraddress());
            superviseBailInformation.setAppointAddress(personAllInformationModel.getAppointaddress());
        }
        SuperviseBailPersonInformation superviseBailPersonInformation=new SuperviseBailPersonInformation();
        if(personAllInformationModel.getSuspectstatus().equals("监视居住")||personAllInformationModel.getExectype().equals("财保")){
            superviseBailPersonInformation=null;
        }
        else{
            superviseBailPersonInformation=superviseService.getBailPersonInformation(code);
        }
        SuperviseBailMoneyInformation superviseBailMoneyInformation=new SuperviseBailMoneyInformation();
        if(personAllInformationModel.getSuspectstatus().equals("监视居住")||personAllInformationModel.getExectype().equals("人保")){
            superviseBailMoneyInformation=null;
        }
        else{
            superviseBailMoneyInformation=superviseService.getBailMoneyInformation(code);
        }

        SuperviseDetailModel superviseDetailModel=new SuperviseDetailModel();
        superviseDetailModel.setBase(superviseBaseInformation);
        superviseDetailModel.setCaseInfo(superviseCaseInformation);
        superviseDetailModel.setBailInfo(superviseBailInformation);
        superviseDetailModel.setBailPerson(superviseBailPersonInformation);
        superviseDetailModel.setBailMoney(superviseBailMoneyInformation);
        if(personAllInformationModel.getModifiertime()!=null){
            superviseDetailModel.setLastUpdateTime(String.valueOf(personAllInformationModel.getModifiertime().getTime()));
        }
        else{
            superviseDetailModel.setLastUpdateTime(String.valueOf(personAllInformationModel.getFoundertime().getTime()));
        }

        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=superviseDetailModel;
        return rs;
    }
}
