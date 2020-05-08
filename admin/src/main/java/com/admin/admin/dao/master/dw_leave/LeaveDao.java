package com.admin.admin.dao.master.dw_leave;

import com.admin.admin.entity.dw_auditor.AuditorInformation;
import com.admin.admin.entity.dw_leave.LeaveInformation;
import com.admin.admin.entity.dw_leave.PersonInformation;
import com.admin.model.leave.LeavefModel;
import com.admin.model.search.SearchModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface LeaveDao {
    //列表
    List<LeavefModel> getLeave(@Param("searchModel") SearchModel searchModel,@Param("type") boolean type,@Param("limit") String limit);

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

    List<PersonInformation> countLeavearea(@Param("city") String city, @Param("area") String area);


    List<PersonInformation> countLeavepersonid(@Param("city") String city, @Param("area") String area);

    List<LeaveInformation> countLeave(@Param("personid") String personid, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<PersonInformation> getPoliceStation(@Param("area") String area);
}
