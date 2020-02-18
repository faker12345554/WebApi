package com.admin.admin.dao.dw_log;


import com.admin.admin.entity.dw_log.LogInformation;
import com.admin.model.Appstatistics.AppStatistics;
import com.admin.model.log.LogParamModel;
import com.admin.model.log.LogReturnModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
    AppStatistics getNumber(String Areacode,String Level);
}
