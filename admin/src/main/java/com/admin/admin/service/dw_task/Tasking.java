package com.admin.admin.service.dw_task;

import com.admin.admin.dao.dw_task.TaskDao;
import com.admin.admin.entity.dw_message.TMessage;
import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_prisonsetting.TPrisonsetting;
import com.admin.admin.entity.dw_reminder.Remindersettings;
import com.admin.admin.entity.dw_summons.TSummons;
import com.common.common.authenticator.CalendarAdjust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class Tasking {
    @Autowired
    private TaskDao taskDao;

    /**
     * 取保监居到期提醒
     */
    public void ReminderBail() throws Exception {

        TMessage message = new TMessage();
        List<Personinformation> list = GetPerson();
        for (Personinformation item : list) {
            long days = CalendarAdjust.getDays(CalendarAdjust.GetYear(new Date()), CalendarAdjust.GetYear(item.getBailoutenddate()));
            System.out.println(CalendarAdjust.getDays(CalendarAdjust.GetYear(new Date()), CalendarAdjust.GetYear(item.getBailoutenddate())));

            if (days == 15) {
                if (item.getSuspectstatus().equals("取保候审")) {
                    message.setModular(6);
                    message.setWorkcontent("监居人员" + item.getPersonname() + "15天后取保候审到期,请提前告知!");
                    message.setPersonid(item.getPersonid());
                    message.setModularname("取保候审到期提醒");
                    message.setReadmessage(false);
                    message.setMessagetime(CalendarAdjust.timeStamp2Date(CalendarAdjust.getNotificationTime()));
                    taskDao.SaveMessage(message);
                } else if (item.getSuspectstatus().equals("监视居住")) {
                    message.setModular(7);
                    message.setWorkcontent("监居人员" + item.getPersonname() + "15天后监视居住到期,请提前告知!");
                    message.setPersonid(item.getPersonid());
                    message.setModularname("监视居住到期提醒");
                    message.setReadmessage(false);
                    message.setMessagetime(CalendarAdjust.timeStamp2Date(CalendarAdjust.getNotificationTime()));
                    taskDao.SaveMessage(message);
                }


            }
        }
    }

    /**
     * 获取取保监居人员
     *
     * @return
     */
    public List<Personinformation> GetPerson() {
        return taskDao.GetPerson();
    }

    /**
     * 生成传讯记录
     */
    public void GeneratedRecord() throws Exception {

        Remindersettings remindersettings = taskDao.GetConfigure("3");
        String[] strArr = null;
        if (remindersettings.getSettingday().indexOf(",") != -1) {
            strArr = remindersettings.getSettingday().split(",");
        }
        TMessage message = new TMessage();
        TSummons summons = new TSummons();
        List<Personinformation> list = GetPerson();
        for (Personinformation item : list) {
            //填写消息内容
            message.setModular(5);
            message.setWorkcontent("监居人员" + item.getPersonname() + "下个月应进行传讯取证,请提前告知!");
            message.setPersonid(item.getPersonid());
            message.setModularname("传讯提醒");
            message.setReadmessage(false);
            //填写传讯信息
            summons.setPersonid(item.getPersonid());
            summons.setPersonname(item.getPersonname());
            if (item.getCasetype().equals("经济案件")) {
                int mondiff = GetMessageByTime(item.getPersonid(), 5);
                if (mondiff != 0) {
                    if (taskDao.GetSummons(item.getPersonid(),CalendarAdjust.GetSummons(1))==0) {


                        if (strArr == null || strArr.length == 0) {
                            message.setMessagetime(
                                    CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(0, Integer.parseInt(remindersettings.getSettingday()))));
                            taskDao.SaveMessage(message);

                        } else {
                            for (int i = 0; i < strArr.length; i++) {
                                message.setMessagetime(
                                        CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(0, Integer.parseInt(strArr[i]))));
                                taskDao.SaveMessage(message);
                            }
                        }
                        summons.setSummontime(CalendarAdjust.GetSummons(1));
                        taskDao.SaveSummons(summons);
                    }
                } else {
                    if (taskDao.GetSummons(item.getPersonid(), CalendarAdjust.GetSummons(2)) == 0) {

                            if (strArr == null || strArr.length == 0) {
                            message.setMessagetime(
                                    CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(1, Integer.parseInt(remindersettings.getSettingday()))));
                            taskDao.SaveMessage(message);
                        } else {
                            for (int i = 0; i < strArr.length; i++) {
                                message.setMessagetime(
                                        CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(1, Integer.parseInt(strArr[i]))));
                                taskDao.SaveMessage(message);
                            }

                        }
                        summons.setSummontime(CalendarAdjust.GetSummons(2));
                        taskDao.SaveSummons(summons);
                    }
                }

            } else if (item.getCasetype().equals("一般案件")){
                int month = GetMessageByTime(item.getPersonid(), 5);
                if (month == 3) {
                    if (taskDao.GetSummons(item.getPersonid(), CalendarAdjust.GetSummons(1)) == 0) {
                        if (strArr == null || strArr.length == 0) {
                            message.setMessagetime(
                                    CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(0, Integer.parseInt(remindersettings.getSettingday()))));
                            taskDao.SaveMessage(message);


                        } else {
                            for (int i = 0; i < strArr.length; i++) {
                                message.setMessagetime(
                                        CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(0, Integer.parseInt(strArr[i]))));
                                taskDao.SaveMessage(message);
                            }

                        }
                        summons.setSummontime(CalendarAdjust.GetSummons(1));
                        taskDao.SaveSummons(summons);
                    }
                } else if (month == 0) {
                    if (taskDao.GetSummons(item.getPersonid(), CalendarAdjust.GetSummons(4)) == 0) {

                        if (strArr == null || strArr.length == 0) {
                            message.setMessagetime(
                                    CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(3, Integer.parseInt(remindersettings.getSettingday()))));
                            taskDao.SaveMessage(message);


                        } else {
                            for (int i = 0; i < strArr.length; i++) {

                                message.setMessagetime(
                                        CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(3, Integer.parseInt(strArr[i]))));
                                taskDao.SaveMessage(message);
                            }

                        }
                        summons.setSummontime(CalendarAdjust.GetSummons(4));

                        taskDao.SaveSummons(summons);
                    }
                }

            }

        }
    }


    /*
    获取每个人有几种监居类型
     */
    public List<TPrisonsetting> GetPersonType(String PersonId) {
        return taskDao.GetPersonType(PersonId);
    }

    /**
     * 获取每个人当前月传讯了几次
     *
     * @param PersonId
     * @return
     */
    public int GetSummons(String PersonId, String Date) {

        return taskDao.GetSummons(PersonId,Date);
    }

    /**
     * 获取最近一次提醒
     *
     * @param PersonId
     * @param type
     * @return
     */

    public int GetMessageByTime(String PersonId, int type) {
        TMessage tMessage = taskDao.GetMessageByTime(PersonId, type);
        if (tMessage == null) {
            return 0;

        } else {
            return CalendarAdjust.getMonthDiff(tMessage.getMessagetime(), new Date());
        }
    }

}
