package com.admin.admin.dao.dw_task;

import com.admin.admin.entity.dw_message.TMessage;
import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_prisonsetting.TPrisonsetting;
import com.admin.admin.entity.dw_reminder.Remindersettings;
import com.admin.admin.entity.dw_summons.TSummons;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskDao {

    /*
    获取人员信息
     */
    List<Personinformation> GetPerson();

    /*
    获取人员几种管理方式
     */
    List<TPrisonsetting> GetPersonType(String PersonId);

    /*
    查询监居人员当月已做过几次
     */
    int GetSummons(String PersonId);

    /**
     * 生成提醒记录
     * @param tMessage
     * @return
     */
    int SaveMessage(TMessage tMessage);

    /**
     * 新增传讯记录
     * @param tSummons
     * @return
     */
    int SaveSummons(TSummons tSummons);

    /**
     * 获取最近一次传讯记录
     * @param PersonId
     * @return
     */
    TMessage GetMessageByTime(String PersonId,int type);

    /**
     *根据人员id查询
     * @param PersonId
     * @return
     */
     int GetSummonsByPersonId(String PersonId);

    /**
     * 获取配置
     * @param Code
     * @return
     */
     Remindersettings GetConfigure(String Code);







}
