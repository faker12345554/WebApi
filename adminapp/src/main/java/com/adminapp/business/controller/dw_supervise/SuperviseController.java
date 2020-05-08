package com.adminapp.business.controller.dw_supervise;

import com.adminapp.business.entity.dw_supervise.*;
import com.adminapp.business.entity.dw_user.UserModel;
import com.adminapp.business.service.dw_supervise.SuperviseService;
import com.adminapp.config.CacheUtils;
import com.adminapp.config.token.TokenUtil;
import com.adminapp.config.token.tation.UserLoginToken;
import com.adminapp.demo.VoicePrintApi;
import com.adminapp.model.demo.DemoModel;
import com.adminapp.model.dw_supervise.*;
import com.alibaba.fastjson.JSONArray;
import com.common.common.Uploadfiles.Upload;
import com.common.common.apppush.Demo;
import com.common.common.result.ResultSet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(value="工作人员监督Controller",tags={"工作人员监督功能管理"})
@RequestMapping("/app/admin/supervise")
public class SuperviseController {

    @Autowired
    private SuperviseService superviseService;

    @UserLoginToken
    @ApiOperation(value = "获取保外人员列表")
    @GetMapping("/getSuperviseList")
    public ResultSet getSuperviseList(@ApiParam(name = "type",value = "类别") @RequestParam(required = true)int type,@ApiParam(name = "count",value = "当前已经获取的数据条数") @RequestParam(required = true)int count,
                                    @ApiParam(name = "requestCount",value = "请求获取数据的条数") @RequestParam(required = true)int requestCount,@ApiParam(name = "key",value = "搜索关键字") @RequestParam(required = false)String key) {
        ResultSet rs=new ResultSet();
        String userId=TokenUtil.getTokenUserId();
        List<Personinformation> newPerson = new ArrayList<>();  //符合条件所有监居人员列表
        List<Personinformation> newRecentPerson = new ArrayList<>();  //符合条件的最近新增人员列表
        List<Personinformation> newBailoutPerson = new ArrayList<>(); //符合条件的取保候审人员列表
        List<Personinformation> newBailoutWatchPerson = new ArrayList<>(); //符合条件的监视居住人员列表

        List<Personinformation> personinformation = superviseService.listPersonInformation(userId);  //获取该工作人员负责的所有监居人员列表
        SuperviseReturnModel superviseReturnModel = new SuperviseReturnModel();

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
        ResultSet rs=new ResultSet();
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
        ResultSet rs=new ResultSet();
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


    @UserLoginToken
    @ApiOperation(value = "获取保外人员未到案统计")
    @GetMapping("/getCiteNotArrived")
    public ResultSet getCiteNotArrived(@ApiParam(name="startDate",value = "开始日期时间戳")@RequestParam(required = true)String startDate,
                                       @ApiParam(name="endDate",value = "结束日期时间戳")@RequestParam(required = true)String endDate,
                                       @ApiParam(name="count",value = "当前已获取的数据条数")@RequestParam(required = true)int count,
                                       @ApiParam(name="requestCount",value = "请求获取数据的条数")@RequestParam(required = true)int requestCount,
                                       @ApiParam(name="key",value = "搜索关键字")@RequestParam(required = false)String key) {
        ResultSet rs=new ResultSet();
        String userId= TokenUtil.getTokenUserId();
        SuperviseReturnModel superviseReturnModel=new SuperviseReturnModel();
        try{
        List<SummonsInformation> summonsInformations = superviseService.listNotCiteRecord();
        List<SummonsInformation> newSummonsInformations = new ArrayList<>();     //筛选规定时间内的传讯数据
        for (SummonsInformation item : summonsInformations
        ) {
            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //Date d=sdf.parse(item.getSummontime());
            if (Long.parseLong(item.getSummontime()) >= Long.parseLong(startDate) && Long.parseLong(item.getSummontime()) < Long.parseLong(endDate)) {
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
        ResultSet rs=new ResultSet();
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
            LeaveInformation leaveInformation=superviseService.getAllLeaveInformation(item.getCode());
            PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(leaveInformation.getPersonid());
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
                                       @ApiParam(name = "isApprove",value = "是否通过")@RequestParam(required = true)boolean isApprove) throws Exception {
        ResultSet rs=new ResultSet();
        String userId=TokenUtil.getTokenUserId();
        UserModel userModel=superviseService.getUserInformation(userId);
        String policeName=userModel.getAliasname();
        LeaveListModel leaveInformation=superviseService.getLeaveInformation(code);
        LeaveInformation leaveInformation1=superviseService.getAllLeaveInformation(code);
        if(leaveInformation!=null){
            if(leaveInformation.getStatusCode().equals("1")){    //判断该请假单是否为待审批状态
                //String userId=CacheUtils.get("UserId").toString();
                String userName =leaveInformation.getApplicant();
                Date date=new Date();
                //Date afterDate=new Date(date.getTime()+14400000);
                Calendar cal=new GregorianCalendar();
                cal.setTime(date);
                cal.add(Calendar.DATE,1);
                String message=leaveInformation.getReason();
                //String dateTime=new SimpleDateFormat("yyyy-MM-dd").format(leaveInformation.getApplyTimestamp());
                DateFormat dt=new SimpleDateFormat("yyyy-MM-dd");
                String time=dt.format(leaveInformation1.getSubittimestamp());
                String content="";
                if(isApprove){    //审批为通过
                    int updateLeaveInformation=superviseService.updateLeaveInformation(code,"2","审批通过");  //修改请假单信息
                    int insertAuditorInformation=superviseService.insertAuditorInformation(code,userId,policeName,date,message,"2","审批通过");
                    content="您于"+time+"提交的外出申请已审批通过。";

                }
                else{      //审批不通过
                    int updateLeaveInformation=superviseService.updateLeaveInformation(code,"3","审批未通过"); //修改请假单信息
                    int insertAuditorInformation=superviseService.insertAuditorInformation(code,userId,policeName,date,message,"3","审批不通过");
                    content="您于"+time+"提交的外出申请审批未通过，请重新申请或致电民警。";
                }
                int insertPersonMessage=superviseService.insertPersonMessage(4,content,"外出提醒",leaveInformation1.getPersonid(),1,"外出审批通知",leaveInformation1.getLeaveorder());
                Demo demo = new Demo("5dd349b4570df37b6700045e", "4hpqbdi0wpikb7bkwamq4uwnpvkjhebz");
                demo.sendAndroidCustomizedcast(leaveInformation1.getPersonid(),"ReleaseBailCode","外出审批通知",
                        "外出审批通知",content,cal.getTime());
                JSONObject jsonObject=new JSONObject();
                rs.resultCode=0;
                rs.resultMsg="";
                rs.data=jsonObject;
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
        ResultSet rs=new ResultSet();
        String userId=TokenUtil.getTokenUserId();
        PersonAllInformationModel personinformation=superviseService.getPersonInformation(code);
        if(personinformation!=null) {
            List<LocationRecordModel> locationRecordModels = superviseService.listLocationRecord(code);  //获取监居人员所有定位信息

            List<LocationRecordModel> newLocationRecords = new ArrayList<>();
            if (startDate != null && startDate != "" && (endDate == null || endDate == "")) {   //开始时间戳不为空
                for (LocationRecordModel item : locationRecordModels
                ) {
                    if (Long.parseLong(item.getTimestamp()) >= Long.parseLong(startDate)) {
                        newLocationRecords.add(item);
                    }
                }
            }
            if ((startDate == null || startDate == "") && endDate != null && endDate != "") {   //结束时间戳不为空
                for (LocationRecordModel item : locationRecordModels
                ) {
                    if (Long.parseLong(item.getTimestamp()) < Long.parseLong(endDate)) {
                        newLocationRecords.add(item);
                    }
                }
            }
            if ((startDate == null || startDate == "") && (endDate == null || endDate == "")) {     //都为空
                for (LocationRecordModel item : locationRecordModels
                ) {
                    newLocationRecords.add(item);
                }
            }
            if (startDate != null && startDate != "" && endDate != null && endDate != "") {     //都不为空
                for (LocationRecordModel item : locationRecordModels
                ) {
                    if (Long.parseLong(item.getTimestamp()) >= Long.parseLong(startDate) && Long.parseLong(item.getTimestamp()) < Long.parseLong(endDate)) {
                        newLocationRecords.add(item);
                    }
                }
            }

            if (newLocationRecords.size() != 0) {

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
                String areaCode = superviseService.getAreaCode(code);     //获取监居人员区域编码
                List<AreaFenceModel> areaFenceModelList = new ArrayList<>();
                if (areaFence != null) {   //区域围栏不为空为空
                    String[] area = areaFence.split(",");
                    for (int i = 0; i < area.length; i = i + 2) {
                        AreaFenceModel areaFenceModel = new AreaFenceModel();
                        areaFenceModel.setLatitude(Float.valueOf(area[i]));
                        areaFenceModel.setLongitude(Float.valueOf(area[i + 1]));
                        areaFenceModelList.add(areaFenceModel);
                    }
                    for (LocationRecordModel item : locationRecordModels2
                    ) {
                        item.setArea(areaFenceModelList);
                    }
                } else {     //区域围栏为空，传区域编码
                    for (LocationRecordModel item : locationRecordModels2
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
            }else{
                LocationRecordReturnModel locationRecordReturnModel = new LocationRecordReturnModel();
                locationRecordReturnModel.setTotalCount(0);
                List<LocationRecordModel> locationRecordModels2 = new ArrayList<>();
                locationRecordReturnModel.setList(locationRecordModels2);
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data =locationRecordReturnModel;
            }
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此监居人员";
            rs.data=null;
        }
        return rs;
    }

//    @UserLoginToken
//    @ApiOperation(value = "获取违规记录统计")
//    @GetMapping("/getAgainstRule")
//    public ResultSet getAgainstRule(@ApiParam(name="code",value = "监居人员编号")@RequestParam(required = true)String code) {
//        ResultSet rs=new ResultSet();
//        AgainstRuleModel againstRuleModel1 = new AgainstRuleModel();
//        againstRuleModel1.setTypeCode("1");
//        againstRuleModel1.setType("脱离管控区域");
//        againstRuleModel1.setViolateCode("0");
//        againstRuleModel1.setViolate("正常");
//        againstRuleModel1.setCount(0);
//        AgainstRuleModel againstRuleModel2 = new AgainstRuleModel();
//        againstRuleModel2.setTypeCode("2");
//        againstRuleModel2.setType("传讯未及时到案");
//        againstRuleModel2.setViolateCode("0");
//        againstRuleModel2.setViolate("正常");
//        againstRuleModel2.setCount(0);
//        List<AgainstRuleModel> againstRuleModels = new ArrayList<>();
//        againstRuleModels.add(againstRuleModel1);
//        againstRuleModels.add(againstRuleModel2);
//        AgainstRuleReturnModel againstRuleReturnModel = new AgainstRuleReturnModel();
//        againstRuleReturnModel.setTotalCount(0);
//        againstRuleReturnModel.setList(againstRuleModels);
//        rs.resultCode = 0;
//        rs.resultMsg = "";
//        rs.data = againstRuleReturnModel;
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
//                                        @ApiParam(name="typeCode",value = "违规类型编号")@RequestParam(required = true)String typeCode) {
//        ResultSet rs=new ResultSet();
//        SignRecordReturnModel signRecordReturnModel = new SignRecordReturnModel();
//        List<SignRecordModel> newSignRecordModelList = new ArrayList<>();
//        signRecordReturnModel.setTotalCount(0);
//        signRecordReturnModel.setList(newSignRecordModelList);
//        rs.resultCode = 0;
//        rs.resultMsg = "";
//        rs.data = signRecordReturnModel;
//        return rs;
//    }

    @UserLoginToken
    @ApiOperation(value = "获取违规记录统计")
    @GetMapping("/getAgainstRule")
    public ResultSet getAgainstRule(@ApiParam(name="code",value = "监居人员编号")@RequestParam(required = true)String code){
        ResultSet rs=new ResultSet();
        PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(code);
        if(personAllInformationModel!=null) {
            List<AgainstRuleModel> againstRuleModels = new ArrayList<>();
            int locationViolateSlight = superviseService.getViolationSlightFens( "1");  //上报设置中位置轻微违规次数
            int locationViolateSerious = superviseService.getViolationSeriousFens("1"); //上报设置中位置严重违规次数
            int summonsViolateSlight = superviseService.getViolationSlightFens( "2");   //上报设置中传讯轻微违规次数
            int summonsViolateSerious = superviseService.getViolationSeriousFens( "2");  //上报设置中传讯严重违规次数

            List<ReportLocationModel> reportLocationModels = superviseService.listLocation(code);    //获取监居人员定位信息
            int locationViolateCount = 0;    //位置违规次数
            for (int j = 0; j < reportLocationModels.size(); j++) {         //计算位置定位违规次数
                ReportLocationModel reportLocationModel = reportLocationModels.get(j);
                boolean Fscope = reportLocationModel.isFscope();
                if (reportLocationModel.isFscope()) {     //判断定位位置是否违规
                    locationViolateCount += 1;
                }
            }
            AgainstRuleModel againstRuleModel1 = new AgainstRuleModel();
            againstRuleModel1.setTypeCode("1");
            againstRuleModel1.setType("脱离管控区域");
            againstRuleModel1.setViolateCode("0");
            againstRuleModel1.setViolate("正常");
            againstRuleModel1.setCount(locationViolateCount);
            int locationViolateStatus = 0;    //违规状态为正常
            if (locationViolateCount >= locationViolateSlight && locationViolateCount < locationViolateSerious) {   //位置违规次数
                locationViolateStatus = 1;   //违规状态为轻微
                againstRuleModel1.setViolateCode("1");
                againstRuleModel1.setViolate("轻微");
            }
            if (locationViolateCount >= locationViolateSerious) {
                locationViolateStatus = 2;  //违规状态为严重
                againstRuleModel1.setViolateCode("2");
                againstRuleModel1.setViolate("严重");
            }
            againstRuleModels.add(againstRuleModel1);

            int summonsViolateTimes = 0;    //传讯违规次数
            List<SummonsInformation> summonsInformations = superviseService.getSummonsInformation(code);
            for (SummonsInformation item3 : summonsInformations
            ) {
                if (item3.getReporttime() == null||item3.getReporttime().equals("")) {
                    summonsViolateTimes++;
                }
            }
            AgainstRuleModel againstRuleModel2 = new AgainstRuleModel();
            againstRuleModel2.setTypeCode("2");
            againstRuleModel2.setType("传讯未及时到案记录");
            againstRuleModel2.setViolateCode("0");
            againstRuleModel2.setViolate("正常");
            againstRuleModel2.setCount(summonsViolateTimes);
            int summonsViolateStatus = 0;   //传讯违规状态
            if (summonsViolateTimes >= summonsViolateSlight && summonsViolateTimes < summonsViolateSerious) {
                summonsViolateStatus = 1;
                againstRuleModel2.setViolateCode("1");
                againstRuleModel2.setViolate("轻微");
            }
            if (summonsViolateTimes >= summonsViolateSerious) {
                summonsViolateStatus = 2;
                againstRuleModel2.setViolateCode("2");
                againstRuleModel2.setViolate("严重");
            }
            againstRuleModels.add(againstRuleModel2);

            Collections.sort(againstRuleModels, new Comparator<AgainstRuleModel>() {
                @Override
                public int compare(AgainstRuleModel againstRuleModel, AgainstRuleModel t1) {
                    int i=t1.getCount()-againstRuleModel.getCount();    //按照违规次数排序
                    if(i==0){     //违规次数相等再按照类型顺序排序
                        return Integer.parseInt(againstRuleModel.getTypeCode())-Integer.parseInt(t1.getTypeCode());
                    }
                    return i;
                }
            });
            AgainstRuleReturnModel againstRuleReturnModel = new AgainstRuleReturnModel();
            againstRuleReturnModel.setTotalCount(locationViolateCount + summonsViolateTimes);
            againstRuleReturnModel.setList(againstRuleModels);
            rs.resultCode = 0;
            rs.resultMsg = "";
            rs.data = againstRuleReturnModel;
        }
        else
        {
            rs.resultCode=1;
            rs.resultMsg="无此监居人员";
            rs.data=null;
        }
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
                                        @ApiParam(name="typeCode",value = "违规类型编号")@RequestParam(required = true)String typeCode){
        ResultSet rs=new ResultSet();
        int locationViolateSlight = superviseService.getViolationSlightFens( "1");  //上报设置中位置轻微违规次数
        int locationViolateSerious = superviseService.getViolationSeriousFens("1"); //上报设置中位置严重违规次数
        int summonsViolateSlight = superviseService.getViolationSlightFens( "2");   //上报设置中传讯轻微违规次数
        int summonsViolateSerious = superviseService.getViolationSeriousFens( "2");  //上报设置中传讯严重违规次数

        PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(code);
        if(personAllInformationModel!=null){
            List<LocationInformation> locationRecordModels=superviseService.listViolateLocationRecord(code);   //获取监居人员越界定位信息
            List<SummonsInformation> summonsInformations = superviseService.getSummonsInformation(code);   //获取该监居人员传讯记录
            List<SummonsInformation> summonsViolateInformations=new ArrayList<>();    //获取该监居人员传讯违规记录
            for (SummonsInformation item : summonsInformations
            ) {
                if (item.getReporttime() == null||item.getReporttime().equals("")) {
                    summonsViolateInformations.add(item);
                }
            }

            String locationViolateStatus = "0";    //违规状态为正常
            String locationViolateType="正常";
            if (locationRecordModels.size() >= locationViolateSlight && locationRecordModels.size() < locationViolateSerious) {   //位置违规次数
                locationViolateStatus = "1";   //违规状态为轻微
                locationViolateType="轻微";
            }
            if (locationRecordModels.size() >= locationViolateSerious) {
                locationViolateStatus = "2";  //违规状态为严重
                locationViolateType="严重";
            }

            List<AgainstRuleListModel> againstRuleListModels=new ArrayList<>();
            if(typeCode.equals("1")){
                for (LocationInformation item:locationRecordModels
                     ) {
                    AgainstRuleListModel againstRuleListModel = new AgainstRuleListModel();
                    againstRuleListModel.setTimestamp(item.getTimestamp());
                    againstRuleListModel.setAddress(item.getAddress());
                    againstRuleListModel.setTypeCode("1");
                    againstRuleListModel.setType("脱离管控区域");
                    againstRuleListModel.setViolateCode(locationViolateStatus);
                    againstRuleListModel.setViolate(locationViolateType);
                    againstRuleListModels.add(againstRuleListModel);
                }
            }

            String summonsViolateStatus = "0";   //传讯违规状态
            String summonsViolateType="正常";    //传讯违规描述
            if (summonsViolateInformations.size() >= summonsViolateSlight && summonsViolateInformations.size() < summonsViolateSerious) {
                summonsViolateStatus = "1";
                summonsViolateType="轻微";
            }
            if (summonsViolateInformations.size() >= summonsViolateSerious) {
                summonsViolateStatus = "2";
                summonsViolateType="严重";
            }
            if(typeCode.equals("2")){
                for (SummonsInformation item:summonsViolateInformations
                     ) {
                    AgainstRuleListModel againstRuleListModel = new AgainstRuleListModel();
                    againstRuleListModel.setTimestamp(item.getSummontime());
                    againstRuleListModel.setTypeCode("2");
                    againstRuleListModel.setType("传讯未及时到案记录");
                    againstRuleListModel.setViolateCode(summonsViolateStatus);
                    againstRuleListModel.setViolate(summonsViolateType);
                    againstRuleListModels.add(againstRuleListModel);
                }
            }
            if(typeCode.equals("0")){
                for (LocationInformation item:locationRecordModels
                ) {
                    AgainstRuleListModel againstRuleListModel = new AgainstRuleListModel();
                    againstRuleListModel.setTimestamp(item.getTimestamp());
                    againstRuleListModel.setAddress(item.getAddress());
                    againstRuleListModel.setTypeCode("1");
                    againstRuleListModel.setType("越界记录");
                    againstRuleListModel.setViolateCode(locationViolateStatus);
                    againstRuleListModel.setViolate(locationViolateType);
                    againstRuleListModels.add(againstRuleListModel);
                }
                for (SummonsInformation item:summonsViolateInformations
                ) {
                    AgainstRuleListModel againstRuleListModel = new AgainstRuleListModel();
                    againstRuleListModel.setTimestamp(item.getSummontime());
                    againstRuleListModel.setTypeCode("2");
                    againstRuleListModel.setType("传讯未及时到案记录");
                    againstRuleListModel.setViolateCode(summonsViolateStatus);
                    againstRuleListModel.setViolate(summonsViolateType);
                    againstRuleListModels.add(againstRuleListModel);
                }
            }

            List<AgainstRuleListModel> newAgainstRuleListModels=new ArrayList<>();  //经过时间戳筛选之后的数据列表
            if(startTime!=null&&endTime==null){   //开始时间戳不为空
                for (AgainstRuleListModel item:againstRuleListModels
                     ) {
                    if(Long.parseLong(item.getTimestamp())>=Long.parseLong(startTime)){
                        newAgainstRuleListModels.add(item);
                    }
                }
            }
            if(startTime==null&&endTime!=null){   //结束时间戳不为空
                for (AgainstRuleListModel item:againstRuleListModels
                ) {
                    if(Long.parseLong(item.getTimestamp())<Long.parseLong(endTime)){
                        newAgainstRuleListModels.add(item);
                    }
                }
            }
            if(startTime==null&endTime==null){   //都为空
                newAgainstRuleListModels=againstRuleListModels;
            }
            if(startTime!=null&&endTime!=null){  //都不为空
                for (AgainstRuleListModel item:againstRuleListModels
                ) {
                    if(Long.parseLong(item.getTimestamp())>=Long.parseLong(startTime)&&Long.parseLong(item.getTimestamp())<Long.parseLong(endTime)){
                        newAgainstRuleListModels.add(item);
                    }
                }
            }

            List<AgainstRuleListModel> againstRuleListModelList=new ArrayList<>();
            if (newAgainstRuleListModels.size() > count && newAgainstRuleListModels.size() <= count + requestCount) {
                for (int i = count; i < newAgainstRuleListModels.size(); i++) {
                    AgainstRuleListModel summonsInformation = newAgainstRuleListModels.get(i);
                    againstRuleListModelList.add(summonsInformation);
                }
            }
            if (newAgainstRuleListModels.size() > count + requestCount) {
                for (int i = count; i < count + requestCount; i++) {
                    AgainstRuleListModel summonsInformation = newAgainstRuleListModels.get(i);
                    againstRuleListModelList.add(summonsInformation);
                }
            }

            Collections.sort(againstRuleListModelList, new Comparator<AgainstRuleListModel>() {   //排序
                @Override
                public int compare(AgainstRuleListModel againstRuleListModel, AgainstRuleListModel t1) {
                    long a=Long.parseLong(t1.getTimestamp());
                    long b=Long.parseLong(againstRuleListModel.getTimestamp());
                    long c=a-b;
                    return Integer.parseInt(String.valueOf(c));
                }
            });
            AgainstRuleListReturnModel againstRuleListReturnModel=new AgainstRuleListReturnModel();
            againstRuleListReturnModel.setTotalCount(newAgainstRuleListModels.size());
            againstRuleListReturnModel.setList(againstRuleListModelList);
            rs.resultCode=0;
            rs.resultMsg="";
            rs.data=againstRuleListReturnModel;
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此监居人员";
            rs.data=null;
        }
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "获取签到记录列表")
    @GetMapping("/getSignRecord")
    public ResultSet getSignRecord(@ApiParam(name="type",value = "签到类型")@RequestParam(required = true)String type,
                                   @ApiParam(name="startDate",value = "开始时间戳")@RequestParam(required = false)String startDate,
                                   @ApiParam(name="endDate",value = "结束时间戳")@RequestParam(required = false)String endDate,
                                   @ApiParam(name="count",value = "当前已获取数据条数")@RequestParam(required = true)int count,
                                   @ApiParam(name="requestCount",value = "请求获取条数")@RequestParam(required = true)int requestCount,
                                   @ApiParam(name="key",value = "关键字")@RequestParam(required = false)String key,
                                   HttpServletResponse response){
        ResultSet rs=new ResultSet();
        if(type.equals("0")||type.equals("1")||type.equals("2")) {
            String userId = TokenUtil.getTokenUserId();
            List<Personinformation> personinformations=superviseService.listPersonInformation(userId);
//            List<SinginInformation> singinInformations = superviseService.listAllSinginInformation();   //获取全部签到数据
//            List<SinginInformation> newsinginInformations = new ArrayList<>();    //筛选重复数据
//            newsinginInformations.add(singinInformations.get(0));
//            for (int i = 0; i < singinInformations.size(); i++) {
//                SinginInformation listTemp = singinInformations.get(i);
//                int k=0;
//                for (SinginInformation item : newsinginInformations
//                ) {
//                    if (item.getPersonid().equals( listTemp.getPersonid())) {
//                        k=1;   //k=1，证明有重复数据
//                    }
//                }
//                if(k==0) {
//                    newsinginInformations.add(listTemp);
//                }
//            }
//
//            for(int i=0;i<newsinginInformations.size();i++){
//                SinginInformation singinInformation=newsinginInformations.get(i);
//                PersonAllInformationModel personAllInformationModel = superviseService.getPersonInformation(singinInformation.getPersonid());
//                if (personAllInformationModel.getSponsoralarm().equals(userId)==false) {
//                    newsinginInformations.remove(singinInformation);
//                    i=i-1;
//                }
//            }
            List<SinginInformation> listAllFaceSinginInformations = new ArrayList<>();   //所有视频签到记录
            List<SinginInformation> listAllVoiceSinginInformations = new ArrayList<>();  //所有声纹签到记录
            for (Personinformation item : personinformations
            ) {
                List<SinginInformation> faceSinginInformationList = superviseService.listSingin(item.getCode(), 1);  //获取视频签到记录
                for (SinginInformation item1 : faceSinginInformationList
                ) {
                    listAllFaceSinginInformations.add(item1);
                }
                List<SinginInformation> voiceSinginInformationList = superviseService.listSingin(item.getCode(), 2);  //获取声纹签到记录
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
                    long a=Long.parseLong(t1.getTimestamp())/1000;
                    long b=Long.parseLong(signRecordModel.getTimestamp())/1000;
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
        ResultSet rs=new ResultSet();
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
            JSONObject jsonObject=new JSONObject();
            rs.resultCode=0;
            rs.resultMsg="";
            rs.data=jsonObject;
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
        ResultSet rs=new ResultSet();
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
        ResultSet rs=new ResultSet();
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
        ResultSet rs=new ResultSet();
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
        ResultSet rs=new ResultSet();
        PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(code);
        SuperviseBaseInformation superviseBaseInformation=new SuperviseBaseInformation();

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

    @UserLoginToken
    @ApiOperation(value = "获取保外人员是否有语音注册记录")
    @GetMapping("/getVoiceRegister")
    public ResultSet getVoiceRegister(@ApiParam(name = "code",value = "取保人员唯一标识")@RequestParam(required = true)String code){
        ResultSet rs=new ResultSet();
        String userId=TokenUtil.getTokenUserId();
        PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(code);
        if(personAllInformationModel.getSponsoralarm().equals(userId)){
            VoiceRegisterReturnModel voiceRegisterReturnModel=new VoiceRegisterReturnModel();
            voiceRegisterReturnModel.setHasVoice(personAllInformationModel.isHasvoice());
            rs.resultCode=0;
            rs.resultMsg="";
            rs.data=voiceRegisterReturnModel;
        }
        else{
            rs.resultCode=1;
            rs.resultMsg="无此取保监居人员";
            rs.data=null;
        }
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "获取语音签到识别串")
    @GetMapping("/generateVoiceSignNum")
    public ResultSet generateVoiceSignNum(){
        VoiceSignNumReturnModel voiceSignNumReturnModel=new VoiceSignNumReturnModel();
        ResultSet rs=new ResultSet();
        String signCode="SBC";
        String number="";
        Random random=new Random();
        for(int i=0;i<8;i++){
            signCode+=String.valueOf(random.nextInt(10));
            number+=String.valueOf(random.nextInt(10));
        }
        voiceSignNumReturnModel.setSignCode(signCode);
        voiceSignNumReturnModel.setNumber(number);
        rs.resultCode=0;
        rs.resultMsg="";
        rs.data=voiceSignNumReturnModel;
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "保外人员语音签到注册（声纹注册）")
    @PostMapping("/voiceRegister")
    public ResultSet voiceRegister(@RequestParam(required = true)String code, @RequestParam(required = false)MultipartFile voice,
                                   @RequestParam(required = true)String signCode) throws Exception {
        ResultSet rs=new ResultSet();
        Upload upload=new Upload();
        DemoModel demo=new DemoModel();
        PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(code);
        /*
        上传文件至服务器
         */
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String url = System.getProperty("user.dir") + "/../webapps/voiceFile/" + formatter.format(date)+"/";    //保存图片的路径
        File path = new File(url);
        if (!path.exists() && !path.isDirectory()) {
            path.setWritable(true,false);
            path.mkdirs();
        }
        String uploadVoiceFile=upload.upload(url,voice);
        String fileName = voice.getOriginalFilename();
        String serverUrl=demo.getServerUrl();
        String filePath=serverUrl+"//voiceFile//"+ formatter.format(date)+"//"+fileName;
        //String filePath="https://pardon.cnnc626.com:8443//voiceFile//2020-04-03//20200403122416ILgdn.wav";
       //String filePath="https://pardon.cnnc626.com:8443//voiceFile//2020-03-25//123.wav";
        String accessToken=superviseService.getAccessToken();
        String userId=superviseService.getUserId();
        VoicePrintApi voicePrintApi=new VoicePrintApi("203793248", "qhntfvf4x2a582059ji1z9vzlpr9r2cu");
        /*
        获取权限，获取算法列表
         */
        //boolean access=voicePrintApi.getAccess();
        //String algoList=voicePrintApi.getAlgoList();\\
        //String algoList="{\"body\":\"{\\\"data\\\":[{\\\"algo_id\\\":\\\"ivector-1-iv_tx_8k-1\\\",\\\"algo_name\\\":\\\"ivector\\\",\\\"algo_version\\\":1,\\\"model_name\\\":\\\"iv_tx_8k\\\",\\\"model_version\\\":1,\\\"voice_bits_per_sample\\\":16,\\\"voice_sample_rate\\\":8000},{\\\"algo_id\\\":\\\"xvector-1-xv_tx_8k-1\\\",\\\"algo_name\\\":\\\"xvector\\\",\\\"algo_version\\\":1,\\\"model_name\\\":\\\"xv_tx_8k\\\",\\\"model_version\\\":1,\\\"voice_bits_per_sample\\\":16,\\\"voice_sample_rate\\\":8000}],\\\"has_error\\\":false,\\\"error_message\\\":\\\"ok\\\",\\\"error_code\\\":0,\\\"request_id\\\":\\\"7a5509d4-6da5-11ea-b608-00163e02dfac\\\"}\",\"contentType\":\"application/json; charset=utf-8\",\"headers\":{\"Access-Control-Allow-Headers\":\"X-Requested-With,X-Sequence,X-Ca-Key,X-Ca-Secret,X-Ca-Version,X-Ca-Timestamp,X-Ca-Nonce,X-Ca-API-Key,X-Ca-Stage,X-Ca-Client-DeviceId,X-Ca-Client-AppId,X-Ca-Signature,X-Ca-Signature-Headers,X-Ca-Signature-Method,X-Forwarded-For,X-Ca-Date,X-Ca-Request-Mode,Authorization,Content-Type,Accept,Accept-Ranges,Cache-Control,Range,Content-MD5\",\"Access-Control-Allow-Methods\":\"GET,POST,PUT,DELETE,HEAD,OPTIONS,PATCH\",\"Access-Control-Allow-Origin\":\"*\",\"Access-Control-Max-Age\":\"172800\",\"Connection\":\"keep-alive\",\"Content-Length\":\"452\",\"Content-Type\":\"application/json; charset=utf-8\",\"Date\":\"Tue, 24 Mar 2020 08:00:07 GMT\",\"Server\":\"Tengine\",\"X-Ca-Request-Id\":\"994B00D2-8A16-4726-830E-C470B1C71DF3\"},\"requestId\":\"994B00D2-8A16-4726-830E-C470B1C71DF3\",\"statusCode\":200}";
        //String algo_id=JSON.parseArray(JSON.parseObject(JSON.parseObject(algoList).getString("body")).getString("data")).getJSONObject(0).getString("algo_id");
        /*
        创建声纹库
         */
        //String Vpstore=voicePrintApi.createVpstore(algo_id);
        //String Vpstore="{\"body\":\"{\\\"data\\\":{\\\"vpstore_id\\\":\\\"d448c490-c065-4627-b957-fa84b9a7a3cb\\\",\\\"algo_id\\\":\\\"xvector-1-xv_tx_8k-1\\\"},\\\"has_error\\\":false,\\\"error_message\\\":\\\"create vpstroe success\\\",\\\"error_code\\\":0,\\\"request_id\\\":\\\"673e431b-6e62-11ea-b608-00163e02dfac\\\"}\",\"contentType\":\"application/json; charset=utf-8\",\"headers\":{\"Access-Control-Allow-Headers\":\"X-Requested-With,X-Sequence,X-Ca-Key,X-Ca-Secret,X-Ca-Version,X-Ca-Timestamp,X-Ca-Nonce,X-Ca-API-Key,X-Ca-Stage,X-Ca-Client-DeviceId,X-Ca-Client-AppId,X-Ca-Signature,X-Ca-Signature-Headers,X-Ca-Signature-Method,X-Forwarded-For,X-Ca-Date,X-Ca-Request-Mode,Authorization,Content-Type,Accept,Accept-Ranges,Cache-Control,Range,Content-MD5\",\"Access-Control-Allow-Methods\":\"GET,POST,PUT,DELETE,HEAD,OPTIONS,PATCH\",\"Access-Control-Allow-Origin\":\"*\",\"Access-Control-Max-Age\":\"172800\",\"Connection\":\"keep-alive\",\"Content-Length\":\"221\",\"Content-Type\":\"application/json; charset=utf-8\",\"Date\":\"Wed, 25 Mar 2020 06:32:30 GMT\",\"Server\":\"Tengine\",\"X-Ca-Request-Id\":\"E7E4751C-F396-41F7-92AB-0E3C1BC6A2CC\"},\"requestId\":\"E7E4751C-F396-41F7-92AB-0E3C1BC6A2CC\",\"statusCode\":200}";
        //String vpstore_id=JSON.parseObject(JSON.parseObject(JSON.parseObject(Vpstore).getString("body")).getString("data")).getString("vpstore_id");
        try {
        /*
        上传文件
         */
            String file_id = "";
            String uploadFile = voicePrintApi.uploadFile("bailWatch", filePath, 600000, accessToken, userId);
            int uploadStatusCode = Integer.valueOf(JSON.parseObject(uploadFile).getString("statusCode"));
            if (uploadStatusCode == 401) {
                //boolean access=voicePrintApi.getAccess();
                //accessToken=voicePrintApi.getAccess();
                org.json.JSONObject object = voicePrintApi.getAccess();
                accessToken = object.getString("token");
                userId = object.getString("userId");
                if (superviseService.getAccessToken() != null) {
                    int updateAccessToken = superviseService.updateAccessToken(accessToken, userId);
                } else {
                    int insertAccessToken = superviseService.insertAccessToken(accessToken, userId);
                }
                uploadFile = voicePrintApi.uploadFile("bailWatch", filePath, 600000, accessToken, userId);
                file_id = JSON.parseObject(JSON.parseObject(JSON.parseObject(uploadFile).getString("body")).getString("data")).getString("file_id");
            } else {
                file_id = JSON.parseObject(JSON.parseObject(JSON.parseObject(uploadFile).getString("body")).getString("data")).getString("file_id");
            }
            //   String uploadFile1=voicePrintApi.uploadFile("bailWatch",filePath1,600000);
            //String uploadFile="{\"body\":\"{\\\"data\\\":{\\\"bucket\\\":\\\"bailWatch\\\",\\\"file_id\\\":\\\"1585120727418_OlDQuCuVaj_bailWatch\\\"},\\\"has_error\\\":false,\\\"error_message\\\":\\\"Upload success\\\",\\\"error_code\\\":0,\\\"request_id\\\":\\\"ddf0915b-6e68-11ea-b608-00163e02dfac\\\"}\",\"contentType\":\"application/json; charset=utf-8\",\"headers\":{\"Access-Control-Allow-Headers\":\"X-Requested-With,X-Sequence,X-Ca-Key,X-Ca-Secret,X-Ca-Version,X-Ca-Timestamp,X-Ca-Nonce,X-Ca-API-Key,X-Ca-Stage,X-Ca-Client-DeviceId,X-Ca-Client-AppId,X-Ca-Signature,X-Ca-Signature-Headers,X-Ca-Signature-Method,X-Forwarded-For,X-Ca-Date,X-Ca-Request-Mode,Authorization,Content-Type,Accept,Accept-Ranges,Cache-Control,Range,Content-MD5\",\"Access-Control-Allow-Methods\":\"GET,POST,PUT,DELETE,HEAD,OPTIONS,PATCH\",\"Access-Control-Allow-Origin\":\"*\",\"Access-Control-Max-Age\":\"172800\",\"Connection\":\"keep-alive\",\"Content-Length\":\"196\",\"Content-Type\":\"application/json; charset=utf-8\",\"Date\":\"Wed, 25 Mar 2020 07:18:47 GMT\",\"Server\":\"Tengine\",\"X-Ca-Request-Id\":\"2C3171B5-2527-47EB-ACA5-9DC3D1D1176B\"},\"requestId\":\"2C3171B5-2527-47EB-ACA5-9DC3D1D1176B\",\"statusCode\":200}";

            //String file_id1=JSON.parseObject(JSON.parseObject(JSON.parseObject(uploadFile1).getString("body")).getString("data")).getString("file_id");
            String[] file_ID = {file_id};//new String[2];
            //file_ID[0]=file_id;
            //file_ID[1]=file_id1;
            // file_ID[0]=uploadFile;//"1585148195326_dTkhAMTSJu_bailWatch";
            // file_ID[1]=uploadFile1;//"1585148225792_NycNxfaIJT_bailWatch";
        /*
        注册声纹
         */
            String register = voicePrintApi.registerVoicePrint("758001e8-e36b-4539-aaf7-4697b9a767c0", personAllInformationModel.getContact(), file_ID, true, accessToken, userId);
            //String register="{\"body\":\"{\\\"data\\\":{},\\\"has_error\\\":false,\\\"error_message\\\":\\\"voice print register success\\\",\\\"error_code\\\":0,\\\"request_id\\\":\\\"204ca554-6f35-11ea-b608-00163e02dfac\\\"}\",\"contentType\":\"application/json; charset=utf-8\",\"headers\":{\"Access-Control-Allow-Headers\":\"X-Requested-With,X-Sequence,X-Ca-Key,X-Ca-Secret,X-Ca-Version,X-Ca-Timestamp,X-Ca-Nonce,X-Ca-API-Key,X-Ca-Stage,X-Ca-Client-DeviceId,X-Ca-Client-AppId,X-Ca-Signature,X-Ca-Signature-Headers,X-Ca-Signature-Method,X-Forwarded-For,X-Ca-Date,X-Ca-Request-Mode,Authorization,Content-Type,Accept,Accept-Ranges,Cache-Control,Range,Content-MD5\",\"Access-Control-Allow-Methods\":\"GET,POST,PUT,DELETE,HEAD,OPTIONS,PATCH\",\"Access-Control-Allow-Origin\":\"*\",\"Access-Control-Max-Age\":\"172800\",\"Connection\":\"keep-alive\",\"Content-Length\":\"143\",\"Content-Type\":\"application/json; charset=utf-8\",\"Date\":\"Thu, 26 Mar 2020 07:40:55 GMT\",\"Server\":\"Tengine\",\"X-Ca-Request-Id\":\"2AC3BCD1-9B6D-4BA6-861E-B0D7A8726E0C\"},\"requestId\":\"2AC3BCD1-9B6D-4BA6-861E-B0D7A8726E0C\",\"statusCode\":200}";
            int statusCode = Integer.valueOf(JSON.parseObject(register).getString("statusCode"));
            //int statusCode=200;
            if (statusCode == 200) {
                JSONObject jsonObject=new JSONObject();
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = jsonObject;
            } else {
                rs.resultCode = 1;
                rs.resultMsg = "注册失败";
                rs.data = null;
            }
        }catch (Exception e){
            rs.resultCode=1;
            rs.resultMsg=e.getMessage();
            rs.data=null;
        }
        return rs;
    }

    @UserLoginToken
    @ApiOperation(value = "保外人员语音签到验证（声纹验证）")
    @PostMapping("/voiceRecognize")
    public ResultSet voiceRecognize(@RequestParam(required = true)String code,@RequestParam(required = false)MultipartFile voice,
                                    @RequestParam(required = true)String signCode) throws Exception {
        ResultSet rs=new ResultSet();
        Upload upload=new Upload();
        DemoModel demo=new DemoModel();
        PersonAllInformationModel personAllInformationModel=superviseService.getPersonInformation(code);
        /*
        上传文件至服务器
         */
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String url = System.getProperty("user.dir") + "/../webapps/voiceValidateFile/" + formatter.format(date)+"/";    //保存图片的路径
        File path = new File(url);
        if (!path.exists() && !path.isDirectory()) {
            path.setWritable(true,false);
            path.mkdirs();
        }
        String uploadVoiceFile=upload.upload(url,voice);
        String fileName = voice.getOriginalFilename();
        String serverUrl=demo.getServerUrl();
        String filePath=serverUrl+"//voiceValidateFile//"+ formatter.format(date)+"//"+fileName;
        String accessToken=superviseService.getAccessToken();
        String userId=superviseService.getUserId();
        //String filePath="https://pardon.cnnc626.com:8443//voiceValidateFile//2020-04-03//20200403164030zazpe.wav";

        try {
            VoicePrintApi voicePrintApi = new VoicePrintApi("203793248", "qhntfvf4x2a582059ji1z9vzlpr9r2cu");
        /*
        上传文件
         */
            //boolean access=voicePrintApi.getAccess();
            String file_id="";
            String uploadFile=voicePrintApi.uploadFile("bailWatch",filePath,600000,accessToken,userId);
            int uploadStatusCode=Integer.valueOf(JSON.parseObject(uploadFile).getString("statusCode"));
            if(uploadStatusCode==401){
                //boolean access=voicePrintApi.getAccess();
                //accessToken=voicePrintApi.getAccess();
                org.json.JSONObject object=voicePrintApi.getAccess();
                accessToken=object.getString("token");
                userId=object.getString("userId");
                if(superviseService.getAccessToken()!=null){
                    int updateAccessToken=superviseService.updateAccessToken(accessToken,userId);
                }else
                {
                    int insertAccessToken=superviseService.insertAccessToken(accessToken,userId);
                }
                uploadFile=voicePrintApi.uploadFile("bailWatch",filePath,600000,accessToken,userId);
                file_id=JSON.parseObject(JSON.parseObject(JSON.parseObject(uploadFile).getString("body")).getString("data")).getString("file_id");
            }
            else{
                file_id=JSON.parseObject(JSON.parseObject(JSON.parseObject(uploadFile).getString("body")).getString("data")).getString("file_id");
            }
            //String file_id = JSON.parseObject(JSON.parseObject(JSON.parseObject(uploadFile).getString("body")).getString("data")).getString("file_id");
            //String file_id="1585276267323_iqIKuXvLnJ_bailWatch";
        /*
        获取声纹列表
         */
            String voicePrintList = voicePrintApi.getVoicePrintList("758001e8-e36b-4539-aaf7-4697b9a767c0", 0, 0,accessToken,userId);
            //String voicePrintList="{\"body\":\"{\\\"data\\\":[{\\\"vp_store_id\\\":\\\"758001e8-e36b-4539-aaf7-4697b9a767c0\\\",\\\"voice_print_id\\\":\\\"SW12345670\\\"}],\\\"has_error\\\":false,\\\"error_message\\\":\\\"ok\\\",\\\"error_code\\\":0,\\\"request_id\\\":\\\"133ec247-6fd3-11ea-b608-00163e02dfac\\\"}\",\"contentType\":\"application/json; charset=utf-8\",\"headers\":{\"Access-Control-Allow-Headers\":\"X-Requested-With,X-Sequence,X-Ca-Key,X-Ca-Secret,X-Ca-Version,X-Ca-Timestamp,X-Ca-Nonce,X-Ca-API-Key,X-Ca-Stage,X-Ca-Client-DeviceId,X-Ca-Client-AppId,X-Ca-Signature,X-Ca-Signature-Headers,X-Ca-Signature-Method,X-Forwarded-For,X-Ca-Date,X-Ca-Request-Mode,Authorization,Content-Type,Accept,Accept-Ranges,Cache-Control,Range,Content-MD5\",\"Access-Control-Allow-Methods\":\"GET,POST,PUT,DELETE,HEAD,OPTIONS,PATCH\",\"Access-Control-Allow-Origin\":\"*\",\"Access-Control-Max-Age\":\"172800\",\"Connection\":\"keep-alive\",\"Content-Length\":\"201\",\"Content-Type\":\"application/json; charset=utf-8\",\"Date\":\"Fri, 27 Mar 2020 02:31:34 GMT\",\"Server\":\"Tengine\",\"X-Ca-Request-Id\":\"00AF9AA5-B453-4205-AA90-2CB2B0CED1CB\"},\"requestId\":\"00AF9AA5-B453-4205-AA90-2CB2B0CED1CB\",\"statusCode\":200}";
            //String vp_store_id = JSON.parseArray(JSON.parseObject(JSON.parseObject(voicePrintList).getString("body")).getString("data")).getJSONObject(0).getString("voice_print_id");
            JSONArray vp_store_id1 = JSON.parseArray(JSON.parseObject(JSON.parseObject(voicePrintList).getString("body")).getString("data"));
            String vp_store_id ="";
            for(int i=0;i<vp_store_id1.size();i++){
                if(JSON.parseArray(JSON.parseObject(JSON.parseObject(voicePrintList).getString("body")).getString("data")).getJSONObject(i).getString("voice_print_id").equals(personAllInformationModel.getContact())){
                    vp_store_id=JSON.parseArray(JSON.parseObject(JSON.parseObject(voicePrintList).getString("body")).getString("data")).getJSONObject(i).getString("voice_print_id");
                    break;
                }
            }
            String[] voicePrintIds = {vp_store_id};
        /*
        验证声纹
         */
            String compareVoicePrint = voicePrintApi.compareVoicePrint("758001e8-e36b-4539-aaf7-4697b9a767c0", file_id, voicePrintIds,accessToken,userId);
            int statusCode = Integer.valueOf(JSON.parseObject(compareVoicePrint).getString("statusCode"));

            if (statusCode == 200) {
                //String voice_print_id = JSON.parseArray(JSON.parseObject(JSON.parseObject(compareVoicePrint).getString("body")).getString("data")).getJSONObject(1).getString("voice_print_id");
                float score = JSON.parseArray(JSON.parseObject(JSON.parseObject(compareVoicePrint).getString("body")).getString("data")).getJSONObject(0).getFloat("score");
                VoiceRecognizeReturnModel voiceRecognizeReturnModel = new VoiceRecognizeReturnModel();
                if(score>0){
                    int updateHasVoice = superviseService.updateHasVoice(code);
                    voiceRecognizeReturnModel.setCode("0");
                    voiceRecognizeReturnModel.setPassed(true);
                    voiceRecognizeReturnModel.setScore(score);
                    voiceRecognizeReturnModel.setUrl(filePath);
                }
                else{
                    voiceRecognizeReturnModel.setCode("0");
                    voiceRecognizeReturnModel.setPassed(false);
                    voiceRecognizeReturnModel.setScore(score);
                    voiceRecognizeReturnModel.setUrl(filePath);
                }
                rs.resultCode = 0;
                rs.resultMsg = "";
                rs.data = voiceRecognizeReturnModel;
            } else {
                rs.resultCode = 1;
                rs.resultMsg = "验证失败";
                rs.data = null;
            }
        }catch (Exception e){
            rs.resultCode=1;
            rs.resultMsg=e.getMessage();
            rs.data=null;
        }
        return rs;
    }

}

