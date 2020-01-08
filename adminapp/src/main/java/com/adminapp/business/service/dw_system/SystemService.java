package com.adminapp.business.service.dw_system;

import com.adminapp.business.dao.dw_system.SystemDao;
import com.adminapp.model.dw_system.SubmitAdviceModel;
import com.adminapp.model.dw_system.UpdateInformationModel;
import com.adminapp.model.dw_system.UpdateRecordModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemService {
    @Autowired
    private SystemDao systemDao;

    //获取APP更新信息
    public List<UpdateInformationModel> getUpdateInformation(int type){
        return systemDao.getUpdateInformation(type);
    }

    //获取更新记录
    public List<UpdateRecordModel> getUpdateRecord(int type){
        return systemDao.getUpdateRecord(type);
    }

    //提交意见反馈
    public int submitAdvice(SubmitAdviceModel submitAdviceModel){
        return systemDao.submitAdvice(submitAdviceModel);
    }
}
