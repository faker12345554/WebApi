package com.admin.admin.service.dw_picture;

import com.admin.admin.dao.dw_picture.PictureReportDao;
import com.admin.admin.entity.dw_person.Personinformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureReportService {
    @Autowired
    private PictureReportDao pictureReportDao;

    public Personinformation checkPersonId(String personid){
        return pictureReportDao.checkPersonId(personid);
    }

    public int postPictureReport(String perosnid,String facepath){
        return pictureReportDao.postPictureReport(perosnid, facepath);
    }
}
