package com.admin.admin.service.dw_Alarm;

import com.admin.admin.dao.master.dw_alarm.AlarmDao;
import com.admin.admin.entity.dw_alarm.Alarmsettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlarmService {

    @Autowired
    private AlarmDao alarmDao;

    /*
    新增 或者修改
     */
    public int SaveOrUpdateAlarm(List<Alarmsettings> alarmsettings) {
        alarmDao.deleteAlarm();

        for (Alarmsettings item : alarmsettings) {
            item.setStatus(true);
            item.setCreatetime(new Date());
//            if (item.getId() != 0) {
//                return alarmDao.UpdateAlarm(item);
//            }
           // alarmDao.SaveAlarm(item);
            System.out.println( alarmDao.SaveAlarm(item));
        }
        Alarmsettings alarm=new Alarmsettings();

        return  alarm.getId();
    }

    /*
    删除
     */
    public int DeleteAlarm() {
        return alarmDao.deleteAlarm();
    }
    /*
    查看
     */

    public List<Alarmsettings> getAlarm() {
        return alarmDao.getAlarm();
    }
}
