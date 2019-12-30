package com.prisonapp.business.dao.dw_system;


import com.prisonapp.business.entity.dw_system.GetUpdateInfoModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemDao {
    List<GetUpdateInfoModel> getUpdateInfo();
}
