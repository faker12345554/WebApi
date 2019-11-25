package com.person.person.Personnel.Service;

import com.person.person.Personnel.Dao.GuarantDao;
import com.person.person.Personnel.Entity.Guaranteeinformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuaranService {
    @Autowired
    private GuarantDao guarantDao;

    //新增
    public int AddGuarant(Guaranteeinformation guaranteeinformation){
        return guarantDao.AddGuarant(guaranteeinformation);
    }
    //修改
    public int UpdateGuara(Guaranteeinformation guaranteeinformation){
        return guarantDao.UpdateGuara(guaranteeinformation);
    }
    //删除
    public int DelGuara(int id,boolean flag){return guarantDao.DelGuara(id,flag);}

    //获取
    public Guaranteeinformation GetGuara(int id){return guarantDao.GetGuara(id); }
}
