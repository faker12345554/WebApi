package com.person.person.personnel.service;

import com.person.person.personnel.dao.SinginDao;
import com.person.person.personnel.entity.SinginInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinginService {
    @Autowired
    private SinginDao singinDao;

    //获取
    public SinginInformation getSingin(int person_id){return singinDao.getSingin(person_id);}
}
