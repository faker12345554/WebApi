package com.admin.admin.dao.person;


import com.admin.admin.entity.person.LogInformation;
import com.admin.model.LogParamModel;
import com.admin.model.LogReturnModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogDao {

    int insertLog(LogInformation logInformation);

    LogReturnModel listLog(LogParamModel logParamModel);

}
