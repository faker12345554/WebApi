package com.admin.admin.service.dw_enum;

import com.admin.admin.dao.dw_enum.EnumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EnumService {

    @Autowired
    private EnumDao enumDao;

    //获取枚举值
    public List<Map<String,Object>> GetEnum(String Code){
        return enumDao.GetEnum(Code);
    }

    public List<Map<String,Object>> GetPolice(String PoliceStation){
        return enumDao.GetPolice(PoliceStation);
    }
}
