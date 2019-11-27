package com.person.person.Personnel.Service;

import com.person.person.Personnel.Dao.LeaveDao;
import com.person.person.Personnel.Entity.AuditorInformation;
import com.person.person.Personnel.Entity.Leaveinformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveService {
    @Autowired
    private LeaveDao leaveDao;

    //获取
    public Leaveinformation GetLeave(int id) {
        return leaveDao.GetLeave(id);
    }
    //更新
    public int UpdateLeave(Leaveinformation leaveinformation) {
        return leaveDao.UpdateLeave(leaveinformation);
    }
    public int Addauditor(AuditorInformation auditorInformation){
        return leaveDao.Addauditor(auditorInformation);
    }
}
