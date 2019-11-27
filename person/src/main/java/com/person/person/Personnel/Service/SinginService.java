package com.person.person.Personnel.Service;

import com.person.person.Personnel.Dao.SinginDao;
import com.person.person.Personnel.Entity.SinginInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinginService {
    @Autowired
    private SinginDao singinDao;

    //获取
    public SinginInformation GetSingin(int person_id){return singinDao.GetSingin(person_id);}
}
