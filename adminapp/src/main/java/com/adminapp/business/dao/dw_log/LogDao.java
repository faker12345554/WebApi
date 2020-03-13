package com.adminapp.business.dao.dw_log;


import com.adminapp.business.entity.dw_log.LogInformation;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LogDao {
    /*
    新增操作日志
     */
    int insertLog(LogInformation logInformation);

}
