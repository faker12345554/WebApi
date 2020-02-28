package com.admin.timer;

import com.admin.admin.dao.dw_violation.ViolationDao;
import com.admin.admin.entity.dw_message.TMessage;
import com.admin.admin.entity.dw_violation.Violationfens;
import com.admin.admin.service.dw_task.Tasking;
import com.common.common.apppush.Demo;
import com.common.common.authenticator.CalendarAdjust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//@Component注解用于对那些比较中立的类进行注释；
//相对与在持久层、业务层和控制层分别采用 @Repository、@Service 和 @Controller 对分层中的类进行注释
@Configuration
@Component
@EnableScheduling   // 1.开启定时任务
@EnableAsync        // 2.开启多线程
@Lazy(false)
public class Datatimer  {

    @Autowired
    private Tasking tasking;

    @Autowired
    private ViolationDao violationDao;
    //每天凌晨1点执行
    @Scheduled(cron = "0 0 1 * * ?")
    public void depositJob() throws Exception {

        tasking.ReminderBail();
        //执行代码
    }
    //每天凌晨2点执行
    @Scheduled(cron = "0 0 2 * * ?")
    public void job2() throws Exception {

        tasking.GeneratedRecord();
    }
    //每个月1号凌晨一点执行
    @Scheduled(cron = "0 0 1 1 * ?")
    public void updatePayRecords() throws Exception {
        List<Violationfens> violationlist = violationDao.ListViolation();
        List<Violationfens> violation = violationDao.enabledViolationList();
        if (violation.size()!=0){
            for (Violationfens item:violation){
                violationDao.updateStatus(true,false,item.getId());
            }
        }
        if (violationlist.size()!=0){
            for (Violationfens item:violationlist){
                violationDao.updateStatus(false,true,item.getId());
            }
        }
       // System.out.println(CalendarAdjust.GetMonth());

    }
    //每个月1号凌晨2点执行
    @Scheduled(cron = "0 0 2 1 * ?")
    public void Savemessage() throws Exception{
        tasking.GetMessage();
    }
    //每个月1号凌晨2点执行
    @Scheduled(cron = "0 0 3 1 * ?")
    public void Statisticalsummons() throws Exception{
        tasking.Statisticalsummons();
    }
    //每天凌晨三点
    @Scheduled(cron = "0 0 3 * * ?")
    public void SendSummons() throws Exception{
    tasking.GetMessageList(5);
    }
    //每天凌晨三点
    @Scheduled(cron = "0 0 3 * * ?")
    public void SendBailout() throws Exception{
        tasking.GetMessageList(6);
    }
    //每天凌晨三点
    @Scheduled(cron = "0 0 3 * * ?")
    public void SendPrison() throws Exception{
        tasking.GetMessageList(7);
    }
}
