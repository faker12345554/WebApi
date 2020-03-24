package com.admin.admin.dao.master.dw_log;


import com.admin.admin.entity.dw_log.LogInformation;
import com.admin.model.Appstatistics.AppStatistics;
import com.admin.model.log.LogModel;
import com.admin.model.log.LogParamModel;
import com.admin.model.log.LogReturnModel;
import com.admin.model.log.LogSearchModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Mapper
public interface LogDao {
    /*
    新增操作日志
     */
    int insertLog(LogInformation logInformation);
    /*
    查看日志
     */

    List<LogReturnModel> listLog(LogParamModel logParamModel);

    /*
    取保监居APP统计使用量
     */
    AppStatistics getNumber(@Param("Areacode") String Areacode,@Param("Days") String Days);

    /**
     * 脱管率
     * @param Areacode
     * @param Starttime
     * @param Endtime
     * @return
     */
    List<Map<String,String>> Removalrate(@Param("Areacode") String Areacode, @Param("Starttime") String Starttime, @Param("EndTime") String Endtime);

    /**
     *
     * @param Areacode
     * @return
     */
    int gettotelnumber(@Param("Areacode") String Areacode);


    /**
     * 日活动率
     * @param Areacode
     * @param
     * @return
     */
    List<AppStatistics> Solarrate(@Param("Areacode") String Areacode,@Param("Starttime") String Starttime,@Param("EndTime")String EndTime,@Param("level") int level);

    List<LogModel> listAllLog(@Param("date") LogSearchModel logSearchModel);

    int deleteLog (@Param("id")int id);
}
