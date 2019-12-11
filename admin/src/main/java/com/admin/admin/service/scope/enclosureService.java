package com.admin.admin.service.scope;

import com.admin.admin.dao.scope.enclosureDao;
import com.admin.admin.entity.scope.enclosure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class enclosureService {

    @Autowired
    private enclosureDao enclosureDao;

    //新增
    public int saveEnclosure(enclosure enclosure){
        return enclosureDao.saveEnclosure(enclosure);
    }
    //修改
    public int updateEnclosure(enclosure enclosure){
        return enclosureDao.updateEnclosure(enclosure);
    }
    //删除
    public int deleteEnclosure(String personId){
        return enclosureDao.deleteEnclosure(personId);
    }

    //根据人员编号查询
    public int selectEnclosureByPersonId(String personId){
        return enclosureDao.selectEnclosureByPersonId(personId);
    }

    //查看
    public List<enclosure> selectEnclosure(String personId){
        return enclosureDao.selectEnclosure(personId);
    }
}
