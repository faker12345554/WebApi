package com.admin.admin.service.dw_Alarm;

import com.admin.admin.dao.dw_alarm.AlarmDao;
import com.admin.admin.entity.dw_alarm.Alarmsettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmService {

    @Autowired
    private AlarmDao alarmDao;

    /*
    新增 或者修改
     */
    public int SaveOrUpdateAlarm(Alarmsettings alarmsettings){

        if (alarmsettings.getId()!=0){
            return alarmDao.UpdateAlarm(alarmsettings);
        }
        return alarmDao.SaveAlarm(alarmsettings);
    }

    /*
    删除
     */
    public int DeleteAlarm(int id){
        return alarmDao.deleteAlarm(id);
    }
    /*
    查看
     */

    public Alarmsettings getAlarm(){
        return alarmDao.getAlarm();
    }
}
