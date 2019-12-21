package com.admin.admin.dao.dw_reminder;

import com.admin.admin.entity.dw_reminder.Remindersettings;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReminderDao {
   /*
新增 待办提醒
  */
    int SaveReminder(Remindersettings remindersettings);

    /*
    修改待办提醒设置
     */
    int UpdateReminder(Remindersettings remindersettings);
    /*
    作废
     */
    int deleteReminder(int id);

    /*
    查看待办提醒设置
     */
    Remindersettings getReminder();
}
