package com.adminapp.business.service.dw_supervise;

import com.adminapp.business.dao.dw_supervise.SuperfineDado;
import com.adminapp.business.entity.dw_supervise.*;
import com.adminapp.model.dw_supervise.*;
import org.omg.CORBA.PUBLIC_MEMBER;
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

    public PersonAllInformationModel getPersonInformationFromName(String personName){
        return superfineDado.getPersonInformationFromName(personName);
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
    public int listViolationFensInformation(String violationName,String Code){
        return superfineDado.listViolationFensInformation(violationName,Code);
    }

    public List<SinginInformation> listPersonSingin(String personId,int type){
        return superfineDado.listPersonSingin(personId, type);
    }

    //获取个人传讯记录
    public List<SummonsInformation> getSummonsInformation(String personId){
        return superfineDado.getSummonsInformation(personId);
    }

    //获取监居人员区域围栏
    public String getAreaFence(String personId){
        return superfineDado.getAreaFence(personId);
    }

    //获取监居人员区域编码
    public String getAreaCode(String personId){
        return superfineDado.getAreaCode(personId);
    }

    //获取监居人员定位记录
    public List<LocationRecordModel> listLocationRecord(String personId){
        return superfineDado.listLocationRecord(personId);
    }

    //获取监居人员越界定位信息
    public List<LocationInformation> listViolateLocationRecord(String personId){
        return superfineDado.listViolateLocationRecord(personId);
    }

    //获取监居人员所有签到信息
    public List<SinginInformation> listAllSinginInformation(){
        return superfineDado.listAllSinginInformation();
    }

    //获取监居人员监居设置
    public PrisonSettingInformation getPrisonValidSetting(String personId,String validName){
        return superfineDado.getPrisonValidSetting(personId, validName);
    }

    //更新监居人员的监居设置
    public int updatePrisonSetting(String personId,String settingName,boolean settingCheck,Date settingTime){
        return superfineDado.updatePrisonSetting(personId,settingName,settingCheck,settingTime);
    }

    //插入监居人员的监居设置
    public int insertPrisonSetting(String personId,String settingName,boolean settingCheck,Date settingTime,int settingCode){
        return superfineDado.insertPrisonSetting(personId,settingName,settingCheck,settingTime,settingCode);
    }

    //获取监居人员的所有监居配置
    public List<PrisonSettingModel> getPrisonValidWay(String personId){
        return superfineDado.getPrisonValidWay(personId);
    }

    //获取保外人员申请单
    public LeaveListModel getApplyLeave(String code){
        return superfineDado.getApplyLeave(code);
    }

    public SuperviseCaseInformation getPersonCaseInformation(String personId){
        return superfineDado.getPersonCaseInformation(personId);
    }

    //获取监居人员保证人信息
    public SuperviseBailPersonInformation getBailPersonInformation(String personId){
        return superfineDado.getBailPersonInformation(personId);
    }

    //获取监居人员取保金额信息
    public SuperviseBailMoneyInformation getBailMoneyInformation(String personId){
        return superfineDado.getBailMoneyInformation(personId);
    }

    public String getViolateName(String typeCode,String enumCode){
        return superfineDado.getViolateName(typeCode, enumCode);
    }
}
