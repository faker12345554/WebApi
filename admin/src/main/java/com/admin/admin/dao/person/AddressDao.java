package com.admin.admin.dao.person;

import com.admin.admin.entity.person.AddressInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressDao {
    int insertLocation(AddressInformation model);
}
