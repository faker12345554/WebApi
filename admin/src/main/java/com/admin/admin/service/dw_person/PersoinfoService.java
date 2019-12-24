package com.admin.admin.service.dw_person;

import com.admin.admin.dao.dw_person.PersonDao;
import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_sysenum.Dictionary;
import com.admin.tool.CacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersoinfoService {

    @Autowired
    private PersonDao personDao;



    //新增
    public int insertPersion(Personinformation personinformation){
        personinformation.setFounderid(CacheUtils.get("UserId").toString());
        personinformation.setFoundertime(new Date());

        return personDao.insertPersion(personinformation);
    }
    //修改
    public int updatePersion(Personinformation personinformation){
        personinformation.setFounderid(CacheUtils.get("UserId").toString());
        personinformation.setFoundertime(new Date());

        return personDao.updatePersion(personinformation);
    }
    //删除
    public int deletePersion(boolean flag,String PersionId){

        return personDao.deletePersion(flag,PersionId);
    }
    //获取人员信息
    public Personinformation getPersoin(String id){

        return  personDao.getPersoin(id);
    }

    public int getPersonByCard(String Card){

        return personDao.getPersonByCard(Card);
    }

    //获取枚举数据
    public List<Dictionary> getEnum(){
        return personDao.getEnum();
    }
}
