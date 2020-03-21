package com.admin.admin.dao.master.dw_alarm;

import com.admin.admin.entity.dw_alarm.Alarmsettings;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlarmDao {

    /*
    新增
     */
    int SaveAlarm( Alarmsettings alarmsettings);

    /*
    修改
     */
    int UpdateAlarm(Alarmsettings alarmsettings);

    /*
    查看
     */
    List<Alarmsettings> getAlarm();
    /*
    作废
     */
    int deleteAlarm();
}
