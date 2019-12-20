package com.prisonapp.business.dao.supervise;

import com.prisonapp.business.entity.message.MessageModel;
import com.prisonapp.business.entity.supervise.ApplyRecord;
import com.prisonapp.business.entity.supervise.GetApplyLeaveListModel;
import com.prisonapp.business.entity.supervise.SubmitApplyLeaveModel;
import com.prisonapp.business.entity.supervise.SuperviseModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface SuperviseDao {
    List<GetApplyLeaveListModel> getApplyLeaveList(@Param("statusCode")String  statusCode,@Param("count")int count,@Param("requestCount")int requestCount, @Param("userId")String userId);

    List<SuperviseModel> getTotalApplyLeaveList(@Param("statusCode")String  statusCode, @Param("userId")String userId);

    List<ApplyRecord> applyRecord(@Param("code")String  code);

     int submitApplyLeave(@Param("submitApplyLeaveModel")SubmitApplyLeaveModel submitApplyLeaveModel, @Param("startDate") Date startDate,@Param("endDate") Date endDate, @Param("code") String code, @Param("userId")String userId);
}
