package com.admin.admin.dao.dw_alarm;

import com.admin.admin.entity.dw_alarm.Alarmsettings;

public interface AlarmDao {

    /*
    新增
     */
    int SaveAlarm(Alarmsettings alarmsettings);

    /*
    修改
     */
    int UpdateAlarm(Alarmsettings alarmsettings);

    /*
    查看
     */
    Alarmsettings getAlarm();
    /*
    作废
     */
    int deleteAlarm(int id);
}
