package com.admin.admin.dao.master.dw_address;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AddressDao {

    /*
    获取地区编码
     */
    List<Map<String,String>> getAddress(@Param("Code") String Code, @Param("level") int level);
}
