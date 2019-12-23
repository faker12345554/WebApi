package com.prisonapp.business.service.dw_supervise;

import com.prisonapp.business.dao.dw_supervise.SuperviseDao;
import com.prisonapp.business.entity.dw_supervise.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SuperviseService {
    @Autowired
    private SuperviseDao superviseDao ;

    public List<GetApplyLeaveListModel> getApplyLeaveList(String statusCode, int count, int requestCount, String userId){
        return superviseDao.getApplyLeaveList(statusCode,count,requestCount,userId);
    }
    //外出申请列表的总数
    public List<SuperviseModel> getTotalApplyLeaveList(String statusCode, String userId){
        return superviseDao.getTotalApplyLeaveList(statusCode,userId);
    }

    //外出申请列表的第三级
    public List<ApplyRecordModel> applyRecord(String code){
        return superviseDao.applyRecord(code);
    }

    public int submitApplyLeave(SubmitApplyLeaveModel submitApplyLeaveModel, Date startDate,Date endDate, String code, String userId){
        return superviseDao.submitApplyLeave(submitApplyLeaveModel,startDate,endDate,code,userId);
    }

    public List<TReportsettingsModel> getSuperviseTask(){
        return superviseDao.getSuperviseTask();
    }
}
