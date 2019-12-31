package com.admin.admin.service.dw_task;

import com.admin.admin.dao.dw_task.TaskDao;
import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_prisonsetting.TPrisonsetting;
import com.admin.admin.entity.dw_summons.TSummons;
import com.common.common.authenticator.CalendarAdjust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class Tasking {
    @Autowired
    private TaskDao taskDao;

    /**
     * 取保监居到期提醒
     */
    public void ReminderBail() {
        List<Personinformation> list = GetPerson();
        for (Personinformation item : list) {
            long days = CalendarAdjust.getDays(CalendarAdjust.GetYear(new Date()), CalendarAdjust.GetYear(item.getBailoutenddate()));
            System.out.println(CalendarAdjust.getDays(CalendarAdjust.GetYear(new Date()), CalendarAdjust.GetYear(item.getBailoutenddate())));

            if (days >= 0 && days <= 15) {
                if (item.getSuspectstatus() == "取保监居") {

                } else {

                }

            }
        }
    }

    /**
     * 获取取保监居人员
     * @return
     */
    public List<Personinformation> GetPerson(){
        return taskDao.GetPerson();
    }

    /**
     * 生成传讯记录
     */
    public void GeneratedRecord(){
        List<Personinformation> list=GetPerson();
        for (Personinformation item:list){
            if (CalendarAdjust.GetDays()>15){
                return;
            }else if (CalendarAdjust.GetDays()>1 && CalendarAdjust.GetDays() <15){
                TSummons tSummons =new TSummons();
                tSummons.setPersonid(item.getPersonid());
                tSummons.setPersonname(item.getPersonname());
                tSummons.setSummontime(CalendarAdjust.GetSummons());
            }
        }
    }

    /*
    获取每个人有几种监居类型
     */
    public List<TPrisonsetting> GetPersonType(String PersonId){
        return taskDao.GetPersonType(PersonId);
    }

    /**
     * 获取每个人当前月传讯了几次
     * @param PersonId
     * @return
     */
    public int GetSummons(String PersonId){

        return taskDao.GetSummons(PersonId);
    }

}
