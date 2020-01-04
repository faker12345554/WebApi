package com.admin.timer;

import com.admin.admin.service.dw_task.Tasking;
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

    //定义一个按时间执行的定时任务，在每天02:00执行一次。
    @Scheduled(cron = "0 0 1 * * ?")
    public void depositJob() throws Exception {
        System.out.println(2);
        tasking.ReminderBail();
        //执行代码
    }
    //定义一个按一定频率执行的定时任务，每隔1分钟执行一次
    @Scheduled(cron = "0 0 2 * * ?")
    public void job2() throws Exception {
        System.out.println(3);
        tasking.GeneratedRecord();
    }
    //定义一个按一定频率执行的定时任务，每隔1分钟执行一次，延迟1秒执行
    @Scheduled(fixedRate = 1000 * 60,initialDelay = 1000)
    public void updatePayRecords() throws Exception {
        //执行代码
       // System.out.println(CalendarAdjust.GetMonth());
        System.out.println(1);

    }
}
