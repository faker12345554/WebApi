package com.admin.admin.service.dw_task;

import com.admin.admin.dao.dw_task.TaskDao;
import com.admin.admin.entity.dw_message.TMessage;
import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_prisonsetting.TPrisonsetting;
import com.admin.admin.entity.dw_reminder.Remindersettings;
import com.admin.admin.entity.dw_summons.TSummons;
import com.admin.admin.entity.dw_violation.Violationfens;
import com.common.common.authenticator.CalendarAdjust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
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
            // System.out.println(CalendarAdjust.getDays(CalendarAdjust.GetYear(new Date()), CalendarAdjust.GetYear(item.getBailoutenddate())));
            System.out.println(item.getBailoutenddate());
            if (days == 15) {
                System.out.println(item.getPersonid());
                if (item.getSuspectstatus().equals("取保候审")) {
                    System.out.println(item.getPersonid());
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
        TSummons summons = new TSummons();
        List<Personinformation> list = GetPerson();
        for (Personinformation item : list) {
            //填写传讯信息
            summons.setPersonid(item.getPersonid());
            summons.setPersonname(item.getPersonname());
            TSummons tSummons = taskDao.GetSummons(item.getPersonid());
            //判断监居时间是否为null
            int month = 0;
            int year = 0;

            if (item.getBailoutbegindate() != null) {
                System.out.println(CalendarAdjust.getMonthDiff(item.getBailoutbegindate(), new Date()));
                if (CalendarAdjust.getMonthDiff(item.getBailoutbegindate(), new Date()) >= 2) {
                    month = CalendarAdjust.getMonth(CalendarAdjust.GetYear(new Date()));
                    year = CalendarAdjust.getYears(CalendarAdjust.GetYear(new Date()));
                    summons.setSummonsbegintime(CalendarAdjust.getFirstDayOfMonth1(year, month));
                    summons.setSummonsendtime(CalendarAdjust.getLastDayOfMonth1(year, month ));
                } else {
                    if (tSummons != null) {
                        month = CalendarAdjust.getMonth(tSummons.getSummonsendtime());
                        year = CalendarAdjust.getYears(tSummons.getSummonsendtime());
                        summons.setSummonsbegintime(CalendarAdjust.getFirstDayOfMonth1(year, month));
                        summons.setSummonsendtime(CalendarAdjust.getLastDayOfMonth1(year, month));
                    } else {
                        month = CalendarAdjust.getMonth(item.getBailoutbegindate().toString());
                        year = CalendarAdjust.getYears(item.getBailoutbegindate().toString());
                        summons.setSummonsbegintime(CalendarAdjust.getFirstDayOfMonth1(year, month));
                        summons.setSummonsendtime(CalendarAdjust.getLastDayOfMonth1(year, month ));
                    }

                }

                TSummons tSummons1 = taskDao.GetNumber(item.getPersonid());
                if (tSummons1 == null) {

                    taskDao.SaveSummons(summons);
                }else if (tSummons1!=null && (new Date().getTime()>CalendarAdjust.dateFormat.parse(tSummons1.getSummonsendtime()).getTime())){
                    taskDao.SaveSummons(summons);
                }
            }
        }

    }

    /**
     * 生成消息
     * @throws Exception
     */

    public void GetMessage() throws Exception {
        Remindersettings remindersettings = taskDao.GetConfigure("3");

        String[] strArr = null;
        if (remindersettings.getSettingday().indexOf(",") != -1) {
            strArr = remindersettings.getSettingday().split(",");
        }
        List<Personinformation> list = GetPerson();
        TMessage message = new TMessage();
        for (Personinformation item : list) {
            //填写消息内容
            message.setModular(5);
            message.setWorkcontent("监居人员" + item.getPersonname() + "下个月应进行传讯取证,请提前告知!");
            message.setPersonid(item.getPersonid());
            message.setModularname("传讯提醒");
            message.setReadmessage(false);
            if (taskDao.GetMessage(item.getPersonid(), CalendarAdjust.GetYear(new Date())) != 0) {
                if (strArr == null || strArr.length == 0) {
                    message.setMessagetime(
                            CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(Integer.parseInt(remindersettings.getSettingday()))));
                    taskDao.SaveMessage(message);
                } else {
                    for (int i = 0; i < strArr.length; i++) {
                        message.setMessagetime(
                                CalendarAdjust.timeStamp2Date(CalendarAdjust.perThridMouthTime(Integer.parseInt(strArr[i]))));
                        taskDao.SaveMessage(message);
                    }
                }
            }

        }
    }

    /**
     * 修改严重程度
     */
    public void Statisticalsummons(){
        Violationfens violationfens=taskDao.GetViolationfens(2);

        List<Personinformation> list = GetPerson();
        String severity="";
        for (Personinformation item : list) {

            int num=taskDao.StatisticalSummons(item.getPersonid(),CalendarAdjust.GetYear(new Date()));
            if (num< violationfens.getSlightfens()){
                severity="正常";
            }else if(num>=violationfens.getSlightfens() && num <violationfens.getSeriousfens()){
                severity="轻微";
            }else if(num>=violationfens.getSeriousfens()){
                severity="严重";
            }
            taskDao.UpdateDegree(item.getPersonid(),severity);
        }

    }




}
