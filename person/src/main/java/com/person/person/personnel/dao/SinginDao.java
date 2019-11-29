package com.person.person.personnel.dao;

import com.person.person.personnel.entity.SinginInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SinginDao {
    SinginInformation getSingin(@Param("person_id") int person_id);
}
