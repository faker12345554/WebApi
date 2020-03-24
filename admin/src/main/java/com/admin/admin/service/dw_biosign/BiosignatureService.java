package com.admin.admin.service.dw_biosign;

import com.admin.admin.dao.master.dw_biosign.BiosignatureDao;
import com.admin.admin.entity.dw_bios.BiosignatureInformation;
import com.admin.model.biosignature.BiosignatureReturnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiosignatureService {

    @Autowired
    private BiosignatureDao biosignatureDao;


    public int insertBiosignature(BiosignatureInformation biosignatureInformation) {
        return biosignatureDao.insertBiosignature(biosignatureInformation);
    }

    public List<BiosignatureReturnModel> getBiosignature(String personid, int type) {
        return biosignatureDao.getBiosignature(personid, type);
    }
}
