package com.person.person.Personnel.Dao;

import com.person.person.Personnel.Entity.Guaranteeinformation;
import org.apache.ibatis.annotations.Param;

public interface GuarantDao {

    //新增
    int AddGuarant(@Param("model")Guaranteeinformation model);
    //修改
    int UpdateGuara(@Param("model")Guaranteeinformation model);
    //删除
    int DelGuara(@Param("id")int id,@Param("flag") boolean flag);

    //获取人员信息
    Guaranteeinformation GetGuara(@Param("id") int id);
}
