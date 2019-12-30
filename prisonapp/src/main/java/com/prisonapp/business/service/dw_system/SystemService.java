package com.prisonapp.business.service.dw_system;

import com.prisonapp.business.dao.dw_system.SystemDao;
import com.prisonapp.business.entity.dw_system.GetUpdateInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemService {
    @Autowired
    private SystemDao systemDao;
    public List<GetUpdateInfoModel> getUpdateInfo(){
        return  systemDao.getUpdateInfo();
    }
}
