package com.admin.admin.service.person;

import com.admin.admin.dao.person.BiosignatureDao;
import com.admin.admin.entity.person.BiosignatureInformation;
import com.admin.model.biosignature.BiosignatureModel;
import com.admin.model.biosignature.BiosignatureReturnModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiosignatureService {

    @Autowired
    private BiosignatureDao biosignatureDao;

    public int insertBiosignature(BiosignatureInformation biosignatureInformation){
        return biosignatureDao.insertBiosignature(biosignatureInformation);
    }

    public List<BiosignatureReturnModel> getBiosignature(String person_id, int type){
        return biosignatureDao.getBiosignature(person_id, type);
    }
}
