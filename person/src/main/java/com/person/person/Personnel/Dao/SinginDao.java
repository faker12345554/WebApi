package com.person.person.Personnel.Dao;

import com.person.person.Personnel.Entity.SinginInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SinginDao {
    SinginInformation getSingin(@Param("person_id") int person_id);
}
