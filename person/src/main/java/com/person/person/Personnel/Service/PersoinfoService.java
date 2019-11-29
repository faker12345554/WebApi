package com.person.person.Personnel.Service;

import com.person.person.Personnel.Dao.PersonDao;
import com.person.person.Personnel.Entity.Personinformation;
import com.person.person.model.ParamterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersoinfoService {

    @Autowired
    private PersonDao personDao;

    //新增
    public int insertPersion(Personinformation personinformation){
        return personDao.insertPersion(personinformation);
    }
    //修改
    public int updatePersion(Personinformation personinformation){return personDao.updatePersion(personinformation);}
    //删除
    public int deletePersion(ParamterModel paramterModel){return personDao.deletePersion(paramterModel);}
    //获取人员信息
    public Personinformation getPersoin(int id){return personDao.getPersoin(id);}
}
