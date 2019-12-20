package com.admin.admin.service.dw_sing;

import com.admin.admin.dao.dw_sing.SinginDao;
import com.admin.admin.entity.dw_sing.SinginInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinginService {

    @Autowired
    private SinginDao singinDao;


    //获取
    public SinginInformation getSingin(int personId){

        return singinDao.getSingin(personId);
    }
}
