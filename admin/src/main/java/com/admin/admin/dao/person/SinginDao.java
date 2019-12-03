package com.admin.admin.dao.person;

import com.admin.admin.entity.person.SinginInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SinginDao {
    SinginInformation getSingin(@Param("person_id") int person_id);
}
