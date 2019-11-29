package com.person.person.personnel.dao;

import com.person.person.personnel.entity.Personinformation;
import com.person.person.model.ParamterModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonDao {

    //新增
    int insertPersion( Personinformation personinformation);
    //修改
    int updatePersion(Personinformation personinformation );
    //删除
    int deletePersion(ParamterModel paramterModel);
    //查询
    Personinformation getPersoin(@Param("id") int id);
}
