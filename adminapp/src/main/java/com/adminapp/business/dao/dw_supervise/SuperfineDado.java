package com.adminapp.business.dao.dw_supervise;

import com.adminapp.business.entity.dw_supervise.Personinformation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SuperfineDado {

    List<Personinformation> listPersonInformation();

}
