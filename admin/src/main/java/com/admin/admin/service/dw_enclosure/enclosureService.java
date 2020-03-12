package com.admin.admin.service.dw_enclosure;

import com.admin.admin.dao.dw_enclosure.enclosureDao;
import com.admin.admin.entity.dw_enclosure.enclosure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class enclosureService {

    @Autowired
    private enclosureDao enclosureDao;


    //新增
    public int saveEnclosure(enclosure enclosure) {
        enclosureDao.deleteEnclosure(enclosure.getPersonId());

        return enclosureDao.saveEnclosure(enclosure);
    }

    public int selectEnclosureByPersonId(String PersonId) {
        return enclosureDao.selectEnclosureByPersonId(PersonId);
    }

    //修改
    public int updateEnclosure(enclosure enclosure) {

        return enclosureDao.updateEnclosure(enclosure);
    }

    //删除
    public int deleteEnclosure(String personId) {

        return enclosureDao.deleteEnclosure(personId);
    }


    //查看
    public List<enclosure> selectEnclosure(String personId) {

        return enclosureDao.selectEnclosure(personId);
    }
}
