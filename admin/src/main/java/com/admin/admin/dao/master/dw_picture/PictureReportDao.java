package com.admin.admin.dao.master.dw_picture;

import com.admin.admin.entity.dw_person.Personinformation;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

@Mapper
public interface PictureReportDao {
    Personinformation checkPersonId(@Param("personid") String personid);

    int postPictureReport(@Param("personid")String personid,@Param("facepath")String facepath);
}
