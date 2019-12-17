package com.admin.admin.dao.person;

import com.admin.admin.entity.person.GuaranteeInformation;
import com.admin.model.ParamterModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface GuarantDao {

    //新增
    int insertGuarant(GuaranteeInformation model);
    //修改
    int updateGuara(GuaranteeInformation model);
    //删除
    int deleteGuara(@Param("flag") boolean flag, @Param("GuaId") int GuaId);

    //获取人员信息
    GuaranteeInformation getGuara(@Param("id") int id);
}
