package com.admin.admin.dao.dw_enum;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EnumDao {

    //获取枚举值
    List<Map<String,Object>> GetEnum(String Code);

    List<Map<String,Object>> GetPolice(String PoliceStation);


}
