package com.person.person.Personnel.Dao;

import com.person.person.Personnel.Entity.Personinformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonDao {

    //新增
    int AddPersion(@Param("personinformation") Personinformation personinformation);
    //修改
    int UpdatePersion(@Param("personinformation") Personinformation personinformation );
    //删除
    int DelPersion(@Param("id") int id,@Param("flag") boolean flag);
    //查询
    Personinformation GetPersoin(@Param("id") int id);
}
