package com.admin.timer;

import com.admin.admin.dao.master.dw_violation.ViolationDao;
import com.admin.admin.entity.dw_violation.Violationfens;
import com.admin.admin.service.dw_app.MessageService;
import com.admin.admin.service.dw_person.PersoinfoService;
import com.admin.admin.service.dw_task.Tasking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
    private PersoinfoService persoinfoService;

    @Autowired
    private MessageService  messageService;

    @Autowired
    private ViolationDao violationDao;
    //每天凌晨1点执行
    @Scheduled(cron = "0 0 1 * * ?")
    public void depositJob() throws Exception {
        violationDao.Execution();
        tasking.ReminderBail();
        //执行代码
    }
    //每天凌晨2点执行
    @Scheduled(cron = "0 0 2 * * ?")
    public void job2() throws Exception {
        messageService.Synchronizedpolice();
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
    //tasking.GetMessageList(4);
        messageService.Synchronouscase();
    }
    //每天凌晨4点
    @Scheduled(cron = "0 0 4 * * ?")
    public void SendBailout() throws Exception{
        persoinfoService.getlistpernson();
        //tasking.GetMessageList(6);
    }
//    //每天凌晨三点
//    @Scheduled(cron = "0 0 3 * * ?")
//    public void SendPrison() throws Exception{
//        tasking.GetMessageList(7);
//    }
}
