package com.admin.admin.service.dw_violation;

import com.admin.admin.dao.dw_violation.ViolationDao;
import com.admin.admin.entity.dw_alarm.Alarmsettings;
import com.admin.admin.entity.dw_user.User;
import com.admin.admin.entity.dw_violation.Violationfens;
import com.admin.page.PageBean;
import com.admin.tool.CacheUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ViolationService {

    @Autowired
    private ViolationDao violationDao;

    /*
    新增或者修改
     */
    public int SaveViolation(Violationfens violationfens){



        return  violationDao.SaveViolation(violationfens);


    }
    /*
    获取下月生效的记录
     */

    public List<Violationfens>  enabledViolationList(){
        return violationDao.enabledViolationList();
    }

    /*
    删除
     */
    public int deleteViolation(int id){
        return violationDao.deleteViolation(id);
    }

    /*
    查看
     */
//    public Violationfens getViolation(int id){
//        return violationDao.selectViolation(id);
//    }
    /*
    列表
     */

    public List<Violationfens> ListViolation(){


        return violationDao.ListViolation();
    }

}
