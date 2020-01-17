package com.prisonapp.business.dao.dw_enum;

import com.prisonapp.business.entity.dw_enum.AreaAddressInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnumDao {
    List<AreaAddressInfo> getProvice();
    List<AreaAddressInfo> getCity(String code);
    List<AreaAddressInfo> getDistrict(String code);
}
