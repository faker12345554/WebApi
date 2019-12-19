package com.admin.admin.dao.dw_address;

import com.admin.admin.entity.dw_address.AddressInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressDao {
    int insertLocation(AddressInformation model);
}
