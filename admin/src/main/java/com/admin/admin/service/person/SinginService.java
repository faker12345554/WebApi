package com.admin.admin.service.person;

import com.admin.admin.dao.person.SinginDao;
import com.admin.admin.entity.person.SinginInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinginService {
    @Autowired
    private SinginDao singinDao;

    //获取
    public SinginInformation getSingin(int personId){return singinDao.getSingin(personId);}
}
