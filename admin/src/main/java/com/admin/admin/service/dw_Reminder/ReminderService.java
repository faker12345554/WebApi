package com.admin.admin.service.dw_Reminder;

import com.admin.admin.dao.dw_reminder.ReminderDao;
import com.admin.admin.entity.dw_reminder.Remindersettings;
import com.admin.admin.entity.dw_report.Reportsettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReminderService {
    @Autowired
    private ReminderDao reminderDao;

    /*
    保存或者修改
     */
    public int SaveOrUpdateReminder(List<Remindersettings> remindersettings) {
        reminderDao.deleteReminder();
        int id=0;
        for (Remindersettings item : remindersettings) {
            item.setStatus(true);
            item.setCreatetime(new Date());
//            if (item.getId()!=0){
//                return reminderDao.UpdateReminder(item);
//            }
           id= reminderDao.SaveReminder(item);
        }
        return id;

    }

    /*
    作废
     */
    public int DeleteReminder() {
        return reminderDao.deleteReminder();
    }

    /*
    查看
     */
    public List<Remindersettings> getReminder() {
        return reminderDao.getReminder();
    }
}
