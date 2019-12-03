package com.admin.admin.service.person;

import com.admin.admin.dao.person.LeaveDao;
import com.admin.admin.entity.person.AuditorInformation;
import com.admin.admin.entity.person.LeaveInformation;
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
