package com.admin.admin.service.dw_Reminder;

import com.admin.admin.dao.dw_reminder.ReminderDao;
import com.admin.admin.entity.dw_reminder.Remindersettings;
import com.admin.admin.entity.dw_report.Reportsettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReminderService {
    @Autowired
    private ReminderDao reminderDao;

    /*
    保存或者修改
     */
    public int SaveOrUpdateReminder(Remindersettings remindersettings){
        if (remindersettings.getId()!=0){
            return reminderDao.UpdateReminder(remindersettings);
        }
        return reminderDao.SaveReminder(remindersettings);
    }

    /*
    作废
     */
    public int DeleteReminder(int id){
        return  reminderDao.deleteReminder(id);
    }

    /*
    查看
     */
    public Remindersettings getReminder(int id){
        return reminderDao.getReminder();
    }
}
