package com.admin.admin.dao.person;

import com.admin.admin.entity.person.BiosignatureInformation;
import com.admin.model.biosignature.BiosignatureModel;
import com.admin.model.biosignature.BiosignatureReturnModel;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BiosignatureDao {
    int insertBiosignature(BiosignatureInformation biosignatureInformation);

    List<BiosignatureReturnModel> getBiosignature(@Param("person_id")String person_id, @Param("type")int type);

}
