package com.admin.admin.dao.second;

import com.admin.admin.entity.dw_case.TCaseinfo;
import com.admin.admin.entity.dw_fsgayw.FsgaYwRyb;
import com.admin.admin.entity.dw_person.Personinformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface fsdao {

    //获得警员信息
    List<FsgaYwRyb> getfslist(@Param("Date") String Date);

    //获取案件信息
    List<TCaseinfo> getCaseing(@Param("Date") String Date);

    List<Personinformation> getperson(@Param("Date") String Date);




}
