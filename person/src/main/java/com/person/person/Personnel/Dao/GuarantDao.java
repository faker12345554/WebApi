package com.person.person.Personnel.Dao;

import com.person.person.Personnel.Entity.Guaranteeinformation;
import com.person.person.model.ParamterModel;
import org.apache.ibatis.annotations.Param;

public interface GuarantDao {

    //新增
    int Addguarant(Guaranteeinformation model);
    //修改
    int Updateguara(Guaranteeinformation model);
    //删除
    int Delguara(ParamterModel paramterModel);

    //获取人员信息
    Guaranteeinformation Getguara(@Param("id") int id);
}
