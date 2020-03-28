package com.admin.admin.dao.master.dw_person;


import com.admin.admin.entity.dw_person.Personinformation;
import com.admin.admin.entity.dw_prisonsetting.TPrisonsetting;
import com.admin.model.Appstatistics.monthnumber;
import com.admin.model.search.SearchModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PersonDao {

    //新增
    int insertPersion(Personinformation personinformation);
    //修改
    int updatePersion(Personinformation personinformation);
    //删除
    int deletePersion(@Param("flag") boolean flag, @Param("PersonId") String PersonId);
    //查询
    Personinformation getPersoin(@Param("id") String id);

    int getPersonByCard(@Param("Card") String Card);


    /*
    变更主办人
    */
    int updateSponsor(String Name,String id,String PersonId);

    /*
    列表
     */
    List<Personinformation> ListPerson(@Param("searchmodel") SearchModel searchModel,@Param("type") int type,@Param("limit") String limit );

    /*
    批量设置
     */
    int insertprison(@Param("data")TPrisonsetting tPrisonsetting);


    int delconfig(TPrisonsetting tPrisonsetting);

    /*
    查询是否重复
     */
    int Getprison(String personId,String settingname);
    /*
    查询设置
     */
    List<TPrisonsetting> ListPrison(String PersonId);

    monthnumber getvolocation(String PersonId);

    List<Map<String,String>> getdetails(@Param("id") int id, @Param("personid") String personid);






}
