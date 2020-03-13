package com.admin.admin.dao.dw_Rulestatistics;

import com.admin.admin.entity.dw_log.LogInformation;
import com.admin.admin.entity.dw_message.TMessage;
import com.admin.admin.entity.dw_summons.TSummons;
import com.admin.model.Appstatistics.HomePage;
import com.admin.model.log.LogModel;
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

    //首页
    HomePage Homeindex();

    //工作日志
    List<LogModel> getLoglist(int Userid);

    List<TMessage> getSummonsList(String UserName);

    int getNumber(String type,String Limitedmonth);

    //更新人员的严重程度
    int updatestatus(String personid,String code);


}
