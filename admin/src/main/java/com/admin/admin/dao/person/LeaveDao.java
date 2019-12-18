package com.admin.admin.dao.person;

import com.admin.admin.entity.person.AuditorInformation;
import com.admin.admin.entity.person.LeaveInformation;
import com.admin.model.leave.LeavefModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaveDao {

    List<LeavefModel> getLeave(@Param("personid") String personid);

    LeaveInformation listLeave();

    int updateLeave(LeaveInformation leaveinformation);

    int insertAuditor(AuditorInformation auditorInformation);

    LeaveInformation getLeaveInformation(AuditorInformation auditorInformation);

    int updateLeaveStatus(AuditorInformation auditorInformation);

    int deleteAuditor(@Param("leaveOrder") String leaveOrder);

    int cancelAuditor(String leaveorder);
}
