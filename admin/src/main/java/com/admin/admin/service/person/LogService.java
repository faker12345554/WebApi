package com.admin.admin.service.person;

import com.admin.admin.dao.person.LogDao;
import com.admin.admin.entity.person.LogInformation;
import com.admin.model.LogParamModel;
import com.admin.model.LogReturnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    @Autowired
    private LogDao logDao;

    public int insertLog(LogInformation logInformation){
        return logDao.insertLog(logInformation);
    }

    public LogReturnModel listLog(LogParamModel logParamModel){
        return logDao.listLog(logParamModel);
    }
}
