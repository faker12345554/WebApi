package com.person.person.Personnel.Service;

import com.person.person.Personnel.Dao.PersonDao;
import com.person.person.Personnel.Entity.Personinformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersoinfoService {

    @Autowired
    private PersonDao personDao;

    //新增
    public int AddPersion(Personinformation personinformation){return personDao.AddPersion(personinformation); }
    //修改
    public int UpdatePersion(Personinformation personinformation){return personDao.UpdatePersion(personinformation);}
    //删除
    public int DelPersion(int id,boolean flag){return personDao.DelPersion(id,flag);}
    //获取人员信息
    public Personinformation GetPersoin(int id){return personDao.GetPersoin(id);}
}
