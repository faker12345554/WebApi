package com.admin.admin.dao.dw_leave;

import com.admin.admin.entity.dw_auditor.AuditorInformation;
import com.admin.admin.entity.dw_leave.LeaveInformation;
import com.admin.admin.entity.dw_leave.PersonInformation;
import com.admin.model.leave.LeavefModel;
import com.admin.model.search.SearchModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface LeaveDao {
    //列表
    List<LeavefModel> getLeave(SearchModel searchModel);

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

    List<LeaveInformation> countLeave(@Param("personid") String personid);

    List<PersonInformation> getPoliceStation(@Param("area") String area);
}
