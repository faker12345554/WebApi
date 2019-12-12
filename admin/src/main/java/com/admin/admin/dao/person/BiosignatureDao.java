package com.admin.admin.dao.person;

import com.admin.admin.entity.person.BiosignatureInformation;
import org.mapstruct.Mapper;

@Mapper
public interface BiosignatureDao {
    int insertBiosignature(BiosignatureInformation biosignatureInformation);

}
