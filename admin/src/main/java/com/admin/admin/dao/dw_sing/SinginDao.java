package com.admin.admin.dao.dw_sing;

import com.admin.admin.entity.dw_sing.SinginInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SinginDao {
    SinginInformation getSingin(@Param("personId") int personId);
}
