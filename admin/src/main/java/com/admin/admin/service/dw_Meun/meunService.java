package com.admin.admin.service.dw_Meun;

import com.admin.admin.dao.dw_meun.MeunDao;
import com.admin.admin.entity.dw_menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class meunService {

    @Autowired
    private MeunDao meunDao;

    public List<Menu> GetMenuList(){
        return meunDao.GetMenuList();
    }

    public int delMeun(boolean flag,int id){
        return meunDao.delMeun(flag,id);
    }

    public Menu GetMeunbyid(int id){
        return meunDao.GetMeunbyid(id);
    }
}
