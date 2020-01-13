package com.admin.admin.dao.dw_enum;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EnumDao {

    //获取枚举值
    List<Map<String,Object>> GetEnum(String Code);

    List<Map<String,Object>> GetPolice(String PoliceStation);

    //获取枚举数据
    List<Map<String,Object>> getEnum();

    /**
     * 获取所有 一定要用 List<Map<String,Object>> ？？是的
     * @return
     */
    List<Map<String,Object>> ListMechanism();

    /**
     * 派出所编号
     * @param Code
     * @return
     */
    List<Map<String,String>> ListSponsor(String Code);


}
