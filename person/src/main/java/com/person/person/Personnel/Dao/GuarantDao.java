package com.person.person.Personnel.Dao;

import com.person.person.Personnel.Entity.Guaranteeinformation;
import org.apache.ibatis.annotations.Param;

public interface GuarantDao {

    //新增
    int Addguarant(@Param("model")Guaranteeinformation model);
    //修改
    int Updateguara(@Param("model")Guaranteeinformation model);
    //删除
    int Delguara(@Param("id")int id,@Param("flag") boolean flag);

    //获取人员信息
    Guaranteeinformation Getguara(@Param("id") int id);
}
