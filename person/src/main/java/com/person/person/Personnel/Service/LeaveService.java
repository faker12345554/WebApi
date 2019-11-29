package com.person.person.Personnel.Service;

import com.person.person.Personnel.Dao.LeaveDao;
import com.person.person.Personnel.Entity.AuditorInformation;
import com.person.person.Personnel.Entity.LeaveInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveService {
    @Autowired
    private LeaveDao leaveDao;

    //获取
    public LeaveInformation getLeave(int id) {
        return leaveDao.getLeave(id);
    }

    //获取列表
    public LeaveInformation listLeave() {
        return leaveDao.listLeave();
    }

    //更新
    public int updateLeave(LeaveInformation leaveinformation) {
        return leaveDao.updateLeave(leaveinformation);
    }

    public int insertAuditor(AuditorInformation auditorInformation) {
        return leaveDao.insertAuditor(auditorInformation);
    }
}
