package com.admin.admin.service.scope;

import com.admin.admin.dao.scope.enclosureDao;
import com.admin.admin.entity.scope.enclosure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class enclosureService {

    @Autowired
    private enclosureDao enclosureDao;

    public int saveEnclosure(enclosure enclosure){
        return enclosureDao.saveEnclosure(enclosure);
    }

    public int updateEnclosure(enclosure enclosure){
        return enclosureDao.updateEnclosure(enclosure);
    }

    public int deleteEnclosure(int id){
        return enclosureDao.deleteEnclosure(id);
    }
}
