package com.admin.admin.dao.dw_Rulestatistics;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RuleStatistics {



    int RuleStatisList(@Param("Code") String Code, @Param("level") int level, @Param("StartTime") String StartTime,
                                            @Param("EndTime") String EndTime,@Param("Severity") String Severity);

    //获得正常的人数
    List<Map<String,String>> getNotout(@Param("Code") String Code,@Param("StartTime") String StartTime, @Param("EndTime") String EndTime);


}
