package com.adminapp.business.dao.dw_system;

import com.adminapp.model.dw_system.SubmitAdviceModel;
import com.adminapp.model.dw_system.UpdateInformationModel;
import com.adminapp.model.dw_system.UpdateRecordModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemDao {
    List<UpdateInformationModel> getUpdateInformation(@Param("type")int type);

    List<UpdateRecordModel> getUpdateRecord(@Param("type")int type);

    int submitAdvice(@Param("date")SubmitAdviceModel submitAdviceModel);


}
