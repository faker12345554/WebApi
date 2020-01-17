package com.admin.admin.dao.dw_task;

import com.admin.admin.entity.dw_message.TMessage;
import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_prisonsetting.TPrisonsetting;
import com.admin.admin.entity.dw_reminder.Remindersettings;
import com.admin.admin.entity.dw_summons.TSummons;
import com.admin.admin.entity.dw_violation.Violationfens;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskDao {

    /*
    获取人员信息
     */
    List<Personinformation> GetPerson();



    /*
    获取上一次的记录
     */
    TSummons GetSummons(String PersonId);

    /**
     * 获取当前时间段是否已有数据
     * @param
     * @param PersonId
     * @return
     */
    TSummons GetNumber(String PersonId);

    /**
     * 获取每月的提醒记录数
     * @param PersonId
     * @param Date
     * @return
     */
    int GetMessage(@Param("PersonId") String PersonId, @Param("Date") String Date);

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
     * 获取配置
     * @param Code
     * @return
     */
     Remindersettings GetConfigure(String Code);

    /***
     *
     * @return
     */
    Violationfens GetViolationfens(int type);

    /**
     * 统计几次未报到
     * @param PersonId
     * @param date
     * @return
     */
    int StatisticalSummons(String PersonId,String date);
    /**
     * 修改状态
     * @param PersonId
     * @param severity
     * @return
     */
    int UpdateDegree(String PersonId,String severity);









}
