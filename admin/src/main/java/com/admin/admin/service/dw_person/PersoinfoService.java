package com.admin.admin.service.dw_person;

import com.admin.admin.dao.dw_person.PersonDao;
import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_prisonsetting.TPrisonsetting;
import com.admin.admin.entity.dw_sysenum.Dictionary;
import com.admin.model.search.SearchModel;
import com.admin.page.PageBean;
import com.admin.tool.CacheUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PersoinfoService {

    @Autowired
    private PersonDao personDao;


    //新增
    public int insertPersion(Personinformation personinformation) {
        personinformation.setFounderid(CacheUtils.get("UserId").toString());
        personinformation.setFoundertime(new Date());

        return personDao.insertPersion(personinformation);
    }

    //修改
    public int updatePersion(Personinformation personinformation) {
        personinformation.setFounderid(CacheUtils.get("UserId").toString());
        personinformation.setFoundertime(new Date());

        return personDao.updatePersion(personinformation);
    }

    //删除
    public int deletePersion(boolean flag, String PersionId) {

        return personDao.deletePersion(flag, PersionId);
    }

    //获取人员信息
    public Personinformation getPersoin(String id) {

        return personDao.getPersoin(id);
    }

    /*
    根据身份证查询是否重复
     */
    public int getPersonByCard(String Card) {

        return personDao.getPersonByCard(Card);
    }

    /*
    变更主办人
     */
    public int updateSponsor(String Name, String id, String PersonId) {

        return personDao.updateSponsor(Name, id, PersonId);
    }

    /*
    配置管理方式
     */

    public int insertprison(List<TPrisonsetting> List) {
        TPrisonsetting tPrisonsetting = new TPrisonsetting();
        for (TPrisonsetting item : List) {
            item.setSettingcheck(true);
            item.setSettingtime(new Date());
            personDao.insertprison(item);
        }
        return tPrisonsetting.getId();
    }

    /*
    人员信息列表
     */
    public List<Personinformation> ListPerson(SearchModel searchModel) {
        List<Personinformation> personList = personDao.ListPerson(searchModel);//这里面是每个人的信息
        for (Personinformation item : personList) {
            List<TPrisonsetting> prisonList = personDao.ListPrison(item.getPersonid());//这里就是这个人有哪几种管理方式 根据身份证号查询
            List<String> list = new ArrayList<>();
            for (TPrisonsetting itam : prisonList) {

                list.add(itam.getSettingname());
            }
            item.setManagementStyle(list);
        }


        return personList;
    }

    //获取枚举数据
    public List<Dictionary> getEnum() {
        return personDao.getEnum();
    }

    /**
     * 获取机构
     */
    public List<Map<String,Object>> ListMechanism(){
        return personDao.ListMechanism();
    }

    /**
     * 获取主办人信息
     * @param Code
     * @return
     */

    public List<Map<String,String>> ListSponsor(String Code){
        return personDao.ListSponsor(Code);
    }
}
