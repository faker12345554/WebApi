package com.person.person.Personnel.Dao;

import com.person.person.Personnel.Entity.GuaranteeInformation;
import com.person.person.model.ParamterModel;
import org.apache.ibatis.annotations.Param;

public interface GuarantDao {

    //新增
    int insertGuarant(GuaranteeInformation model);
    //修改
    int updateGuara(GuaranteeInformation model);
    //删除
    int deleteGuara(ParamterModel paramterModel);

    //获取人员信息
    GuaranteeInformation getGuara(@Param("id") int id);
}
