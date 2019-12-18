package com.admin.admin.dao.dw_person;


import com.admin.admin.entity.dw_person.Personinformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}
