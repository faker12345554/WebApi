package com.person.person.Personnel.Service;

import com.person.person.Personnel.Dao.GuarantDao;
import com.person.person.Personnel.Entity.Guaranteeinformation;
import com.person.person.model.ParamterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuaranService {
    @Autowired
    private GuarantDao guarantDao;

    //新增
    public int AddGuarant(Guaranteeinformation guaranteeinformation){
        return guarantDao.Addguarant(guaranteeinformation);
    }
    //修改
    public int UpdateGuara(Guaranteeinformation guaranteeinformation){
        return guarantDao.Updateguara(guaranteeinformation);
    }
    //删除
    public int DelGuara(ParamterModel paramterModel){return guarantDao.Delguara(paramterModel);}

    //获取
    public Guaranteeinformation GetGuara(int id){return guarantDao.Getguara(id); }
}
