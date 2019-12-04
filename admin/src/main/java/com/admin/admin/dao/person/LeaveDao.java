package com.admin.admin.dao.person;

import com.admin.admin.entity.person.AuditorInformation;
import com.admin.admin.entity.person.LeaveInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LeaveDao {

    LeaveInformation getLeave(@Param("personId") int personId);

    LeaveInformation listLeave();

    //
    int updateLeave(LeaveInformation leaveinformation);

    int insertAuditor(AuditorInformation auditorInformation);

    int delectAuditor(@Param("personId") int personId);
}
