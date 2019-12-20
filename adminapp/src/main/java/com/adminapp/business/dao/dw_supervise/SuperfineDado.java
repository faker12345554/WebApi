package com.adminapp.business.dao.dw_supervise;

import com.adminapp.business.entity.dw_supervise.Personinformation;
import com.adminapp.model.dw_supervise.PrisonSettingModel;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SuperfineDado {

    List<Personinformation> listPersonInformation();

    PrisonSettingModel getPrisonSetting(@Param("personid") String personid);

}
