package com.admin.admin.dao.dw_address;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AddressDao {

    /*
    获取地区编码
     */
    List<Map<String,String>> getAddress(String Code,String level);
}
