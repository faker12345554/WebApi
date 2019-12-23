package com.admin.admin.service.dw_leave;

import com.admin.admin.dao.dw_leave.LeaveDao;
import com.admin.admin.entity.dw_auditor.AuditorInformation;
import com.admin.admin.entity.dw_leave.LeaveInformation;
import com.admin.model.leave.LeavefModel;
import com.admin.model.search.SearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {
    @Autowired
    private LeaveDao leaveDao;


    //获取
    public List<LeavefModel> getLeave(SearchModel searchModel) {

        return leaveDao.getLeave(searchModel);

    }

    //获取列表
//    public LeaveInformation listLeave() {
//        return leaveDao.listLeave();
//    }

    //更新
//    public int updateLeave(LeaveInformation leaveinformation) {
//        return leaveDao.updateLeave(leaveinformation);
//    }

//    public LeaveInformation getLeaveInformation(AuditorInformation auditorInformation){
//        return leaveDao.getLeaveInformation(auditorInformation);
//    }

    public int insertAuditor(AuditorInformation auditorInformation) {

        return leaveDao.insertAuditor(auditorInformation);
    }
    public LeaveInformation getLeaveInformation(String leaveorder){
        return leaveDao.getLeaveInformation(leaveorder);
    }
    public int  updateLeaveStatus(AuditorInformation auditorInformation){
        return leaveDao.updateLeaveStatus(auditorInformation);
    }


    public int deleteAuditor(String leaveOrder){return leaveDao.deleteAuditor(leaveOrder);}

    public int cancelAuditor(String leaveorder){
        return leaveDao.cancelAuditor(leaveorder);
    }
}
