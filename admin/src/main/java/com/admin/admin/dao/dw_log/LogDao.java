package com.admin.admin.dao.dw_log;


import com.admin.admin.entity.dw_log.LogInformation;
import com.admin.model.log.LogParamModel;
import com.admin.model.log.LogReturnModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogDao {

    int insertLog(LogInformation logInformation);

    List<LogReturnModel> listLog(LogParamModel logParamModel);

}
