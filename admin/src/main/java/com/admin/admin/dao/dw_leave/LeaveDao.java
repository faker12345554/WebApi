package com.admin.admin.dao.dw_leave;

import com.admin.admin.entity.dw_auditor.AuditorInformation;
import com.admin.admin.entity.dw_leave.LeaveInformation;
import com.admin.model.leave.LeavefModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaveDao {
    //列表
    List<LeavefModel> getLeave(@Param("personid") String personid);

//    LeaveInformation listLeave();

//    int updateLeave(LeaveInformation leaveinformation);
    //审核
    int insertAuditor(AuditorInformation auditorInformation);

    LeaveInformation getLeaveInformation(String leaveorder);

    int updateLeaveStatus(AuditorInformation auditorInformation);
    //
    int deleteAuditor(@Param("leaveOrder") String leaveOrder);
    //销假
    int cancelAuditor(String leaveorder);
}
