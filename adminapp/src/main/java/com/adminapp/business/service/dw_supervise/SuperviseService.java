package com.adminapp.business.service.dw_supervise;

import com.adminapp.business.dao.dw_supervise.SuperfineDado;
import com.adminapp.business.entity.dw_supervise.*;
import com.adminapp.model.dw_supervise.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SuperviseService {

    @Autowired

    private SuperfineDado superfineDado;

    //获取所有监居人员列表
    public List<Personinformation> listPersonInformation(String UserId){
        return superfineDado.listPersonInformation(UserId);
    }

    //获取监居设置列表
    public List<PrisonSettingModel> getPrisonSetting(String personid){
        return superfineDado.getPrisonSetting(personid);
    }

    //获取位置信息列表
    public List<ReportLocationModel> listLocation(String personid){
        return superfineDado.listLocation(personid);
    }

    //获取上报设置列表
    public List<ReportSettingsInformation> listReportSetting(){
        return superfineDado.listReportSetting();
    }

    //获取人脸或声纹签到数据
    public List<SinginInformation> listSingin(String personid,int type){
        return superfineDado.listSingin(personid, type);
    }

    //获取全部传讯记录
    public List<SummonsInformation> listCiteRecord(){
        return superfineDado.listCiteRecord();
    }

    //获取名字含有key的传讯记录
    public List<SummonsInformation> listKeyCiteRecord(String key){
        return superfineDado.listKeyCiteRecord(key);
    }

    public PersonAllInformationModel getPersonInformation(String personid){
        return superfineDado.getPersonInformation(personid);
    }

    //上传传讯记录
//    public int insertCiteRecord(String personid, String personname, Date summonsTime){
//        return superfineDado.insertCiteRecord(personid, personname, summonsTime);
//    }
    public int insertCiteRecord(CiteRecordSubmitModel citeRecordSubmitModel){
        return superfineDado.insertCiteRecord(citeRecordSubmitModel);
    }

    //获取外出申请列表
    public List<LeaveListModel> getLeaveList(){
        return superfineDado.getLeaveList();
    }

    //外出申请单审批状态列表
    public List<AuditorRecordModel> getAuditorList(String leaveOrder){
        return superfineDado.getAuditorList(leaveOrder);
    }

    //根据状态编码查找外出申请记录
    public List<LeaveListModel> listLeaveType(String statusCode){
        return superfineDado.listLeaveType(statusCode);
    }

    //根据key查找对应申请人姓名的申请列表
    public List<LeaveListModel> getKeyLeaveList(String key){
        return superfineDado.getKeyLeaveList(key);
    }

    //根据key和状态编码查找申请记录
    public List<LeaveListModel> listKeyLeaveType(String key,String statusCode){
        return superfineDado.listKeyLeaveType(key, statusCode);
    }

    //根据单号查找具体请假记录
    public LeaveListModel getLeaveInformation(String leaveOrder){
        return superfineDado.getLeaveInformation(leaveOrder);
    }

    //更新请假表状态信息
    public int updateLeaveInformation(String leaveOrder,String statusCode,String status){
        return superfineDado.updateLeaveInformation(leaveOrder, statusCode, status);
    }

    //插入审批记录
    public int insertAuditorInformation(String leaveOrder,String userId,String accountName,Date auditorDateTime,String leavingMessage,String statusCode,String status){
        return superfineDado.insertAuditorInformation(leaveOrder,userId,accountName,auditorDateTime,leavingMessage,statusCode,status);
    }

    //查找所有传讯记录
    public List<ReminderSettingsInformation> listSummonSetting(){
        return superfineDado.listSummonSetting();
    }

    //查找传讯违规级别
    public List<ViolationFensInformation> listViolationFensInformation(String violationName){
        return superfineDado.listViolationFensInformation(violationName);
    }
}
