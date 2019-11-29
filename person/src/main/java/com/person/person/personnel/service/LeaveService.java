package com.person.person.personnel.service;

import com.person.person.personnel.dao.LeaveDao;
import com.person.person.personnel.entity.AuditorInformation;
import com.person.person.personnel.entity.LeaveInformation;
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
