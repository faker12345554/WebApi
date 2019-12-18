package com.admin.admin.service.dw_leave;

import com.admin.admin.dao.dw_leave.LeaveDao;
import com.admin.admin.entity.dw_auditor.AuditorInformation;
import com.admin.admin.entity.dw_leave.LeaveInformation;
import com.admin.model.leave.LeavefModel;
import com.common.common.result.ResponseResult;
import com.common.common.result.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {
    @Autowired
    private LeaveDao leaveDao;

    private ResponseResult result = new ResponseResult();
    //获取
    public ResponseResult<LeavefModel> getLeave(String personid) {
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result.setData(leaveDao.getLeave(personid));

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

    public ResponseResult insertAuditor(AuditorInformation auditorInformation) {
        LeaveInformation leaveInformations =leaveDao.getLeaveInformation(auditorInformation.getLeaveorder());
        if (leaveInformations != null) {
            int updateLeaveStatus = leaveDao.updateLeaveStatus(auditorInformation);
            result.setCode(ResultCode.SUCCESS.getCode());
            result.setMessage(ResultCode.SUCCESS.getMessage());
            return result.setData(leaveDao.insertAuditor(auditorInformation));
        } else {
            result.setCode(ResultCode.PARAMS_ERROR.getCode());
            result.setMessage(ResultCode.PARAMS_ERROR.getMessage());
            return result.setData("该请假单不存在");
        }

    }


    public int deleteAuditor(String leaveOrder){return leaveDao.deleteAuditor(leaveOrder);}

    public int cancelAuditor(String leaveorder){
        return leaveDao.cancelAuditor(leaveorder);
    }
}
