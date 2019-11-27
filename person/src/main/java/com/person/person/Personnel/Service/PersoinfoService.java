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
    public int Addpersion(Personinformation personinformation){
        return personDao.Addpersion(personinformation);
    }
    //修改
    public int Updatepersion(Personinformation personinformation){return personDao.Updatepersion(personinformation);}
    //删除
    public int Delpersion(ParamterModel paramterModel){return personDao.Delpersion(paramterModel);}
    //获取人员信息
    public Personinformation Getpersoin(int id){return personDao.Getpersoin(id);}
}
