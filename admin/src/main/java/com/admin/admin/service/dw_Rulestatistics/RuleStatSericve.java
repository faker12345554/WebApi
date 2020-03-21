package com.admin.admin.service.dw_Rulestatistics;

import com.admin.admin.dao.master.dw_Rulestatistics.RuleStatistics;
import com.admin.admin.entity.dw_message.TMessage;
import com.admin.model.Appstatistics.HomePage;
import com.admin.model.log.LogModel;
import com.admin.tool.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RuleStatSericve {
    @Autowired
    private RuleStatistics ruleStatistics;

    public int getRuuleList(String Code, int level, String StartTime, String EndTime, String Severity) {
        return ruleStatistics.RuleStatisList(Code, level, StartTime, EndTime, Severity);
    }

    //获取未脱离管控区域的人数
    public List<Map<String, String>> getNotout(String Code, String StartTime, String EndTime) {
        return ruleStatistics.getNotout(Code, StartTime, EndTime);
    }

    //首页
    public HomePage Homeindex() {
        return ruleStatistics.Homeindex();
    }

    public List<LogModel> getLoglist(int UserId) {
        return ruleStatistics.getLoglist(UserId);
    }

    public List<TMessage> getSummonsList() {
        return ruleStatistics.getSummonsList(CacheUtils.get("accountname").toString());

    }

    public int updatestatus(String personid,String code){
        return ruleStatistics.updatestatus(personid,code);
    }

    public int getNumber(String type, String Limitedmonth) {
        return ruleStatistics.getNumber(type, Limitedmonth);
    }


}
