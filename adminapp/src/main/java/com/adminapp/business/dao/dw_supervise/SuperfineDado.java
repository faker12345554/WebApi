package com.adminapp.business.dao.dw_supervise;

import com.adminapp.business.entity.dw_supervise.Personinformation;
import com.adminapp.business.entity.dw_supervise.ReportSettingsInformation;
import com.adminapp.business.entity.dw_supervise.SinginInformation;
import com.adminapp.business.entity.dw_supervise.SummonsInformation;
import com.adminapp.model.dw_supervise.*;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SuperfineDado {

    List<Personinformation> listPersonInformation(@Param("UserId")String UserId);

    List<PrisonSettingModel> getPrisonSetting(@Param("personid") String personid);

    List<ReportLocationModel> listLocation(@Param("personid")String personid);

    List<ReportSettingsInformation> listReportSetting();

    List<SinginInformation> listSingin(@Param("personid") String personid,@Param("type") int type);

    List<SummonsInformation> listCiteRecord();

    List<SummonsInformation> listKeyCiteRecord(@Param("key") String key);

    PersonAllInformationModel getPersonInformation(@Param("personid")String personid);


    int insertCiteRecord(@Param("date")CiteRecordSubmitModel citeRecordSubmitModel);
}
