package com.admin.admin.service.dw_enum;

import com.admin.admin.dao.dw_enum.EnumDao;
import com.admin.admin.entity.dw_enum.TEnum;
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


    //获取枚举数据
    public List<Map<String,Object>> getEnum() {
        return enumDao.getEnum();
    }

    /**
     * 获取机构 搞得那麽複雜 没进来吗？实在是因为那个实体又臭又长,而我需要的就两个
     */
    public List<Map<String,Object>> ListMechanism(){
       // System.out.println(enumDao.ListMechanism());
        return enumDao.ListMechanism();
    }

    /**
     * 获取主办人信息
     * @param Code
     * @return
     */

    public List<Map<String,String>> ListSponsor(String Code){
        return enumDao.ListSponsor(Code);
    }

    public List<TEnum> listEnum(String typeCode){
        return enumDao.listEnum(typeCode);
    }

    //新增枚举类型
    public int addEnum(String typeName,String typeCode,boolean status,String enumCode,String enumName){
        return enumDao.addEnum(typeName,typeCode,status,enumCode,enumName);
    }

    //修改数据字典
    public int updateEnum(int enumId,String typeName,String typeCode,boolean status,String enumName){
        return enumDao.updateEnum(enumId, typeName, typeCode, status, enumName);
    }
}
