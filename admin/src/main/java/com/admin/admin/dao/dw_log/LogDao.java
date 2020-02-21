package com.admin.admin.dao.dw_log;


import com.admin.admin.entity.dw_log.LogInformation;
import com.admin.model.log.LogModel;
import com.admin.model.log.LogParamModel;
import com.admin.model.log.LogReturnModel;
import com.admin.model.log.LogSearchModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogDao {

    int insertLog(LogInformation logInformation);

    List<LogReturnModel> listLog(LogParamModel logParamModel);

    List<LogModel> listAllLog(@Param("date")LogSearchModel logSearchModel);

    int deleteLog (@Param("id")int id);
}
